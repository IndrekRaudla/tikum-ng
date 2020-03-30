import javax.print.attribute.standard.NumberOfInterveningJobs;
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
            System.out.println("Mitu mängijat mängib?");
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
                String mängijaNimi = "player" +i;
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
                    mängijad.add(new Player(isHuman));
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
        System.out.println("Hakkab pihta");

        mängijad = setup();

        Tikutops tikutops = new Tikutops(3);
        tikutops.toString();

    }
}
