import java.util.Scanner;

public class Player {
    private boolean isHuman;
    private int tikkeLaual;

    public Player(boolean isHuman, int tikkeLaual) {
        this.isHuman = isHuman;
        this.tikkeLaual = tikkeLaual;
    }

    public void setHuman(boolean human) {
        isHuman = human;
    }

    public void setTikkeLaual(int tikkeLaual) {
        this.tikkeLaual = tikkeLaual;
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


        if(this.isHuman){
            try{
                System.out.print("Mitu tikku eemaldada? (1 kuni " +maxEemaldada +" tikku)");
                tikkeEemaldada = input.nextInt();
                System.out.println();

            } catch (NumberFormatException e) {
                System.out.println("Ei sisestatud midagi. Eemaldan 1 tiku.");
            }
        } else {
            System.out.println("BOT ei oska veel tikke eemaldada :(");
        }


        return tikkeEemaldada;
    }
}
