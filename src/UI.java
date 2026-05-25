public class UI {

    private static final String RESET  = "\033[0m";
    private static final String BOLD   = "\033[1m";
    private static final String ITALIC = "\033[3m";
    private static final String GOLD   = "\033[33m";
    private static final String YELLOW = "\033[93m";
    private static final String RED    = "\033[31m";
    private static final String GREEN  = "\033[32m";
    private static final String CYAN   = "\033[36m";
    private static final String GRAY   = "\033[90m";
    private static final String WHITE  = "\033[97m";
    private static final String CREAM  = "\033[37m";

    public static void cim() {
        System.out.println();
        System.out.println(GOLD + "════════════════════════════════════════" + RESET);
        System.out.println(BOLD + GOLD + "       ✦  FITYESZ KRÓNIKA  ✦" + RESET);
        System.out.println(GOLD + "════════════════════════════════════════" + RESET);
        System.out.println();
    }

    public static void fejezet(int szam, String cim, String idezet) {
        System.out.println();
        System.out.println(GRAY + "────────────────────────────────────────" + RESET);
        System.out.println(BOLD + GOLD + "  ▸ " + szam + ". FEJEZET: " + cim.toUpperCase() + " ◂" + RESET);
        if (idezet != null && !idezet.isEmpty()) {
            System.out.println(GRAY + ITALIC + "  \"" + idezet + "\"" + RESET);
        }
        System.out.println(GRAY + "────────────────────────────────────────" + RESET);
        System.out.println();
    }

    public static void parbeszed(String karakter, String szoveg) {
        System.out.println(BOLD + GOLD + "[ " + karakter.toUpperCase() + " ]" + RESET);
        for (String sor : szoveg.split("\n")) {
            System.out.println("  " + CREAM + sor + RESET);
        }
        System.out.println();
    }

    public static void narracio(String szoveg) {
        System.out.println(GRAY + ITALIC + "  * " + szoveg + " *" + RESET);
        System.out.println();
    }

    public static void menu(String kerdes, String... opciok) {
        System.out.println();
        int szelesseg = 38;
        System.out.println(CREAM + "┌─ " + kerdes + " " + "─".repeat(Math.max(1, szelesseg - kerdes.length() - 2)) + "┐" + RESET);
        for (int i = 0; i < opciok.length; i++) {
            String sor = "  " + BOLD + YELLOW + (i + 1) + ". " + RESET + YELLOW + opciok[i];
            System.out.println(CREAM + "│" + RESET + sor + RESET);
        }
        System.out.println(CREAM + "└─── Válassz (" + 1 + "-" + opciok.length + "): " + "─".repeat(Math.max(1, szelesseg - 14 - String.valueOf(opciok.length).length())) + "┘" + RESET);
        System.out.print(GOLD + "> " + RESET);
    }

    public static void hpBar(String nev, int jelenlegi, int max, boolean jatekos) {
        int barSzelesseg = 12;
        int teli = (int) Math.round((double) jelenlegi / max * barSzelesseg);
        teli = Math.max(0, Math.min(teli, barSzelesseg));
        int ures = barSzelesseg - teli;

        String szinTeli = jatekos ? GREEN : RED;
        String nevSzin  = jatekos ? CYAN  : "\033[91m";

        String bar = szinTeli + BOLD + "█".repeat(teli) + RESET + GRAY + "░".repeat(ures) + RESET;
        System.out.printf("  %s%-12s%s %s %s%d/%d%s%n",
                nevSzin, nev, RESET, bar, WHITE, Math.max(0, jelenlegi), max, RESET);
    }

    public static void boss(String nev) {
        System.out.println();
        System.out.println(RED + BOLD + "  ⚔  BOSSFIGHT: " + nev.toUpperCase() + "  ⚔" + RESET);
        System.out.println(GRAY + "────────────────────────────────────────" + RESET);
    }

    public static void kepesseg(String nev, int sebzes) {
        System.out.println(RED + BOLD + "  ⚡ " + nev.toUpperCase() + "!" + RESET
                + (sebzes > 0 ? RED + "  -" + sebzes + " HP" + RESET : ""));
    }

    public static void harciEsemeny(String uzenet, boolean pozitiv) {
        String szin = pozitiv ? GREEN : RED;
        System.out.println(szin + "  " + uzenet + RESET);
    }

    public static void statusz(int xp, int lebukas, int szint) {
        System.out.println();
        System.out.println(GRAY + "┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄" + RESET);
        System.out.println("  " + GOLD + "✦ XP: " + xp + RESET
                + "   " + RED + "⚠ LEBUKÁS: " + lebukas + "%" + RESET
                + "   " + GRAY + "◈ Szint: " + szint + RESET);
        System.out.println(GRAY + "┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄" + RESET);
        System.out.println();
    }

    public static void targy(String nev) {
        System.out.println();
        System.out.println(GOLD + "  ┌──────────────────────────────────┐" + RESET);
        System.out.println(GOLD + "  │  ★ MEGSZERZETT TÁRGY: " + nev + RESET);
        System.out.println(GOLD + "  └──────────────────────────────────┘" + RESET);
        System.out.println();
    }

    public static void szintlepes(String rang) {
        System.out.println();
        System.out.println(BOLD + YELLOW + "  ╔══════════════════════════════════╗" + RESET);
        System.out.println(BOLD + YELLOW + "  ║       ✦  SZINTLÉPÉS!  ✦         ║" + RESET);
        System.out.println(BOLD + YELLOW + "  ║  Új rang: " + rang + RESET);
        System.out.println(BOLD + YELLOW + "  ╚══════════════════════════════════╝" + RESET);
        System.out.println();
    }

    public static void gyozelem(String nev, int xp, int lebukas,
                                boolean boritek1, boolean boritekKis, boolean lakatosAktaja,
                                boolean offshoreKod, boolean peteriDosszie,
                                boolean fonokBizalma, boolean parlamentiKulcs) {
        System.out.println();
        System.out.println(GOLD + "════════════════════════════════════════" + RESET);
        System.out.println(BOLD + GOLD + "   ✦  GRATULÁLUNK, " + nev.toUpperCase() + "!  ✦" + RESET);
        System.out.println(BOLD + GOLD + "      TE LETTÉL A PÁRTELNÖK!" + RESET);
        System.out.println(GOLD + "════════════════════════════════════════" + RESET);
        System.out.println();
        System.out.println(GOLD + "  Végső XP:       " + WHITE + xp + RESET);
        System.out.println(GOLD + "  Lebukásmérő:    " + WHITE + lebukas + "%" + RESET);
        System.out.println();
        System.out.println(GOLD + "  Megszerzett tárgyak:" + RESET);
        if (boritek1)       System.out.println(YELLOW + "    - Első Boríték" + RESET);
        if (boritekKis)     System.out.println(YELLOW + "    - Kis Boríték" + RESET);
        if (lakatosAktaja)  System.out.println(YELLOW + "    - Lakatos Aktája" + RESET);
        if (offshoreKod)    System.out.println(YELLOW + "    - Offshore Kód" + RESET);
        if (peteriDosszie)  System.out.println(YELLOW + "    - Péteri Dosszié" + RESET);
        if (fonokBizalma)   System.out.println(YELLOW + "    - Főnök Bizalma" + RESET);
        if (parlamentiKulcs) System.out.println(YELLOW + "    - Parlamenti Többség Kulcsa" + RESET);
        System.out.println();
        System.out.println(GOLD + "════════════════════════════════════════" + RESET);
    }

    public static void vereseg() {
        System.out.println();
        System.out.println(RED + BOLD + "  ╔══════════════════════════════════╗" + RESET);
        System.out.println(RED + BOLD + "  ║         ✖  VESZTETTÉL!  ✖       ║" + RESET);
        System.out.println(RED + BOLD + "  ╚══════════════════════════════════╝" + RESET);
        System.out.println();
    }

    public static void lebukas() {
        System.out.println();
        System.out.println(RED + BOLD + "════════════════════════════════════════" + RESET);
        System.out.println(RED + BOLD + "   ✖  LEBUKTÁL!" + RESET);
        System.out.println(RED + "   A sajtó mindent kiderített." + RESET);
        System.out.println(RED + BOLD + "════════════════════════════════════════" + RESET);
        System.out.println();
    }

    public static void enter() {
        System.out.println(GRAY + "  [ Nyomj Enter-t a folytatáshoz... ]" + RESET);
    }

    public static void elvalaszto() {
        System.out.println(GRAY + "────────────────────────────────────────" + RESET);
    }
}