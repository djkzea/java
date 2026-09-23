# Fityesz — JavaFX graphical version

**Date:** 2026-09-23
**Status:** design, awaiting approval
**Target:** turn the console game into a JavaFX visual novel

---

## 1. What we are building

A desktop window showing the existing Fityesz story as a visual novel:
background art per location, a character portrait when someone speaks, a
styled dialogue box, clickable choice buttons, a persistent HUD showing
XP / lebukás / szint, and a dedicated combat screen with animated HP bars
for the three boss fights.

**The story does not change.** Same seven chapters, same choices, same
numbers, same endings, same HU/EN. This is a presentation change, not a
redesign.

### Decisions already made

| Question | Answer |
|---|---|
| How graphical | Visual novel window — backgrounds, portraits, choice buttons |
| Toolkit | JavaFX |
| Artwork | We draw or generate it ourselves |
| Java | JDK 24, currently a plain IntelliJ module (no Maven/Gradle) |

### Assumptions — correct any of these and the plan adjusts

1. **Timeline is a few weeks**, not a few days. The phases below are
   ordered so you can stop after any one of them and still have a
   working, demoable game.
2. **The GUI replaces the console version** as the thing you present.
   The old files stay in git history, and Phase 1 keeps the console
   running anyway, so nothing is lost if you change your mind.
3. **No save/load.** The game is one sitting, as it is today.
4. **No sound** until Phase 6, and it is optional there.

---

## 2. The real problem

The GUI is not the hard part. This is:

```java
// fityesz1_0.java — one method, ~660 lines
public static void jatek(Scanner sc) {
    ...
    UI.menu(Lang.t("ch1.q1"), ...);
    int valasztas1 = sc.nextInt();   // <-- BLOCKS here
    if (valasztas1 == 1) { xp += 15; lebukas += 10; ... }
    ...                               // 600 more lines like this
}
```

The whole game is one long straight line of code that **stops and waits**
at `sc.nextInt()`. That works in a console. A GUI cannot work that way:
JavaFX has a single UI thread that must never block, because while it is
blocked the window freezes — no repainting, no button clicks, nothing.

So the central design question is: *how do we let linear story code
coexist with an event-driven window?*

### Three ways to solve it

**Option A — Rewrite the story as data.**
Convert every beat into `Scene` and `Choice` objects, and drive them with
a state machine that advances on each click.

*Good:* the cleanest result; unlocks save/load, a scene list, and editing
the story without touching Java.
*Bad:* you hand-convert 660 lines of branching logic into data. It is a
long, boring, error-prone slog, and every mistake is a broken story beat
that only shows up when someone replays that exact path. High risk of
spending your whole internship on data entry instead of a game.

**Option B — Keep the linear code; run it on its own thread. (RECOMMENDED)**
The story keeps its current shape and runs on a background thread. Where
it used to print, it calls a `Presenter`. Where it used to block on
`sc.nextInt()`, it blocks on a queue instead — and the JavaFX button
handler is what puts the answer into that queue.

*Good:* the existing game logic survives almost untouched, so the story
cannot silently break. The change to `fityesz1_0.java` is mechanical:
`System.out.println(x)` becomes `view.say(x)`, `sc.nextInt()` becomes
`view.ask(...)`. You get a running window fast and spend your remaining
time on how it *looks*, which is the actual assignment.
*Bad:* you must get the threading right once, in one small class. We give
you that class below.

**Option C — Full rewrite in JavaFX from scratch.**
*Bad:* throws away working, tested, already-localised code for no gain.
Not recommended.

### Recommendation: Option B

It is the lowest-risk path to the thing you were actually asked for, and
it demonstrates a real architectural idea — separating game logic from
presentation behind an interface — which is a good thing to be able to
explain at Draw Distance. If you later want save/load, Option A becomes
an easy follow-up, because Option B already forces every story beat to go
through one narrow interface.

---

## 3. Architecture

### The key idea: one interface between story and screen

```
        ┌──────────────────────────────┐
        │  Fityesz.java (story logic)  │   ← runs on the STORY thread
        │  xp, lebukás, chapters,      │
        │  boss fights, endings        │
        └──────────────┬───────────────┘
                       │ talks ONLY through
                       ▼
             ┌───────────────────┐
             │ Presenter         │   ← the interface ("port")
             │  say(), ask(),    │
             │  chapter(), ...   │
             └─────┬───────┬─────┘
                   │       │
     ┌─────────────┘       └──────────────┐
     ▼                                    ▼
┌──────────────────┐            ┌──────────────────────┐
│ ConsolePresenter │            │ FxPresenter          │ ← runs on the FX thread
│ (wraps UI.java)  │            │ (drives the window)  │
└──────────────────┘            └──────────────────────┘
```

The story logic never imports anything from JavaFX. It never knows
whether it is being drawn as ANSI escape codes or as a 1280×720 window.
That is the whole trick.

### The Presenter interface

```java
public interface Presenter {
    void title();
    void chapter(int number, String title, String quote);
    void say(String character, String text);   // dialogue
    void narrate(String text);                 // italic narration
    void plain(String text);                   // ordinary paragraph
    void item(String name);                    // item gained
    void levelUp(String rank);
    void stats(GameState state);               // HUD refresh
    void bossIntro(String name);
    void hp(String name, int current, int max, boolean isPlayer);
    void ability(String name, int damage);
    void combatEvent(String message, boolean positive);
    void defeat();
    void exposed();
    void victory(GameState state);

    int ask(String question, String... options);   // BLOCKS, returns 1..n
    String askName(String prompt);                 // BLOCKS, returns text
    void waitForContinue();                        // BLOCKS until click
}
```

Every existing `System.out.println` and `UI.xxx` call in the game maps
onto one of these. Nothing else is needed.

### The threading bridge — the one genuinely tricky class

This is where teams get stuck, so here is the working shape:

```java
public class FxPresenter implements Presenter {

    private final GameView view;                       // the JavaFX UI
    private final SynchronousQueue<Integer> answers = new SynchronousQueue<>();

    /** Called from the STORY thread. Shows the buttons, then waits. */
    @Override
    public int ask(String question, String... options) {
        // 1. hop to the FX thread to touch the UI
        Platform.runLater(() -> view.showChoices(question, options, choice -> {
            // 3. button clicked (on FX thread) -> hand the answer over
            try { answers.put(choice); } catch (InterruptedException ignored) { }
        }));
        // 2. block the STORY thread until an answer arrives
        try {
            return answers.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("game interrupted", e);
        }
    }

    @Override
    public void say(String character, String text) {
        runAndWait(() -> view.showDialogue(character, text));
    }
}
```

**Three rules. Break any one of them and the window freezes:**

1. The story thread may **never** touch a JavaFX node directly. Always
   go through `Platform.runLater`.
2. The FX thread may **never** call `answers.take()` or otherwise block.
   Blocking the FX thread *is* the freeze.
3. Start the story thread as a **daemon** thread, so closing the window
   actually exits the program:
   `Thread t = new Thread(game); t.setDaemon(true); t.start();`

### Extracting GameState

Right now `xp`, `lebukas`, `szint` and the seven item booleans are local
variables inside `jatek()`. The HUD needs to read them, so they move into
a small object:

```java
public class GameState {
    public String playerName;
    public int xp;
    public int lebukas;
    public int szint = 1;
    public final Set<String> items = new LinkedHashSet<>();   // by Lang key

    public boolean has(String itemKey) { return items.contains(itemKey); }
    public boolean exposed()           { return lebukas >= 100; }
}
```

This also cleans up `UI.gyozelem(...)`, which currently takes **seven
boolean parameters** in a fixed order — easy to get wrong, and it grows
every time you add an item. It becomes `victory(state)`.

### Proposed file layout

```
src/
  Main.java                     launcher: GUI by default, --console for the old way
  Lang.java                     UNCHANGED
  UI.java                       UNCHANGED (console rendering)

  story/
    Fityesz.java                renamed from fityesz1_0, logic only
    Presenter.java              the interface
    GameState.java              xp / lebukás / szint / items
    ChoiceLog.java              wraps valasztasok.txt writing

  console/
    ConsolePresenter.java       implements Presenter using UI.java

  fx/
    FityeszApp.java             JavaFX Application, starts the story thread
    FxPresenter.java            implements Presenter, the threading bridge
    GameView.java               main layout: background, portrait, text, choices, HUD
    CombatView.java             boss fight screen
    Assets.java                 image loading with placeholder fallback
    Typewriter.java             letter-by-letter text reveal

resources/
  style/fityesz.css
  assets/bg/*.png
  assets/portrait/*.png
  assets/item/*.png
```

`fityesz1_0` is renamed to `Fityesz` because Java classes are
conventionally `UpperCamelCase` and a version number in a class name ages
badly. Minor, but this is a portfolio piece.

---

## 4. Screen layout

```
┌────────────────────────────────────────────────────────────┐
│  ▸ 3. FEJEZET: A PARLAMENT ◂          [HU|EN]   [≡ menu]   │  header
├────────────────────────────────────────────────────────────┤
│                                                            │
│            background image for the location               │
│                                                            │
│                   ┌────────────┐                           │
│                   │            │                           │
│                   │  portrait  │  ← fades in on speaker    │
│                   │            │     change                │
│                   └────────────┘                           │
│                                                            │
│  ┌──────────────────────────────────────────────────────┐  │
│  │ LAKATOS                                              │  │  dialogue box
│  │ Szóval te vagy az új ember...▌                        │  │  (typewriter)
│  └──────────────────────────────────────────────────────┘  │
│                                                            │
│   ┌──────────────────────────────────────────────────┐     │
│   │ 1.  Elfogadom a borítékot                        │     │  choices
│   ├──────────────────────────────────────────────────┤     │
│   │ 2.  Kérdezek előbb                               │     │
│   ├──────────────────────────────────────────────────┤     │
│   │ 3.  Visszautasítom                               │     │
│   └──────────────────────────────────────────────────┘     │
├────────────────────────────────────────────────────────────┤
│  ✦ XP 120     ⚠ Lebukás ████░░░░ 35%     ◈ Szint 2        │  HUD
└────────────────────────────────────────────────────────────┘
```

Keep the existing gold/black palette from `UI.java` — it already reads as
"corrupt political thriller" and it is free continuity with your console
version. Move those colours into `fityesz.css` as variables.

The **lebukás meter should be a bar, not a number.** It is the tension
mechanic of the whole game, and watching it creep toward 100% is far more
effective than reading "35%".

### Combat screen

```
┌────────────────────────────────────────────────────────────┐
│                   ⚔  BOSS: KAPZSI  ⚔                       │
│                                                            │
│      YOU                              KAPZSI               │
│   ┌─────────┐                      ┌─────────┐             │
│   │portrait │                      │portrait │             │
│   └─────────┘                      └─────────┘             │
│   ████████████░░░  120/150         ██████░░░░░  95/170     │
│                                                            │
│           ⚡ VÁDEMELÉS!  -15 HP                             │
│                                                            │
│        ┌──────────────┐   ┌──────────────┐                 │
│        │  ⚔  TÁMADÁS  │   │  🛡  VÉDEKEZÉS│                 │
│        └──────────────┘   └──────────────┘                 │
└────────────────────────────────────────────────────────────┘
```

HP bars animate between values with a JavaFX `Timeline` rather than
snapping. Damage numbers float up and fade. The screen shakes briefly on
a big hit. These three effects cost very little code and do most of the
work of making combat feel like combat.

---

## 5. Art

Everything falls back to a generated placeholder, so **the game is fully
playable with zero image files** at every stage. Add art one file at a
time; it appears automatically.

```java
// Assets.java
public static Image portrait(String characterKey) {
    Image img = load("/assets/portrait/" + characterKey + ".png");
    return img != null ? img : placeholder(characterKey);   // coloured card + name
}
```

### Asset list, in priority order

**1. Boss portraits (3)** — most screen time, highest payoff.
`portrait/lakatos.png`, `peteri.png`, `kapzsi.png` — 400×600 PNG, transparent background.

**2. Chapter backgrounds (8)** — 1280×720 PNG.
`bg/ch1.png` … `bg/ch7.png`, plus `bg/boss3.png` for the final location.
Darken them slightly so white text stays readable over the top.

**3. Other portraits (3)** — same size as above.
`portrait/lipoti.png`, `molnar.png`, `speaker.png`.
(`npc.unknown` and `npc.you` deliberately have no portrait — the unknown
caller having no face is better storytelling than giving them one.)

**4. Item icons (7)** — 128×128 PNG.
`item/envelope1.png`, `envelopeSmall.png`, `lakatosFile.png`,
`offshore.png`, `peteriDossier.png`, `bossTrust.png`, `parliamentKey.png`.

**5. Title screen (1)** — 1280×720.

**Minimum viable: 3 images.** The three boss portraits alone transform
how the game feels. If you do nothing else, do those.

---

## 6. Build setup

JavaFX is **not** part of the JDK any more, so a plain IntelliJ module
cannot compile it. This has to be solved before anything else works.

### Recommended: migrate to Gradle

```gradle
plugins {
    id 'application'
    id 'org.openjfx.javafxplugin' version '0.1.0'
}

java {
    toolchain { languageVersion = JavaLanguageVersion.of(24) }
}

javafx {
    version = '24'
    modules = ['javafx.controls', 'javafx.media']   // media only if you add sound
}

application {
    mainClass = 'fx.FityeszApp'
}
```

Then `./gradlew run`. The plugin downloads the right JavaFX build for
each teammate's OS automatically — which matters, because JavaFX
downloads are platform-specific, and hand-configured setups reliably
break the moment someone on a different machine opens the project.

### Fallback: manual SDK

Download the JavaFX SDK, then add VM options in the IntelliJ run config:

```
--module-path /path/to/javafx-sdk-24/lib --add-modules javafx.controls
```

Workable, but every teammate must repeat it, and the path differs per
machine. Use Gradle if you can.

> **Risk flag.** This step is the single most likely thing to eat a day.
> Do it first, in isolation, before writing any game code — see Phase 0.
> If it defeats you, Swing is in the JDK and needs no setup at all, and
> every other part of this design works unchanged with Swing. That
> fallback stays open for as long as you want it.

---

## 7. Build order

Each phase ends with something that runs. Stop after any of them and you
still have a demo.

### Phase 0 — Prove JavaFX works (half a day)
Gradle set up; a blank window with the gold title opens via `./gradlew run`
on **every** teammate's machine. No game code yet.
*Done when:* everyone can open the window.

### Phase 1 — Extract the Presenter (1–2 days)
Create `Presenter`, `GameState`, `ChoiceLog`. Write `ConsolePresenter`
wrapping the existing `UI.java`. Rewrite `Fityesz.java` to call the
Presenter instead of `System.out` and `Scanner`.
**No visual change at all** — the console game must behave exactly as
before. That is the point: it proves the refactor was safe before any GUI
risk is added.
*Done when:* the console game plays identically through the new interface.

### Phase 2 — First playable window (2–3 days)
`FityeszApp`, `FxPresenter`, a bare `GameView`: text on screen, buttons
for choices, no art, no styling.
*Done when:* you can finish the whole game, all seven chapters and three
bosses, in a window.
**This is the moment it becomes a GUI game.** Everything after is polish.

### Phase 3 — Make it look good (3–5 days)
`fityesz.css` theme, backgrounds, portraits with fade transitions,
typewriter text reveal, HUD with the animated lebukás bar, `Assets.java`
with placeholder fallback.
*Done when:* it looks like the mockup in section 4.

### Phase 4 — Combat screen (2–3 days)
`CombatView`: portraits facing off, animated HP bars, floating damage
numbers, screen shake, ability call-outs.
*Done when:* all three boss fights use it.

### Phase 5 — Framing screens (1–2 days)
Title screen with HU/EN selection as buttons instead of a console prompt.
Victory screen showing collected items as icons. "Lebukás" game-over
screen. Restart without relaunching.
*Done when:* you never see a console during a full playthrough.

### Phase 6 — Optional polish
Sound effects and music, chapter fade transitions, item-gained toast
animation, settings for text speed, save/load.

---

## 8. Bugs to fix on the way through

Found while reading the current code. Worth fixing as part of the port,
and worth mentioning in your presentation — noticing them is a good look.

1. **The choices file leaks on every early exit.** `fityesz1_0.java`
   calls `if (lebukasEllenorzes(lebukas)) return;` in seven places, and
   none of them close `raf`. The file handle is never released when a
   player gets exposed. Moving this into `ChoiceLog` with try-with-resources
   fixes all seven at once.

2. **Any non-number input crashes the game.** `sc.nextInt()` throws
   `InputMismatchException` on a letter, with no validation anywhere. The
   GUI deletes this entire bug class for free, since buttons cannot be
   mistyped — a nice thing to be able to point at.

3. **The log labels are inconsistent.** `valasztasok.txt` records
   `"1. fejezet valasztas: 1"` for chapter 1 but `"2. valasztas: 2"` for
   the rest. Worth making uniform while you are moving the code.

4. **Runs append to the same file with no separator.** Playthroughs run
   together, as you can see in the current `valasztasok.txt`. Add a
   timestamped header per run.

5. **`UI.gyozelem` takes seven positional booleans.** Fragile and it
   grows with every new item. Solved by `GameState`.

---

## 9. How we will test this

The game has no tests today. It does not need many, but two things are
worth covering because they are easy to break and hard to notice:

- **`GameState` arithmetic** — XP awards, lebukás accumulation, the
  100% exposure threshold, level-up at 50 XP. Plain JUnit, no GUI.
- **`Lang` key coverage** — a test asserting the HU and EN maps have
  identical key sets, and that every key a screen asks for exists. You
  already do this check manually; making it a test means a missing
  translation fails the build instead of crashing mid-playthrough.

Everything visual is verified by playing it. Manual checklist per phase:
finish a full run in both languages, lose each boss fight once, and reach
the exposure ending.

---

## 10. Open questions

None are blocking. Each has a default below, so implementation can start
without answers; say the word if you want a different default.

- **Language switching.** *Default: title screen only*, as it is today.
  Mid-game switching is easy given `Lang.t()`, but it means every visible
  node must refresh on switch, which is fiddly. Revisit in Phase 6.
- **Skipping the typewriter animation.** *Default: Phase 3*, not Phase 6.
  A click that completes the current line instantly is about ten lines of
  code and anyone replaying the game will want it immediately.
- **Window size.** *Default: fixed 1280×720.* Far less layout work and
  normal for visual novels. Resizable is a Phase 6 item if you want it.
