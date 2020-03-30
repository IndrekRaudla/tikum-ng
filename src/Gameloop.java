import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gameloop {
    private List<Player> mängijad;

    public void tutorial(){
        System.out.println("Tikumäng\n");
        System.out.println("Mäng on loodud kahele mängijale.");
        System.out.println("Mängijad võtavad kordamööda laualt ära 1-3 tikku.");
        System.out.println("See kes võtab laualt viimase tiku KAOTAB mängu.");
        System.out.println("Edu! \n");
    }


    public List<Player> setup(){
        System.out.println("Toimub mängijate loomine.");
        System.out.println("Soovituslik on mängida KAHEKESI.");

        mängijad = new ArrayList<Player>();
        int mängijaid = 2;
        Scanner input = new Scanner(System.in);

        while (true){
            System.out.println("Mitu mängijat mängib? (2 - ...) ");
            String sisend = input.nextLine();
            try{
                mängijaid = Integer.parseInt(sisend);

                if(mängijaid < 2){
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Vigane sisend. Proovi uuesti.");
            }
        }
        System.out.println();

        for (int i = 0; i < mängijaid; i++) {
            while(true){
                boolean isHuman;
                String mängijaNimi = "Mängija " +(i+1);
                System.out.println("Kas " +(i+1) +". mängija on inimene? (Y/N)");
                String sisend = input.nextLine().toLowerCase();

                try{
                    if (sisend.equals("y")){
                        isHuman = true;
                    } else if (sisend.equals("n")){
                        isHuman = false;
                    } else {
                        throw new Exception();
                    }
                    mängijad.add(new Player(isHuman, mängijaNimi));
                    break;
                } catch (Exception e){
                    System.out.println("Vigane sisend! Proovi uuesti \n");
                }
            }
            System.out.println(i+1 +". mängija loodud!\n");
        }
        System.out.println("Kõik mängijad loodud!\n");

        return mängijad;
    }

    // Saab muuta mängu alguses laual olevat tikkude arvu
    public void muudaTikke(int tikkudeArv, Tikutops tikutops){
        System.out.println("Uus tikkude arv laual on " +tikkudeArv +".\n");
        tikutops.setTikud(tikkudeArv);
    }


    public void start(){
        int tikke = 25;
        int valik;
        boolean mängKäib = false;

        Tikutops tikutops = new Tikutops(tikke);
        tutorial();
        mängijad = setup();

        while(true){
            Scanner input = new Scanner(System.in);
            while(!mängKäib){

                System.out.println("Mängijaid: " +mängijad.size() +" \t\t:\t\t Tikke topsis: " +tikutops.getTikud());
                System.out.println();
                System.out.println("1 - Alusta mängu");
                System.out.println("2 - Muuda tikutopsi suurust");
                System.out.println("3 - Loo uued mängijad");
                System.out.println("4 - Peata programm");

                // Sisendikontroll
                while(true){
                    String sisend = input.nextLine();
                    try{
                        valik = Integer.parseInt(sisend);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Vigane sisend.");
                    }
                }

                System.out.println();
                switch (valik){

                    case 1: // Mängu alustamine
                        while(tikutops.getTikud() >= 1){
                            for (Player p :mängijad){
                                if(tikutops.getTikud() == 1){
                                    System.out.println("Laual on " +tikutops.getTikud() +" tikk.");
                                    System.out.println(p.getNimi() +" on kohustatud ära võtma viimase tiku.");
                                    System.out.println("\n\tKaotas: " +p.getNimi() +"\n");
                                    tikutops.setTikud(-1);
                                    break;
                                }
                                if (tikutops.getTikud() > 1){
                                    System.out.println("Laual on " +tikutops.getTikud() +" tikku");
                                    System.out.println(p.getNimi() +" kord teha oma käik.");
                                    p.setTikkeLaual(tikutops.getTikud());
                                    tikutops.eemalda(p.play());
                                    System.out.println("\n");
                                }
                            }
                        }
                        tikutops.setTikud(tikke);
                        System.out.println();
                        break;

                    case 2: // Tikutopsi suuruse muutmine
                        while(true){
                            System.out.println("Sisesta uue tikutopsi suurus: ");
                            String sisend = input.nextLine();
                            int uusTikkudeArv;
                            try{
                                uusTikkudeArv = Integer.parseInt(sisend);
                                if (uusTikkudeArv < 0){
                                    throw new NumberFormatException();
                                }
                                tikke = uusTikkudeArv;
                                muudaTikke(uusTikkudeArv, tikutops);
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Vigane sisend. Proovi uuesti.\n");
                            }
                        }
                        break;

                    case 3: // Uute mängijate loomine
                        setup();
                        break;

                    case 4: // Programmi sulgemine
                        System.exit(1);

                    default: // Peamenüüs vigane sisend
                        System.out.println("Vigane sisend.\n");
                        break;
                }
            }
            break;
        }
    }

}
