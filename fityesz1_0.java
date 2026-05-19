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
        System.out.println("*A teremben körülbelül 30 ember ül műanyag székeken. A falon gigantikus Kapzsi Imre-portré, alatta a felirat: \"CSAK FELFELÉ!\"*");
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

        System.out.println("-Hangszóró-\nTisztelt Kongresszus! Kérjük, álljanak fel és köszöntsék a Nemzet Megmentőjét, a Haza Pajzsát, Kapzsi Imre Miniszterelnök Urat!");
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

        System.out.println("4. fejezet");

        raf.close();
        sc.close();
    }
}