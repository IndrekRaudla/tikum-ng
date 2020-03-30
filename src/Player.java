import java.util.Scanner;

public class Player {
    private boolean isHuman;
    private int tikkeLaual;
    private String nimi;

    public Player(boolean isHuman, String nimi) {
        this.isHuman = isHuman;
        this.nimi = nimi;
    }

    public void setTikkeLaual(int tikkeLaual) {
        this.tikkeLaual = tikkeLaual;
    }

    public String getNimi() {
        return nimi;
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

        if(this.tikkeLaual > 3){
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
                    if (tikkeEemaldada > 3){
                        tikkeEemaldada = 3;
                        System.out.println("Maksimaalselt saab eemaldada 3 tikku oma käigu ajal.");
                    }
                    if (tikkeEemaldada <= 0){
                        tikkeEemaldada = 1;
                        System.out.println("Minimaalselt saab eemaldada 1 tiku oma käigu ajal,");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Vigane sisend. Eemaldan 1 tiku");
                }

            } catch (NumberFormatException e) {
                System.out.println("Ei sisestatud midagi. Eemaldan 1 tiku.");
            }
        }

        // ARVUTI
        else {
            if (tikkeLaual > 3){
                tikkeEemaldada = 3;

            } else {
                tikkeEemaldada = tikkeLaual - 1;
                if (tikkeEemaldada <= 0){
                    tikkeEemaldada = 1;
                }
            }
        }

        if(tikkeLaual - tikkeEemaldada >= 1){
            if (tikkeEemaldada > 1){
                System.out.println("\tLaualt eemaldati " +tikkeEemaldada +" tikku!");
            } else {
                System.out.println("\tLaualt eemaldati " +tikkeEemaldada +" tikk!");
            }
        } else {
            System.out.println("Eemaldati viimane tikk!");
            System.out.println("\n\tKaotas: " +nimi +"\n");
        }

        return tikkeEemaldada;
    }
}
