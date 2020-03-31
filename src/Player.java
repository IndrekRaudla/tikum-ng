import java.util.Scanner;

public class Player {
    private boolean isHuman;
    private int tikkeLaual;
    private String nimi;


    // Konstruktor millega luuakse mängijaid. Parameetriteks on bool kas tegu on inimese või arvutiga ning String nimi.
    public Player(boolean isHuman, String nimi) {
        this.isHuman = isHuman;
        this.nimi = nimi;
    }

    // set meetod laual olevate tikkude arvu muutmiseks
    public void setTikkeLaual(int tikkeLaual) {
        this.tikkeLaual = tikkeLaual;
    }

    // get meetod nime tagastamiseks
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

        // Kontrollib palju on võimalik tikke laualt eemaldada ning väärtustab muutujale maxEemaldada sobiva väärtuse
        if(this.tikkeLaual > 3){
            maxEemaldada = 3;
        } else {
            maxEemaldada = this.tikkeLaual;
        }

        // Väärtus mis hiljem tagastatakse meetodi poolt. Väärtustatakse algselt 1, kuid hiljem muudetakse
        int tikkeEemaldada = 1;
        Scanner input = new Scanner(System.in);

        // Kui tegu on inimmängijaga, siis lastakse valida mitu tikku soovitakse eemaldada
        // Lisaks kontrollitakse, kas ja mida sisestati ning kas sisestatud arv tikke on lubatud eemaldada (1-3)
        // Kui sisend ei ole number või jäetakse tühjaks, eemaldatakse 1 tikk
        // Kui soovitakse eemaldada rohkem/vähem kui lubatud, siis antakse sellest ka mängijale märku

        // Sidenote - ma olen kindel, et kui peaks tekkima olukord, kus lauale jääb 2 tikku, siis mängija saab eemaldada
        // ka 3 tikku, kuna mingit reeglit sellevastu ei ole. Küll aga kaotaks ta kohe mängu, seega otseselt vajadust
        // seda parandada pole
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

        // ARVUTI mängija loogika osa
        // Kui laual on rohkem kui 3 tikku, siis eemaldatakse vastavalt juhusele 1/2/3 tikku (20%/30%/50%)
        // Põhirõhk on ikkagi 3 tiku eemaldamisel, kuid kuna ülesandes on nõutud random() kasutamist
        // Siis siin on ideaalne koht selle jaoks
        else {
            // Juhul kui laual on rohkem kui 3 tikku
            if (tikkeLaual > 3){
                int rng = (int)(Math.random()*11);
                System.out.println(rng);

                if (rng < 3 ){
                    tikkeEemaldada  = 1;
                } else if (rng < 6){
                    tikkeEemaldada = 2;
                } else {
                    tikkeEemaldada = 3;
                }
              // Juhul kui laual on 3 või vähem tikku eemaldatakse tikke nii, et järgi jääks 1 tikk
            } else {
                tikkeEemaldada = tikkeLaual - 1;
                if (tikkeEemaldada <= 0){
                    tikkeEemaldada = 1;
                }
            }
        }

        // Laused mis väljastatakse tikkude eemaldamise ajal. Ilmsalt saaks mõistlikumalt ümer korraldada koodi, aga
        // kuna antud ülesandes ei oma see kriitilist tähtsust, siis võib jääda ka nii.
        // Kui eemaldatakse mitu tikku väljastatakse vastav lause, ning kui eemaldatakse viimane tikk, siis väljastatakse
        // ka kaotaja
        if(tikkeLaual - tikkeEemaldada >= 1){
            if (tikkeEemaldada > 1){
                System.out.println("\tLaualt eemaldati " +tikkeEemaldada +" tikku!");
            } else {
                System.out.println("\tLaualt eemaldati " +tikkeEemaldada +" tikk!");
            }
        } else {
            System.out.println("\tEemaldati viimane tikk!");
            System.out.println("\n\tKaotas: " +nimi +"\n");
        }

        // Tagastab eemaldatavate tikkude soovitud arvu
        return tikkeEemaldada;
    }
}
