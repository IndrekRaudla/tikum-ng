/*
    Tikumäng:
            Programmiga saab mängida mängu, kus laual on juhuslik arv tikku.
            Mängijad võtavad sealt kordamööda ära kas üks, kaks või kolm tikku.
            Kes võtab viimase tiku, on kaotaja.

    Autor: Indrek Raudla, 2020
 */
public class Main {
    public static void main(String[] args) {

        // Luuakse gameloop isend ja pannakse see käima. Kogu mänguloogika ja sellega seonduv toimub vastava klassi/objekti sees.
        Gameloop gameloop = new Gameloop();
        gameloop.start();
    }
}
