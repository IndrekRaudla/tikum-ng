import java.util.Scanner;

public class Player {
    private boolean isHuman;
    private int tikkeLaual;

    public Player(boolean isHuman) {
        this.isHuman = isHuman;
    }

    public void setTikkeLaual(int tikkeLaual) {
        this.tikkeLaual = tikkeLaual;
    }


    // Testmeetod kontrollimaks, kas mängijad said ilusti loodud. Võib hiljem kustutada.
    public void hello(){
        if (isHuman){
            System.out.println("Inimene ütleb tere!");
        }
        if(!isHuman){
            System.out.println("BOT ütleb tere!");
        }
    }



    public int play(){
        int maxEemaldada;

        if(this.tikkeLaual >= 3){
            maxEemaldada = 3;
        } else {
            maxEemaldada = this.tikkeLaual;
        }

        int tikkeEemaldada = 1;
        Scanner input = new Scanner(System.in);

        // INIMENE
        if(this.isHuman){
            try{
                System.out.print("Mitu tikku eemaldada? (1 kuni " +maxEemaldada +" tikku) "); // Tee kontroll hiljem
                String sisend = input.nextLine();

                try{
                    tikkeEemaldada = Integer.parseInt(sisend);
                } catch (NumberFormatException e) {
                    System.out.println("Vigane sisend. Eemaldan 1 tiku");
                }

            } catch (NumberFormatException e) {
                System.out.println("Ei sisestatud midagi. Eemaldan 1 tiku.");
            }
        }

        // ARVUTI
        else {
            if (maxEemaldada > 3){
                tikkeEemaldada = 3;
            } else {
                tikkeEemaldada = maxEemaldada - 1;
                if (tikkeEemaldada <= 0){
                    tikkeEemaldada = 1;
                }
            }
        }

        System.out.println("Tikutopsist eemaldati " +tikkeEemaldada +" tikku!");
        return tikkeEemaldada;
    }
}
