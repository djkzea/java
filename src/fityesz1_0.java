import java.util.Scanner;
import java.util.Random;
import java.io.RandomAccessFile;
import java.io.IOException;

public class fityesz1_0 {
    public static boolean lebukasEllenorzes(int lebukas){
        if(lebukas >= 100){
            UI.lebukas();
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        Random rnd = new Random();

        RandomAccessFile raf = new RandomAccessFile("valasztasok.txt", "rw");

        UI.cim();
        System.out.println("Üdvözöllek a játékban!");
        System.out.println("Ez egy szöveg alapú kaland játék, amiben egy izgalmas történeten járhatsz végig.");
        System.out.println("A döntéseid mind befolyásolják a cselekmény kimenetelét. Bölcsen válassz!");
        System.out.println("A sztori során 1-3 számok közül kell választanod a folytatást.");
        UI.elvalaszto();
        UI.enter();
        sc.nextLine();


        int xp = 0;
        int lebukas = 0;
        int szint = 1;

        boolean elsoBoritek = false;
        boolean kisBoritek = false;
        boolean lakatosAktaja = false;
        boolean offshoreKod = false;
        boolean peteriDosszie = false;
        boolean fonokBizalma = false;
        boolean parlamentiKulcs = false;


        System.out.println("Prológus: A mélyPont");
        System.out.println("Aki a múltat nem ismeri, az a jövőt sem érti.");
        UI.enter();
        sc.nextLine();

        System.out.println("Add meg a neved: ");
        String nev = sc.nextLine();
        System.out.println("Te " + nev + " vagy.");
        System.out.println("Volt középiskolai tanár vagy.");
        System.out.println("A havi 180 ezres fizetésből nem jött ki a matek.");
        System.out.println("Van 3 kiflid, 380 Forintod és 4 millió forint adósságod.");
        System.out.println("A bank már keres.");
        System.out.println("Ezért politikusnak állsz.");
        UI.enter();
        sc.nextLine();

        UI.narracio("Csörög a telefonod");
        UI.parbeszed("Ismeretlen hang",
                nev + "? Hallottam a helyzetéről. Van egy ajánlatom, amit nem fog tudni visszautasítani.\n" +
                "Találkozzunk a Dohány utcai kávézóban, holnap délben. Kérdezzen Lipóti úr után.");
        UI.enter();
        sc.nextLine();

        UI.fejezet(1, "A Toborzás", "Az országért mindenkinek tenni kell, nem csak beszélni róla.");
        System.out.println("Helyszín: egy eldugott kávézó. A falon egy Kossuth portrét és egy rejtélyes grafikát látsz.");
        UI.enter();
        sc.nextLine();
        UI.parbeszed("Lipóti Dezső",
                nev + "? Lipóti Dezső vagyok, kérem, foglaljon helyet! Kávét? Pálinkát? Mindkettőt?\n" +
                "Látom a szemén, hogy az utóbbi kell.");
        UI.enter();
        sc.nextLine();
        UI.parbeszed("Lipóti Dezső",
                "Szóval tanár volt. Nemes hivatás, nemde? De tudja, mi a nemesebb? A szolgálat. A NEMZET szolgálata.\n" +
                "És mellékesen... nos, a nemzet szolgálata jól fizet.");

        UI.menu("Mit válaszolsz?",
                "Engem csak a pénz érdekel",
                "A nemzet szolgálata érdekel",
                "Mi lenne a dolgom?");

        int valasztas1 = sc.nextInt();
        sc.nextLine();

        if(valasztas1 == 1){
            xp += 15;
            lebukas += 10;
            UI.parbeszed("Lipóti Dezső", "Őszinte ember! Ezt szeretem. Ilyen emberekre van szükségünk.");
        }
        else if(valasztas1 == 2){
            xp += 10;
            lebukas += 5;
            UI.parbeszed("Lipóti Dezső", "Szép szavak! Megtanulja még a többit is.");
        }
        else{
            xp += 5;
            UI.parbeszed("Lipóti Dezső", "Óvatos! Ez jó. De a részletekbe majd később megyünk bele.");
        }
        UI.enter();
        sc.nextLine();

        if(lebukasEllenorzes(lebukas)) return;

        raf.seek(raf.length());
        raf.writeBytes("1. fejezet valasztas: " + valasztas1 + "\n");

        UI.parbeszed("Lipóti Dezső",
                "A Fityesz nem csak egy párt, " + nev + ". Család. És a családban mindenkiről gondoskodunk. Nézze csak...");
        UI.enter();
        sc.nextLine();

        UI.narracio("Lipóti kitesz az asztalra egy borítékot. Kinyitod és 500 ezer magyar forintot látsz benne. Készpénzben.");
        UI.enter();
        sc.nextLine();

        UI.parbeszed("Lipóti Dezső", "Ez csak az eleje. Előleg a jövőbeni... hűségéért. Ahogy a Miniszterelnök Úr mondta egyszer...");

        UI.menu("Elfogadod a pénzt?",
                "Elveszed és bólogatsz",
                "Elveszed, de kérdezel: \"Mi a feltétel?\"",
                "Nem nyúlsz hozzá");

        int valasztas2 = sc.nextInt();
        sc.nextLine();
        if(valasztas2 == 1){
            xp += 20;
            lebukas += 15;
            elsoBoritek = true;
            UI.targy("ELSŐ BORÍTÉK");
            UI.narracio("Lipóti elmosolyodik");
        }
        else if(valasztas2 == 2){
            xp += 10;
            lebukas += 5;
            UI.parbeszed("Lipóti Dezső", "Feltétel? Csak annyi, hogy holnap megjelensz ezen a címen.");
        }
        else{
            lebukas -= 10;
            UI.parbeszed("Lipóti Dezső", "Ó, tehát alkudni akar! Maga tényleg politikusnak született!");
        }
        UI.enter();
        sc.nextLine();

        if(lebukasEllenorzes(lebukas)) return;

        raf.seek(raf.length());
        raf.writeBytes("2. valasztas: " + valasztas2 + "\n");

        UI.fejezet(2, "Az első gyűlés", "Aki nincs ellenünk, az velünk van.");
        System.out.println("Helyszín: Egy kerületi irodaépület, piros-fehér-zöld zászlókat látsz mindenhol.");
        UI.enter();
        sc.nextLine();
        UI.narracio("A teremben körülbelül 30 ember ül műanyag székeken. A falon gigantikus Kapzs Imre-portré, alatta a felirat: \"CSAK FELFELÉ!\"");
        UI.enter();
        sc.nextLine();

        UI.parbeszed("Lakatos Ervin",
                "Na, te vagy az új! " + nev + ", ugye? Gyere, gyere! Pont jókor jöttél, ma van a havi... izé... KÖZÖSSÉGI TALÁLKOZÓ.\nIgen, úgy hívjük.");
        UI.enter();
        sc.nextLine();
        UI.narracio("A teremben az emberek borítékokat osztogatnak egymás között.");
        UI.enter();
        sc.nextLine();
        UI.parbeszed("Lakatos Ervin",
                "Figyelj, itt egyszerűek a szabályok. Egy: amit látsz, nem láttad.\n" +
                "Kettő: amit hallasz, nem hallottad. Három: amit kapsz, az jutalom a kemény munkáért.\n" +
                "Négy: minden a Főnökért. Világos?");
        UI.enter();
        sc.nextLine();

        UI.menu("Hogyan reagálsz?",
                "Teljesen világos. Hol az én borítékom?",
                "És ha valaki lebukik?",
                "Csendben maradsz és figyelsz.");

        int valasztas3 = sc.nextInt();
        sc.nextLine();
        if(valasztas3 == 1){
            xp += 25;
            lebukas += 20;
            kisBoritek = true;
            UI.targy("KIS BORÍTÉK");
            UI.parbeszed("Lakatos Ervin", "Na EZ a hozzáállás! Fiúk, szeretni fogjátok ezt az embert!");
        }
        else if(valasztas3 == 2){
            xp += 10;
            lebukas += 5;
            UI.parbeszed("Lakatos Ervin", "Lebukás? Haha! Mi vagyunk a hatalom, kisfiam!");
        }
        else{
            xp += 5;
            UI.parbeszed("Lakatos Ervin", "A néma gyereknek az anyja sem érti a szavát...");
        }
        UI.enter();
        sc.nextLine();

        if(lebukasEllenorzes(lebukas)) return;

        raf.seek(raf.length());
        raf.writeBytes("3. valasztas: " + valasztas3 + "\n");


        UI.fejezet(3, "A Kongresszusi Próba", "Ha nincs semmi gond, csinálunk valamit, hogy legyen.");
        System.out.println("Helyszín: Hatalmas rendezvénycsarnok, ezrek a nézőtéren.");
        UI.enter();
        sc.nextLine();
        System.out.println("Két hónap telt el. Szorgalmasan jártál a gyűlésekre, osztottad a szórólapokat,");
        System.out.println("és megtanultad, mikor kell tapsolni. Most előléptettek: meghívást kaptál a Kongresszusra.");
        UI.enter();
        sc.nextLine();

        UI.parbeszed("Hangszóró",
                "Tisztelt Kongresszus! Kérjük, álljanak fel és köszöntsék a Nemzet Megmentőjét,\na Haza Pajzsát, Kapzs Imre Miniszterelnök Urat!");
        UI.enter();
        sc.nextLine();
        UI.narracio("A tömeg felugrik. Te is.");
        UI.enter();
        sc.nextLine();
        UI.parbeszed("Kapzs Imre",
                "Magyarok! Testvéreim! Mi vagyunk az utolsó bástya a... a... nos, mindenki ellen, aki nem mi vagyunk!");
        UI.enter();
        sc.nextLine();
        UI.narracio("A tömeg nevet. Te is.");
        UI.enter();
        sc.nextLine();
        UI.parbeszed("Kapzs Imre",
                "Tudják, mit mondott nekem ma reggel a szakácsunk? Azt mondta: 'Miniszterelnök Úr, maga a legnagyobb!'\nÉs én mit válaszoltam? Azt mondtam: 'TUDOM!'");
        UI.enter();
        sc.nextLine();
        UI.narracio("Mennydörgő taps. Te is tapsolsz.");

        UI.narracio("Lakatos megtudta, hogy te is pályázol a választmányi helyre. Nem nézi jó szemmel a versenyt.\nKihív egy \"baráti vitára\" – ami valójában nyilvános megalázási kísérlet.");

        if(xp >= 50){
            szint = 2;
            UI.szintlepes("HELYI PÁRTTAG");
        }

        boolean boss1Verve = false;

        while(!boss1Verve){

            int playerHp = 100;
            int bossHp = 80;

            int elozo = 0;
            int ugyanaz = 0;

            UI.boss("Lakatos Ervin");
            System.out.println("  Ha 3-szor ugyanazt a képességet használod,");
            System.out.println("  Lakatos használni fogja a FELJELENTÉS képességét!");
            System.out.println();

            while(playerHp > 0 && bossHp > 0){

                UI.hpBar("Te", playerHp, 100, true);
                UI.hpBar("Lakatos", bossHp, 80, false);
                UI.menu("Taktika?", "Támadás", "Védekezés");

                int player = sc.nextInt();
                int boss = rnd.nextInt(2) + 1;

                if(player == elozo){
                    ugyanaz++;
                } else {
                    ugyanaz = 1;
                }
                elozo = player;

                if(ugyanaz == 3){
                    UI.kepesseg("FELJELENTÉS", 30);
                    playerHp -= 30;
                }

                if(player == 1){
                    if(boss == 1){
                        UI.harciEsemeny("Mindketten támadtatok!", false);
                        playerHp -= 10;
                        bossHp -= 20;
                    } else {
                        UI.harciEsemeny("Eltaláltad Lakatos Ervint! -20 HP", true);
                        bossHp -= 20;
                    }
                } else {
                    if(boss == 1){
                        UI.harciEsemeny("Kivédted Lakatos támadását!", true);
                    } else {
                        UI.harciEsemeny("Mindketten védekeztetek.", false);
                    }
                }
            }

            if(bossHp <= 0){
                boss1Verve = true;
                xp += 50;
                lakatosAktaja = true;
                UI.harciEsemeny("Legyőzted Lakatos Ervint!", true);
                UI.targy("LAKATOS AKTÁJA");
            } else {
                UI.vereseg();
                UI.menu("Folytatod?", "Újrapróbálás", "Kilépés");
                int ujra = sc.nextInt();
                if(ujra == 2){
                    System.out.println("Játék vége.");
                    raf.close(); sc.close();
                    return;
                }
            }
        }

        UI.statusz(xp, lebukas, szint);

        UI.fejezet(4, "Az Országos Választmány Árnyai", "A diplomácia nem arról szól, hogy mindig igent mondunk.");
        System.out.println("Helyszín: Elegáns irodaépület, 5. emelet, panorámás kilátás.");
        UI.enter();
        sc.nextLine();
        System.out.println("Feljutottál a Választmányba. A boríték most már vastagabb, az autó most már szolgálati.");
        System.out.println("De a játékosok is veszélyesebbek.");
        UI.enter();
        sc.nextLine();
        UI.parbeszed("Dr. Péteri Katalin",
                nev + "! Hallottam magáról. Gyorsan emelkedik. Talán túl gyorsan.\nTudja, mit mondanak a régi rókák?");
        UI.enter();
        sc.nextLine();
        UI.parbeszed("Dr. Péteri Katalin",
                "De ne aggódjon. Én segíteni akarok. Van itt egy... projekt. EU-s pályázat.\n" +
                "800 millió forint. A papírmunka már megvan, csak... kell valaki, aki aláírja. Érti?");
        UI.enter();
        sc.nextLine();

        UI.menu("Mit válaszolsz?",
                "Mennyi az én részem?",
                "Kinek a projektje ez valójában?",
                "Ezt át kell gondolnom.");

        int valasztas4 = sc.nextInt();
        sc.nextLine();
        if(valasztas4 == 1){
            xp += 30;
            lebukas += 30;
            offshoreKod = true;
            UI.targy("OFFSHORE SZÁMLA BELÉPÉSI KÓD");
            UI.parbeszed("Dr. Péteri Katalin", "10%. De ez csak az első üzlet.");
        }
        else if(valasztas4 == 2){
            xp += 15;
            lebukas += 10;
            UI.parbeszed("Dr. Péteri Katalin", "Kíváncsi kis ember... Ez tetszik. De a nevek nem fontosak.");
        }
        else{
            xp += 5;
            lebukas += 5;
            UI.parbeszed("Dr. Péteri Katalin", "Gondolja át. De ne túl sokáig. Az idő pénz – szó szerint.");
        }
        UI.enter();
        sc.nextLine();

        if(lebukasEllenorzes(lebukas)) return;

        raf.seek(raf.length());
        raf.writeBytes("4. valasztas: " + valasztas4 + "\n");


        UI.fejezet(5, "A Parlamenti Útvesztő", "Egyetlen forint közpénzt sem hagyunk kárba veszni.");
        System.out.println("Helyszín: Parlament, folyosók és titkos szobák.");
        UI.enter();
        sc.nextLine();
        System.out.println("Most már parlamenti képviselő vagy. Van irodád, van asszisztensed,");
        System.out.println("és van egy hosszú lista arról, hogy kinek mivel tartozol.");
        UI.enter();
        sc.nextLine();

        UI.parbeszed("Molnár Gábor",
                "Gratulálok a mandátumhoz, " + nev + "! Most már az elit része vagy.\n" +
                "Na, nem az igazi elité, ahhoz még messze vagy. De a kapuban állsz.");
        UI.enter();
        sc.nextLine();
        UI.narracio("Molnár átad egy borítékot.");
        UI.enter();
        sc.nextLine();
        UI.parbeszed("Molnár Gábor",
                "Ez a holnapi szavazás. Az EU-kritikus állásfoglalás. Te MELLETTE szavazol.\n" +
                "Nem ELLENE, nem TARTÓZKODSZ. MELLETTE. Világos?");
        UI.enter();
        sc.nextLine();

        UI.menu("Mit reagálsz?",
                "Persze. Hol kell aláírni?",
                "És ha esetleg a saját véleményem más?",
                "Előbb elolvasom a javaslatot.");

        int valasztas5 = sc.nextInt();
        sc.nextLine();
        if(valasztas5 == 1){
            xp += 20;
            lebukas += 15;
            UI.parbeszed("Molnár Gábor", "Látom, gyorsan tanul. Ez jó.");
        }
        else if(valasztas5 == 2){
            xp += 10;
            lebukas += 20;
            UI.parbeszed("Molnár Gábor", "Vélemény? VÉLEMÉNY?! " + nev + ", maga vicces ember.");
        }
        else{
            xp += 5;
            UI.parbeszed("Molnár Gábor", "Olvasás? Hm. Furcsa szokás. De legyen.");
        }
        UI.enter();
        sc.nextLine();

        if(lebukasEllenorzes(lebukas)) return;

        raf.seek(raf.length());
        raf.writeBytes("5. valasztas: " + valasztas5 + "\n");

        System.out.println("Megtudtad, hogy Dr. Péteri az előléptetésedért cserébe 50%-os részesedést");
        System.out.println("akar minden jövőbeli üzletedből. Ideje tisztázni a viszonyokat.");
        System.out.println();

        UI.boss("Dr. Péteri Katalin");
        System.out.println("  Ha nem támadsz, 2 körönként -20 HP sebzést fog okozni");
        System.out.println("  Péteri MÉDIABOTRÁNY képessége!");
        System.out.println();

        boolean boss2Verve = false;

        while(!boss2Verve){

            int playerHp = 100;
            int bossHp = 80;
            int kor = 0;

            while(playerHp > 0 && bossHp > 0){

                kor++;

                UI.hpBar("Te", playerHp, 100, true);
                UI.hpBar("Dr. Péteri", bossHp, 80, false);
                UI.menu("Taktika?", "Támadás", "Védekezés");

                int player = sc.nextInt();
                int boss = rnd.nextInt(2) + 1;

                if(kor % 2 == 0 && player != 1){
                    UI.kepesseg("MÉDIABOTRÁNY", 20);
                    playerHp -= 20;
                }

                if(player == 1 && boss == 2){
                    UI.harciEsemeny("Péteri kivédte.", false);
                } else if(player == 1 && boss == 1){
                    UI.harciEsemeny("Mindketten támadtatok!", false);
                    playerHp -= 10;
                    bossHp -= 10;
                } else if(player == 2 && boss == 1){
                    UI.harciEsemeny("Péteri megütött! -20 HP", false);
                    playerHp -= 20;
                } else {
                    UI.harciEsemeny("Mindketten védekeztetek.", false);
                }

                if(player == 1 && boss != 2){
                    bossHp -= 25;
                }
            }

            if(playerHp <= 0){
                UI.vereseg();
                UI.menu("Folytatod?", "Újra", "Kilépés");
                int ujra = sc.nextInt();
                if(ujra == 2){
                    raf.close(); sc.close();
                    return;
                }
            } else {
                boss2Verve = true;
                xp += 80;
                peteriDosszie = true;
                UI.harciEsemeny("Legyőzted Dr. Péterit!", true);
                UI.targy("PÉTERI DOSSZIÉ");
            }
        }

        UI.statusz(xp, lebukas, szint);

        UI.fejezet(6, "Az Országos Elnökség Kapujában", "A demokratikus jogrend nem ad felmentést a felelősség alól.");
        System.out.println("Helyszín: Elit vadászkastély, éjszaka, szivarfüst.");
        UI.enter();
        sc.nextLine();
        System.out.println("Meghívást kaptál a \"belső körbe\". Ahova nem mindenki juthat be.");
        System.out.println("A szobában a párt legbefolyásosabb emberei ülnek bőrfotelekben.");
        UI.enter();
        sc.nextLine();
        UI.parbeszed("Kapzs Imre",
                "Szóval te vagy " + nev + ". Hallottam rólad. Gyorsan mászol. Ez jó.\n" +
                "De tudod, mi a különbség a mászó és a csillag között?");
        UI.enter();
        sc.nextLine();
        UI.narracio("Mindenki téged néz.");
        UI.enter();
        sc.nextLine();
        UI.parbeszed("Kapzs Imre",
                "A mászó bármikor leeshet. A csillag... a csillag örökké ragyog.\n" +
                "Na persze, amíg MI ragyogtatjuk.");
        UI.enter();
        sc.nextLine();
        UI.narracio("Mindenki nevet.");
        UI.enter();
        sc.nextLine();
        UI.parbeszed("Kapzs Imre",
                "Figyelj, " + nev + ". Van egy kis... probléma.\n" +
                "Az ellenzék túl hangos lett. Szükségünk van valakire, aki... nos, aki megoldja. Kreatívan. Érted, ugye?");
        UI.enter();
        sc.nextLine();

        UI.menu("Mit válaszolsz?",
                "Bármit, Főnök. Csak mondja, mit.",
                "Milyen jellegű 'megoldásra' gondol?",
                "*Meghajolsz, de nem szólsz semmit*");

        int valasztas6 = sc.nextInt();
        sc.nextLine();
        if(valasztas6 == 1){
            xp += 50;
            lebukas += 40;
            fonokBizalma = true;
            UI.targy("FŐNÖK BIZALMA");
            UI.parbeszed("Kapzs Imre", "NA EZ AZ! Ilyen embereket akarok! Gyere ide, ülj mellém!");
        }
        else if(valasztas6 == 2){
            xp += 25;
            lebukas += 15;
            UI.parbeszed("Kapzs Imre", "Óvatos! Ez bölcs. De néha a bölcsesség lassít.");
        }
        else{
            xp += 10;
            lebukas += 5;
            UI.parbeszed("Kapzs Imre", "Hm. A néma kutya sosem harap. De nem is véd.");
        }
        UI.enter();
        sc.nextLine();

        if(lebukasEllenorzes(lebukas)) return;

        raf.seek(raf.length());
        raf.writeBytes("6. valasztas: " + valasztas6 + "\n");

        UI.parbeszed("Kapzs Imre", "Rendben. A részletekről később beszélhetünk.");
        UI.enter();
        sc.nextLine();

        UI.fejezet(7, "Az Elnökség Trónján", "A törvény mindenkire egyformán vonatkozik.");
        System.out.println("Helyszín: A párt központja, az elnökségi terem.");
        UI.enter();
        sc.nextLine();
        UI.narracio("Most már az Országos Elnökség tagja vagy. A hatalom szaga érezhető – és kissé rothadt.\nDe te már hozzászoktál.");
        UI.enter();
        sc.nextLine();

        UI.parbeszed("Kapzs Imre", nev + "! Gyere, sétáljunk. Van valami, amit csak neked mondok el.");
        UI.enter();
        sc.nextLine();
        System.out.println("Kimentek a kertbe. Senki más nincs ott.");
        UI.enter();
        sc.nextLine();
        UI.parbeszed("Kapzs Imre",
                "Tudod, én nem leszek örökké. Nem, nem, ne tiltakozz.\n" +
                "Mindenki halandó. És nekem... nos, nekem szükségem van valakire, aki folytatja.\n" +
                "Aki érti, hogyan működik ez az egész.");
        UI.enter();
        sc.nextLine();
        UI.narracio("Kapzs Imre megáll és rádnéz.");
        UI.enter();
        sc.nextLine();
        UI.parbeszed("Kapzs Imre",
                "Te lehetnél az, " + nev + ". De előbb... előbb bizonyítanod kell.\nVan egy utolsó teszt.");
        UI.enter();
        sc.nextLine();

        UI.menu("Mit válaszolsz?",
                "Bármi lesz, kész vagyok rá.",
                "Mi lenne ez a teszt?",
                "Talán még nem vagyok készen...");

        int valasztas7 = sc.nextInt();
        sc.nextLine();
        if(valasztas7 == 1){
            UI.parbeszed("Kapzs Imre", "Bármi? Bármi egy veszélyes szó, kisfiam. De legyen.");
        }
        else if(valasztas7 == 2){
            UI.parbeszed("Kapzs Imre", "Az egyetlen teszt, ami számít: le kell győznöd engem.");
        }
        else{
            UI.parbeszed("Kapzs Imre", "Hm. Az önismeret erény. De a gyávaság nem.");
        }
        UI.enter();
        sc.nextLine();

        if(lebukasEllenorzes(lebukas)) return;

        raf.seek(raf.length());
        raf.writeBytes("7. valasztas: " + valasztas7 + "\n");

        System.out.println("Helyszín: A párt titkos tanácsterme, éjfél.");
        UI.enter();
        sc.nextLine();
        UI.narracio("Ez az a pillanat, amire minden eddig történt felkészített.\n" +
                "Kapzs tudja, hogy te vagy az egyetlen, aki fenyegetést jelenthet rá.\n" +
                "És te is tudod: csak egyikőtök maradhat a csúcson.");

        UI.boss("Kapzs Imre");
        System.out.println("  KÜLÖNLEGES KÉPESSÉGEK:");
        System.out.println("  Média Manipuláció  — minden 2. körben -15 HP neked");
        System.out.println("  Hűséges Talpnyalók — 3 körönként +50 HP magának");
        System.out.println("  Végső Szónoklat    — HP alatt 50: dupla sebzés");
        System.out.println();
        if(fonokBizalma){
            System.out.println("  ★ FŐNÖK BIZALMA aktív — Kapzs támadásai 30%-kal gyengébbek!");
        }
        System.out.println();

        boolean vegsoBoss = false;

        while(!vegsoBoss){

            int playerHp = 150;
            int bossHp = 170;
            int kor = 0;

            while(playerHp > 0 && bossHp > 0){

                kor++;

                UI.hpBar("Te", playerHp, 150, true);
                UI.hpBar("Kapzs Imre", bossHp, 170, false);
                UI.menu("Taktika?", "Támadás", "Védekezés");

                int player = sc.nextInt();
                int boss = rnd.nextInt(2) + 1;

                if(kor % 2 == 0){
                    int sebzes = fonokBizalma ? 10 : 15;
                    UI.kepesseg("MÉDIA MANIPULÁCIÓ", sebzes);
                    playerHp -= sebzes;
                }

                if(kor % 3 == 0){
                    bossHp = Math.min(bossHp + 50, 300);
                    UI.harciEsemeny("Kapzs gyógyult! +50 HP", false);
                }

                if(player == 1 && boss == 2){
                    UI.harciEsemeny("Kapzs kivédte.", false);
                    int sebzes = (bossHp <= 50) ? 20 : 35;
                    bossHp -= sebzes;
                } else if(player == 2 && boss == 1){
                    int sebzes = (bossHp <= 50) ? 60 : 30;
                    UI.harciEsemeny("Kapzs megütött! -" + sebzes + " HP", false);
                    playerHp -= sebzes;
                } else if(player == 1 && boss == 1){
                    UI.harciEsemeny("Mindketten támadtatok!", false);
                    playerHp -= 10;
                    bossHp -= 10;
                } else {
                    UI.harciEsemeny("Mindketten védekeztetek.", false);
                }
            }

            if(playerHp <= 0){
                UI.vereseg();
                UI.menu("Folytatod?", "Újra", "Kilépés");
                int ujra = sc.nextInt();
                if(ujra == 2){
                    raf.close(); sc.close();
                    return;
                }
            } else {
                vegsoBoss = true;
                xp += 120;
                parlamentiKulcs = true;
            }
        }

        UI.narracio("Kapzs Imre \"egészségügyi okokból\" visszavonult. A párt új elnöke: " + nev + ". Te.");
        UI.enter();
        sc.nextLine();
        System.out.println("A tükör előtt állsz, az elnöki irodában. Az ablakon túl Budapest fényei.");
        UI.enter();
        sc.nextLine();
        UI.parbeszed("Te", "4 millió forint adósság. Ezzel kezdődött. És most... most itt vagyok.");
        UI.enter();
        sc.nextLine();
        UI.narracio("Valaki kopog.");
        UI.enter();
        sc.nextLine();
        UI.parbeszed("Ismeretlen hang",
                "Elnök úr? Van itt egy fiatal srác. Kovács Péter a neve.\nAzt mondja, tartozik a banknak, és... nos, bármire hajlandó.");
        UI.enter();
        sc.nextLine();

        if(lebukas < 100){
            UI.gyozelem(nev, xp, lebukas,
                    elsoBoritek, kisBoritek, lakatosAktaja,
                    offshoreKod, peteriDosszie, fonokBizalma, parlamentiKulcs);
        }

        raf.close();
        sc.close();
    }
}