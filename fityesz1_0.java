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


        System.out.println("Add meg a neved: ");
        String nev = input.nextLine();
        System.out.println("Te " + nev + " vagy");
        System.out.println("Van 3 kiflid, 380 Forintod és 4 millió forint adósságod.");
        System.out.println("A bank már keres.");
        System.out.println("Ezért politikusnak állsz.\n");



        System.out.println("Lipóti Dezső:");
        System.out.println("\"A nemzet szolgálata jól fizet.\"");

        System.out.println("\n1 - Engem csak a pénz érdekel");
        System.out.println("2 - A nemzet szolgálata érdekel");
        System.out.println("3 - Mi lenne a dolgom?");

        int valasztas1 = sc.nextInt();

        if(valasztas1 == 1){
            xp += 15;
            lebukas += 10;
        }
        else if(valasztas1 == 2){
            xp += 10;
            lebukas += 5;
        }
        else{
            xp += 5;
        }

        raf.seek(raf.length());
        raf.writeBytes("1. fejezet valasztas: " + valasztas1 + "\n");



        System.out.println("\nElfogadod a pénzt?");
        System.out.println("1 - Igen");
        System.out.println("2 - Igen, de kérdezek");
        System.out.println("3 - Nem");

        int valasztas2 = sc.nextInt();

        if(valasztas2 == 1){
            xp += 20;
            lebukas += 15;
            elsoBoritek = true;
        }
        else if(valasztas2 == 2){
            xp += 10;
            lebukas += 5;
        }
        else{
            lebukas -= 10;
        }

        raf.writeBytes("2. valasztas: " + valasztas2 + "\n");



        System.out.println("\nLakatos Ervin:");
        System.out.println("\"Amit látsz, nem láttad.\"");

        System.out.println("\n1 - Hol az én borítékom?");
        System.out.println("2 - És ha valaki lebukik?");
        System.out.println("3 - Csendben maradok");

        int valasztas3 = sc.nextInt();

        if(valasztas3 == 1){
            xp += 25;
            lebukas += 20;
            kisBoritek = true;
        }
        else if(valasztas3 == 2){
            xp += 10;
            lebukas += 5;
        }
        else{
            xp += 5;
        }

        raf.writeBytes("3. valasztas: " + valasztas3 + "\n");


        if(xp >= 50){
            szint = 2;
            System.out.println("\nSZINTLÉPÉS!");
            System.out.println("Új rang: HELYI PÁRTTAG");
        }



        boolean boss1Verve = false;

        while(!boss1Verve){

            int playerHp = 80;
            int bossHp = 100;

            int elozo = 0;
            int ugyanaz = 0;

            System.out.println("\nBOSSFIGHT: Lakatos Ervin");

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

                if(player == 1 && boss == 2){
                    System.out.println("Lakatos kivédte.");
                }
                else if(player == 1 && boss == 1){
                    System.out.println("Mindketten támadtatok!");
                    playerHp -= 10;
                    bossHp -= 10;
                }
                else if(player == 2 && boss == 1){
                    System.out.println("Lakatos megütött!");
                    playerHp -= 15;
                }
                else{
                    System.out.println("Mindketten védekeztetek.");
                }

                if(player == 1 && boss != 2){
                    bossHp -= 20;
                }
            }

            if(playerHp <= 0){

                System.out.println("\nVESZTETTÉL!");
                System.out.println("1 - Újrapróbálás");
                System.out.println("2 - Kilépés");

                int ujra = sc.nextInt();

                if(ujra == 2){
                    System.out.println("Játék vége.");
                    return;
                }
            }
            else{
                boss1Verve = true;
                xp += 50;
                lakatosAktaja = true;

                System.out.println("\nLegyőzted Lakatos Ervint!");
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

        raf.close();
        sc.close();
    }
}