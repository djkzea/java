import java.util.Scanner;
import java.util.Random;
import java.io.RandomAccessFile;
import java.io.IOException;

public class fityesz1_0 {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        Random rnd = new Random();

        RandomAccessFile raf = new RandomAccessFile("valasztasok.txt", "rw");

        Scanner input = new Scanner(System.in);

        System.out.println("Üdvözöllek a játékban!");
        System.out.println("Ez egy szöveg alapú kaland játék, amiben egy izgalmas történeten járhatsz végig. Emellett a döntésed mind befolyásolják a cselekmény kimenetelét. Bölcsen válassz, hogy jó úton járj.");
        System.out.println("A sztori során a kimeneteleket a CLI alján találod majd és 1-2-3-4 számok közül kell választanod a neked legjobban tetsző folytatást, kimenetelt a jelenleg előállt helyzetnek.");
        System.out.println("--------------------------------------");
        System.out.println("Nyomj Enter-t a folytatáshoz...");
        input.nextLine();


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


        System.out.println("Prológus: A méylpont");
        System.out.println("idézet");
        input.nextLine();

        System.out.println("Add meg a neved: ");
        String nev = input.nextLine();
        System.out.println("Te " + nev + " vagy");
        System.out.println("Volt középiskolai tanár vagy. A havi 180 ezres fizetésből nem jött ki a matek. Van 3 kiflid, 380 Forintod és 4 millió forint adósságod.");
        System.out.println("A bank már keres.");
        System.out.println("Ezért politikusnak állsz.\n");


        System.out.println("*Csörög a telefonod*");
        input.nextLine();
        System.out.println("-Ismeretlen hang-" + "\n" + nev +" ? Hallottam a helyzetéről. Van egy ajánlatom, amit nem fog tudni visszautasítani. Találkozzunk a Dohány utcai kávézóban, holnap délben. Kérdezzen Lipóti úr után.");
        input.nextLine();

        System.out.println("1. Fejezet: A Toborzás");
        System.out.println("idézet");
        input.nextLine();
        System.out.println("Helyszín: egy eldugott kávézó. A falon egy Kossuth portrét és egy rejtélyes grafikát látsz.");
        input.nextLine();
        System.out.println("-Ismeretlen-\n" + nev + " ? Lipóti Dezső vagyok, kérem, foglaljon helyet! Kávét? Pálinkát? Mindkettőt? Látom a szemén, hogy az utóbbi kell.");
        input.nextLine();
        System.out.println("-Lipóti Dezső-\n");
        System.out.println("Szóval tanár volt. Nemes hivatás, nemde? De tudja, mi a nemesebb? A szolgálat. A NEMZET szolgálata. És mellékesen... nos, a nemzet szolgálata jól fizet.");

        System.out.println("Mit válaszolsz?");
        System.out.println("\n1 - Engem csak a pénz érdekel");
        System.out.println("2 - A nemzet szolgálata érdekel");
        System.out.println("3 - Mi lenne a dolgom?");

        int valasztas1 = sc.nextInt();

        if(valasztas1 == 1){
            xp += 15;
            lebukas += 10;
            System.out.println("-Lipóti Dezső-\nŐszinte ember! Ezt szeretem. Ilyen emberekre van szükségünk.");
            input.nextLine();
        }
        else if(valasztas1 == 2){
            xp += 10;
            lebukas += 5;
            System.out.println("-Lipóti Dezső-\nSzép szavak! Megtanulja még a többit is.");
            input.nextLine();
        }
        else{
            xp += 5;
            System.out.println("-Lipóti Dezső-\nÓvatos! Ez jó. De a részletekbe majd később megyünk bele.");
            input.nextLine();

        }

        raf.seek(raf.length());
        raf.writeBytes("1. fejezet valasztas: " + valasztas1 + "\n");

        System.out.println("-Lipóti Dezső-\nA Fityesz nem csak egy párt, " + nev +". Család. És a családban mindenkiről gondoskodunk. Nézze csak...");

        input.nextLine();

        System.out.println("*Lipóti kitesz az asztalra egy borítékot. Kinyitod és 500 ezer magyar forintot látsz benne. Készpénzben.*");
        input.nextLine();

        System.out.println("-Lipóti Dezső-\nEz csak az eleje. Előleg a jövőbeni... hűségéért. Ahogy a Miniszterelnök Úr mondta egyszer...");


        System.out.println("Elfogadod a pénzt?");
        System.out.println("1 - Elveszed és bólogatsz");
        System.out.println("2 - Elveszed, de kérdezel: \"Mi a feltétel?\"");
        System.out.println("3 - Nem nyúlsz hozzá");

        int valasztas2 = sc.nextInt();

        if(valasztas2 == 1){
            xp += 20;
            lebukas += 15;
            elsoBoritek = true;
            System.out.println("MEGSZERZETT TÁRGY: ELSŐ BORÍTÉK\n*Lipóti elmosolyodik*");
            input.nextLine();
        }
        else if(valasztas2 == 2){
            xp += 10;
            lebukas += 5;
            System.out.println("-Lipóti Dezső-\nFeltétel? Csak annyi, hogy holnap megjelensz ezen a címen");
            input.nextLine();
        }
        else{
            lebukas -= 10;
            System.out.println("-Lipóti Dezső-\nÓ, tehát alkudni akar! Maga tényleg politikusnak született!");
            input.nextLine();
        }

        raf.writeBytes("2. valasztas: " + valasztas2 + "\n");

        System.out.println("2. fejezet: Az első gyűlés");
        System.out.println("idézet");
        input.nextLine();
        System.out.println("Helyszín: Egy kerületi irodaépület, piros-fehér-zöld zászlókat látsz mindenhol.");
        input.nextLine();
        System.out.println("*A teremben körülbelül 30 ember ül műanyag székeken. A falon gigantikus Kapzs Imre-portré, alatta a felirat: \"CSAK FELFELÉ!\"*");
        input.nextLine();

        System.out.println("-Lakatos Ervin-\nNa, te vagy az új! " + nev+ ", ugye? Gyere, gyere! Pont jókor jöttél, ma van a havi... izé... KÖZÖSSÉGI TALÁLKOZÓ. Igen, úgy hívjuk.");
        input.nextLine();
        System.out.println("*A teremben az emberek borítékokat osztogatnak egymás között.*");
        input.nextLine();
        System.out.println("-Lakatos Ervin-\nFigyelj, itt egyszerűek a szabályok. Egy: amit látsz, nem láttad. Kettő: amit hallasz, nem hallottad. Három: amit kapsz, az jutalom a kemény munkáért. Négy: minden a Főnökért. Világos?");
        input.nextLine();

        System.out.println("Hogyan reagálsz?");
        System.out.println("\n1 - Teljesen világos. Hol az én borítékom?");
        System.out.println("2 - És ha valaki lebukik?");
        System.out.println("3 - Csendben maradsz és figyelsz.");

        int valasztas3 = sc.nextInt();

        if(valasztas3 == 1){
            xp += 25;
            lebukas += 20;
            kisBoritek = true;
            System.out.println("MEGSZERZETT TÁRGY: KIS BORÍTÉK");
            System.out.println("-Lakatos Ervin-\nNa EZ a hozzáállás! Fiúk, szeretni fogjátok ezt az embert!");
            input.nextLine();
        }
        else if(valasztas3 == 2){
            xp += 10;
            lebukas += 5;
            System.out.println("-Lakatos Ervin-\nLebukás? Haha! Mi vagyunk a hatalom, kisfiam!");
            input.nextLine();
        }
        else{
            xp += 5;
            System.out.println("-Lakatos Ervin-\n*Lakatos néz egy darabig* \nA néma gyereknek az anyja sem érti a szavát...");
            input.nextLine();
        }

        raf.writeBytes("3. valasztas: " + valasztas3 + "\n");


        System.out.println("3. fejezet: A Kongresszusi Próba");
        System.out.println("idézet");
        input.nextLine();
        System.out.println("Helyszín: Hatalmas rendezvénycsarnok, ezrek a nézőtéren");
        input.nextLine();
        System.out.println("Két hónap telt el. Szorgalmasan jártál a gyűlésekre, osztottad a szórólapokat, és megtanultad, mikor kell tapsolni. Most előléptettek: meghívást kaptál a Kongresszusra.");
        input.nextLine();

        System.out.println("-Hangszóró-\nTisztelt Kongresszus! Kérjük, álljanak fel és köszöntsék a Nemzet Megmentőjét, a Haza Pajzsát, Kapzs Imre Miniszterelnök Urat!");
        input.nextLine();
        System.out.println("*A tömeg felugrik. Te is.*");
        input.nextLine();
        System.out.println("-Kapzs Imre-\nMagyarok! Testvéreim! Mi vagyunk az utolsó bástya a... a... nos, mindenki ellen, aki nem mi vagyunk!");
        input.nextLine();
        System.out.println("*A tömeg nevet. Te is.*");
        input.nextLine();
        System.out.println("-Kapzs Imre-\nTudják, mit mondott nekem ma reggel a szakácsunk? Azt mondta: 'Miniszterelnök Úr, maga a legnagyobb!' És én mit válaszoltam? Azt mondtam: 'TUDOM!'");
        input.nextLine();
        System.out.println("*Mennydörgő taps. Te is tapsolsz.*");

        System.out.println("idézet");
        input.nextLine();
        System.out.println("*Lakatos megtudta, hogy te is pályázol a választmányi helyre. Nem nézi jó szemmel a versenyt. Kihív egy \"baráti vitára\" – ami valójában nyilvános megalázási kísérlet.*");

        if(xp >= 50){
            szint = 2;
            System.out.println("\nSZINTLÉPÉS!");
            System.out.println("Új rang: HELYI PÁRTTAG");
        }

        boolean boss1Verve = false;

        while(!boss1Verve){

            int playerHp = 100;
            int bossHp = 80;

            int elozo = 0;
            int ugyanaz = 0;

            System.out.println("\nBOSSFIGHT: Lakatos Ervin");
            System.out.println("Ha 3-szor ugyanazt a képességet használod, Lakatos használni fogja a FELJELENTÉS képességét!");

            while(playerHp > 0 && bossHp > 0){

                System.out.println("\nTe HP: " + playerHp);
                System.out.println("Lakatos HP: " + bossHp);

                System.out.println("1 - Támadás");
                System.out.println("2 - Védekezés");

                int player = sc.nextInt();
                int boss = rnd.nextInt(2) + 1;

                if(player == elozo){
                    ugyanaz++;
                }
                else{
                    ugyanaz = 1;
                }

                elozo = player;

                if(ugyanaz == 3){
                    System.out.println("Lakatos FELJELENTÉS képességet használt!");
                    playerHp -= 30;
                }

                if(player == 1){
                    if(boss == 1){
                        System.out.println("Mindketten támadtatok!");
                        playerHp -= 10;
                        bossHp -= 20;
                    }
                    else{
                        System.out.println("Eltaláltad Lakatos Ervint!");
                        bossHp -= 20;
                    }
                }
                else{
                    if(boss == 1){
                        System.out.println("Kivédted Lakatos támadását!");
                    }
                    else{
                        System.out.println("Mindketten védekeztetek.");
                    }
                }
            }


            if(bossHp <= 0){
                boss1Verve = true;
                xp += 50;
                lakatosAktaja = true;

                System.out.println("\nLegyőzted Lakatos Ervint!");
            }
            else{
                System.out.println("\nVESZTETTÉL!");
                System.out.println("1 - Újrapróbálás");
                System.out.println("2 - Kilépés");

                int ujra = sc.nextInt();

                if(ujra == 2){
                    System.out.println("Játék vége.");
                    return;
                }
            }
        }





        System.out.println("4. fejezet: Az Országos Választmány Árnyai");
        System.out.println("idézet");
        input.nextLine();
        System.out.println("Helyszín: Elegáns irodaépület, 5. emelet, panorámás kilátás");
        input.nextLine();
        System.out.println("Feljutottál a Választmányba. A boríték most már vastagabb, az autó most már szolgálati. De a játékosok is veszélyesebbek.");
        input.nextLine();
        System.out.println("-Dr. Péteri Katalin-\n"+ nev+ " ! Hallottam magáról. Gyorsan emelkedik. Talán túl gyorsan. Tudja, mit mondanak a régi rókák?");
        input.nextLine();
        System.out.println("-Dr. Péteri Katalin-\nDe ne aggódjon. Én segíteni akarok. Van itt egy... projekt. EU-s pályázat. 800 millió forint. A papírmunka már megvan, csak... kell valaki, aki aláírja. Érti?");
        input.nextLine();


        System.out.println("Mit válaszolsz?");
        System.out.println("\n1 - Mennyi az én részem?");
        System.out.println("2 - Kinek a projektje ez valójában?");
        System.out.println("3 - Ezt át kell gondolnom.");

        int valasztas4 = sc.nextInt();

        if(valasztas4 == 1){
            xp += 30;
            lebukas += 30;
            offshoreKod = true;
            System.out.println("MEGSZERZETT TÁRGY: OFFSHORE SZÁMLA BELÉPÉSI KÓD");
            System.out.println("-Dr. Péteri Katalin-\n10%. De ez csak az első üzlet.");
            input.nextLine();
        }
        else if(valasztas4 == 2){
            xp += 15;
            lebukas += 10;
            System.out.println("-Dr. Péteri Katalin-\nKíváncsi kis ember... Ez tetszik. De a nevek nem fontosak.");
            input.nextLine();
        }
        else{
            xp += 5;
            lebukas += 5;
            System.out.println("-Dr. Péteri Katalin-\nGondolja át. De ne túl sokáig. Az idő pénz – szó szerint.");
            input.nextLine();
        }

        raf.writeBytes("4. valasztas: " + valasztas4 + "\n");


        System.out.println("5. fejezet: A Parlamenti Útvesztő");
        System.out.println("idézet");
        input.nextLine();
        System.out.println("Helyszín: Parlament, folyosók és titkos szobák");
        input.nextLine();
        System.out.println("Most már parlamenti képviselő vagy. Van irodád, van asszisztensed, és van egy hosszú lista arról, hogy kinek mivel tartozol.");
        input.nextLine();

        System.out.println("-Molnár Gábor-\nGratulálok a mandátumhoz, " + nev + "! Most már az elit része vagy. Na, nem az igazi elité, ahhoz még messze vagy. De a kapuban állsz.");
        input.nextLine();
        System.out.println("*Molnár átad egy borítékot*");
        input.nextLine();
        System.out.println("-Molnár Gábor-\nEz a holnapi szavazás. Az EU-kritikus állásfoglalás. Te MELLETTE szavazol. Nem ELLENE, nem TARTÓZKODSZ. MELLETTE. Világos?");
        input.nextLine();

        System.out.println("Mit reagálsz?");
        System.out.println("\n1 - Persze. Hol kell aláírni?");
        System.out.println("2 - És ha esetleg a saját véleményem más?");
        System.out.println("3 - Előbb elolvasom a javaslatot.");

        int valasztas5 = sc.nextInt();

        if(valasztas5 == 1){
            xp += 20;
            lebukas += 15;
            System.out.println("-Molnár Gábor-\nLátom, gyorsan tanul. Ez jó.");
            input.nextLine();
        }
        else if(valasztas5 == 2){
            xp += 10;
            lebukas += 20;
            System.out.println("-Molnár Gábor-\n*nevet* Vélemény? VÉLEMÉNY?! " + nev + ", maga vicces ember.");
            input.nextLine();
        }
        else{
            xp += 5;
            System.out.println("-Molnár Gábor-\nOlvasás? Hm. Furcsa szokás. De legyen.");
            input.nextLine();
        }

        raf.writeBytes("5. valasztas: " + valasztas5 + "\n");


        System.out.println("Megtudtad, hogy Dr. Péteri az előléptetésedért cserébe 50%-os részesedést akar minden jövőbeli üzletedből. Ideje tisztázni a viszonyokat.");


        System.out.println("BOSSFIGHT: Dr. Péteri Katalin");
        System.out.println("Ha nem támadsz, 2 körönként -20 HP sebzést fog okozni Péteri MÉDIABOTRÁNY képessége!");

        boolean boss2Verve = false;

        while(!boss2Verve){

            int playerHp = 100;
            int bossHp = 80;

            int kor = 0;

            while(playerHp > 0 && bossHp > 0){

                kor++;

                System.out.println("\nTe HP: " + playerHp);
                System.out.println("Dr. Péteri HP: " + bossHp);

                System.out.println("1 - Támadás");
                System.out.println("2 - Védekezés");

                int player = sc.nextInt();

                int boss = rnd.nextInt(2) + 1;

                if(kor % 2 == 0 && player != 1){
                    System.out.println("Péteri MÉDIABOTRÁNY képességet használt!");
                    playerHp -= 20;
                }

                if(player == 1 && boss == 2){
                    System.out.println("Péteri kivédte.");
                }
                else if(player == 1 && boss == 1){
                    System.out.println("Mindketten támadtatok!");
                    playerHp -= 10;
                    bossHp -= 10;
                }
                else if(player == 2 && boss == 1){
                    System.out.println("Péteri megütött!");
                    playerHp -= 20;
                }
                else{
                    System.out.println("Mindketten védekeztetek.");
                }

                if(player == 1 && boss != 2){
                    bossHp -= 25;
                }
            }


            if(playerHp <= 0){

                System.out.println("\nVESZTETTÉL!");
                System.out.println("1 - Újra");
                System.out.println("2 - Kilépés");

                int ujra = sc.nextInt();

                if(ujra == 2){
                    return;
                }
            }
            else{
                boss2Verve = true;
                xp += 80;
                peteriDosszie = true;

                System.out.println("\nLegyőzted Dr. Péterit!");
            }
        }

        System.out.println("6.fejezet: Az Országos Elnökség Kapujában");
        System.out.println("idézet");
        input.nextLine();
        System.out.println("Helyszín: Elit vadászkastély, éjszaka, szivarfüst");
        input.nextLine();
        System.out.println("Meghívást kaptál a \"belső körbe\". Ahova nem mindenki juthat be. A szobában a párt legbefolyásosabb emberei ülnek bőrfotelekben.");
        input.nextLine();
        System.out.println("-Kapzs Imre-\nSzóval te vagy " + nev + ". Hallottam rólad. Gyorsan mászol. Ez jó. De tudod, mi a különbség a mászó és a csillag között?");
        input.nextLine();
        System.out.println("*Mindenki téged néz.*");
        input.nextLine();
        System.out.println("-Kapzs Imre-\nA mászó bármikor leeshet. A csillag... a csillag örökké ragyog. Na persze, amíg MI ragyogtatjuk.");
        input.nextLine();
        System.out.println("*Mindenki nevet.*");
        input.nextLine();
        System.out.println("-Kapzs Imre-\nFigyelj, " + nev + ". Van egy kis... probléma. Az ellenzék túl hangos lett. Szükségünk van valakire, aki... nos, aki megoldja. Kreatívan. Érted, ugye?");
        input.nextLine();

        System.out.println("Mit válaszolsz?");
        System.out.println("\n1 - Bármit, Főnök. Csak mondja, mit.");
        System.out.println("2 - Milyen jellegű 'megoldásra' gondol?");
        System.out.println("3 - *Meghajolsz, de nem szólsz semmit*");

        int valasztas6 = sc.nextInt();

        if(valasztas6 == 1){
            xp += 50;
            lebukas += 40;
            fonokBizalma = true;
            System.out.println("MEGSZERZETT TÁRGY: FŐNÖK BIZALMA");
            System.out.println("-Kapzs Imre-\nNA EZ AZ! Ilyen embereket akarok! Gyere ide, ülj mellém!");
            input.nextLine();
        }
        else if(valasztas6 == 2){
            xp += 25;
            lebukas += 15;
            System.out.println("-Kapzs Imre-\nÓvatos! Ez bölcs. De néha a bölcsesség lassít.");
            input.nextLine();
        }
        else{
            xp += 10;
            lebukas += 5;
            System.out.println("-Kapzs Imre-\nHm. A néma kutya sosem harap. De nem is véd.");
            input.nextLine();
        }

        raf.writeBytes("6. valasztas: " + valasztas6 + "\n");

        System.out.println("-Kapzs Imre-\nRendben. A részletekről később beszélhetünk.");
        input.nextLine();

        System.out.println("7.fejezet: Az Elnökség Trónján");
        System.out.println("idézet");
        input.nextLine();
        System.out.println("Helyszín: A párt központja, az elnökségi terem");
        input.nextLine();
        System.out.println("*Most már az Országos Elnökség tagja vagy. A hatalom szaga érezhető – és kissé rothadt. De te már hozzászoktál.*");
        input.nextLine();

        System.out.println("-Kapzs Imre-\n" + nev + "! Gyere, sétáljunk. Van valami, amit csak neked mondok el.");
        input.nextLine();
        System.out.println("Kimentek a kertbe. Senki más nincs ott.");
        input.nextLine();
        System.out.println("-Kapzs Imre-\nTudod, én nem leszek örökké. Nem, nem, ne tiltakozz. Mindenki halandó. És nekem... nos, nekem szükségem van valakire, aki folytatja. Aki érti, hogyan működik ez az egész.");
        input.nextLine();
        System.out.println("*Kapzs Imre megáll és rádnéz*");
        input.nextLine();
        System.out.println("-Kapzs Imre-\nTe lehetnél az, " + nev + ". De előbb... előbb bizonyítanod kell. Van egy utolsó teszt.");
        input.nextLine();


        System.out.println("Mit válaszolsz?");
        System.out.println("\n1 - Bármi lesz, kész vagyok rá.");
        System.out.println("2 - Mi lenne ez a teszt?");
        System.out.println("3 - Talán még nem vagyok készen...");

        int valasztas7 = sc.nextInt();

        if(valasztas7 == 1){
            System.out.println("-Kapzs Imre-\nBármi? Bármi egy veszélyes szó, kisfiam. De legyen.");
            input.nextLine();
        }
        else if(valasztas7 == 2){
            System.out.println("-Kapzs Imre-\nAz egyetlen teszt, ami számít: le kell győznöd engem.");
            input.nextLine();
        }
        else{
            System.out.println("-Kapzs Imre-\nHm. Az önismeret erény. De a gyávaság nem.");
            input.nextLine();
        }

        raf.writeBytes("7. valasztas: " + valasztas7 + "\n");

        System.out.println("Helyszín: A párt titkos tanácsterme, éjfél");
        input.nextLine();
        System.out.println("*Ez az a pillanat, amire minden eddig történt felkészített. Kapzs tudja, hogy te vagy az egyetlen, aki fenyegetést jelenthet rá. És te is tudod: csak egyikőtök maradhat a csúcson.*");


        System.out.println("BOSSFIGHT: Kapzs Imre");
        System.out.println("KÜLÖNLEGES KÉPESSÉGEK:\nMédia Manipuláció - Minden 2. körben -15 HP neked\nHűséges Talpnyalók - 3 körönként +50 HP magának\nVégső Szónoklat - HP alatt 50: dupla sebzés");
        input.nextLine();
        System.out.println("Ha megvan a FŐNÖK BIZALMA tárgyad, Kapzs támadásai 30%-kal gyengébbek!");


        boolean vegsoBoss = false;

        while(!vegsoBoss){

            int playerHp = 150;
            int bossHp = 170;

            int kor = 0;

            while(playerHp > 0 && bossHp > 0){

                kor++;

                System.out.println("\nTe HP: " + playerHp);
                System.out.println("Kapzs Imre HP: " + bossHp);

                System.out.println("1 - Támadás");
                System.out.println("2 - Védekezés");

                int player = sc.nextInt();

                int boss = rnd.nextInt(2) + 1;



                if(kor % 2 == 0){

                    int sebzes = 15;

                    if(fonokBizalma){
                        sebzes = 10;
                    }

                    playerHp -= sebzes;

                    System.out.println("MÉDIA MANIPULÁCIÓ!");
                }



                if(kor % 3 == 0){
                    bossHp += 50;

                    if(bossHp > 300){
                        bossHp = 300;
                    }

                    System.out.println("Kapzs gyógyult!");
                }



                if(player == 1 && boss != 2){
                    System.out.println("Kapzs kivédte.");

                    int sebzes = 35;

                    if(bossHp <= 50){
                        sebzes = 20;
                    }

                    bossHp -= sebzes;
                }

                else if(player == 2 && boss == 1){

                    System.out.println("Kapzs megütött!");
                    int sebzes = 30;

                    if(bossHp <= 50){
                        sebzes = 60;
                    }

                    playerHp -= sebzes;
                }

                else if(player == 1 && boss == 1){
                    System.out.println("Mindketten támadtatok!");
                    playerHp -= 10;
                    bossHp -= 10;
                }
                else{
                    System.out.println("Mindketten védekeztetek.");
                }


            }

            if(playerHp <= 0){

                System.out.println("\n1 - Újra");
                System.out.println("2 - Kilépés");

                int ujra = sc.nextInt();

                if(ujra == 2){
                    return;
                }
            }
            else{
                vegsoBoss = true;
                xp += 120;
                parlamentiKulcs = true;
            }
        }

        System.out.println("*Kapzs Imre \"egészségügyi okokból\" visszavonult. A párt új elnöke: " + nev + ". Te.*");
        input.nextLine();
        System.out.println("A tükör előtt állsz, az elnöki irodában. Az ablakon túl Budapest fényei.");
        input.nextLine();
        System.out.println("-Te-\n4 millió forint adósság. Ezzel kezdődött. És most... most itt vagyok.");
        input.nextLine();
        System.out.println("*Valaki kopog*");
        input.nextLine();
        System.out.println("-Ismeretlen hang-\nElnök úr? Van itt egy fiatal srác. Kovács Péter a neve. Azt mondja, tartozik a banknak, és... nos, bármire hajlandó.");
        input.nextLine();





        if(lebukas >= 100){

            System.out.println("\nLEBUKTÁL!");
            System.out.println("A sajtó mindent kiderített.");
        }
        else{

            System.out.println("\n================================");
            System.out.println(" GRATULÁLOK!");
            System.out.println(" TE LETTÉL A PÁRTELNÖK!");
            System.out.println("================================");

            System.out.println("\nVégső XP: " + xp);
            System.out.println("Lebukásmérő: " + lebukas + "%");

            System.out.println("\nTárgyak:");

            if(elsoBoritek){
                System.out.println("- Első Boríték");
            }

            if(kisBoritek){
                System.out.println("- Kis Boríték");
            }

            if(lakatosAktaja){
                System.out.println("- Lakatos Aktája");
            }

            if(offshoreKod){
                System.out.println("- Offshore Kód");
            }

            if(peteriDosszie){
                System.out.println("- Péteri Dosszié");
            }

            if(fonokBizalma){
                System.out.println("- Főnök Bizalma");
            }

            if(parlamentiKulcs){
                System.out.println("- Parlamenti Többség Kulcsa");
            }
        }

        raf.close();
        sc.close();
    }
}