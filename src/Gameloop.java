import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gameloop {

    // Mängijate nimekiri
    private List<Player> mängijad;

    // Mängujuhendi väljastamiseks loodud meetod
    public void tutorial(){
        System.out.println("Tikumäng\n");
        System.out.println("Mäng on loodud kahele mängijale.");
        System.out.println("Mängijad võtavad kordamööda laualt ära 1-3 tikku.");
        System.out.println("See kes võtab laualt viimase tiku KAOTAB mängu.");
        System.out.println("Edu! \n");
    }


    // Meetod, millega luuakse mängijad.
    // Valida saab mängijate arvu ning selle, kas mängija on inimene või mängib soovitud mängija eest arvuti.
    // Nimi genereeritakse automaatselt (Mängija i), kus i = mängija number.
    // Rangelt soovituslik on mängida kahekesi (või ka lihtsalt arvuti vastu), kuna mängu olemuse pärast on võimalik
    // teha koostööd nii, et alati kaotab teatud mängija.
    // Kuid samas, alat ion seltsis segasem, ning alati võib ka mitmekesi mängida.
    // Tagastab mängijate nimekirja

    // Sidenote - Mängijate arv võiks olla teoorias vähemalt 3x(mängijate arv - 1), kuna siis saaksid kõik vähemalt
    // ühe käigu käia, kuid ma ei näe vajadust sellist reeglit peale suruda.
    public List<Player> setup(){
        System.out.println("Toimub mängijate loomine.\n");
        System.out.println("Soovituslik on mängida KAHEKESI.");

        mängijad = new ArrayList<Player>();
        // Mängijate arv väärtustatakse kaheks, kuid kohe küsitakse üle, mitu mängijat mängib.
        int mängijaid = 2;
        Scanner input = new Scanner(System.in);

        // while loop kuniks on sisestatud korrektne arv mängijaid.
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

        // Iga mängija kohta küsitakse, kas tegu on inimese või arvutiga ning seejärel väärtustatakse neile
        // vastav tõeväärtus ning luuakse Player isend mis lisatakse ka ennem tehtud listi.
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
    // Tegelikult ebavajalik meetod, kuna tikutops.setTikud töötaks ka mujal, kuid see teeb hiljem mänguloogikas natukene
    // viisakamaks koodi.
    public void muudaTikke(int tikkudeArv, Tikutops tikutops){
        System.out.println("Uus tikkude arv laual on " +tikkudeArv +".\n");
        tikutops.setTikud(tikkudeArv);
    }


    // Mänguloogika meetod, mis käib kogu mängu käimise ajal
    // Menüüs saab valida nelja valiku vahel [Alusta mängu, Muuda tikutopsi suurust, Loo uued mängijad, Peata programm]
    public void start(){
        int tikke = 25;
        int valik;

        // Ennem kui menüüsse minnakse, luuakse ära mängijad ja näidatakse mängureeglid ette. Mõlemat saab hiljem muuta
        // Lisaks tehakse ära tikutops, mis vahendab kogu mängu jooksul infot tikkude kohta laual.
        Tikutops tikutops = new Tikutops(tikke);
        tutorial();
        mängijad = setup();

        // See vajaks natukene ümberkirjutamist et olla efektiivsem, kuid hetkel pole vajadust.
        // (Kuna ma alguses ei plaaninud switche kasutada, kuid hiljem otsustasin ümber, siis on üks while(true) tsükkel liiga palju hetkel)

        while(true){
            Scanner input = new Scanner(System.in);
            while(true){

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

                // Switch vastavalt menüü valikule. Default vastus on menüüs edasi olemine. Kui sisend on int
                // siis laetakse menüü uuesti.
                switch (valik){

                    // Mängu alustamine
                    // Nimekirjas olevad mängijad hakkavad kordamööda eemaldama laualt tikke, kuni lauale jääb 1 tikk
                    // Seejärel kuulutatakse välja kaotaja ning minnakse tagasi peamenüüsse
                    case 1:
                        while(tikutops.getTikud() >= 1){
                            for (Player p :mängijad){
                                // Kui laual on järgi 1 tikk, siis mängija on kohustatud selle ära võtma, ning kaotab
                                // seega mängu
                                if(tikutops.getTikud() == 1){
                                    System.out.println("Laual on " +tikutops.getTikud() +" tikk.");
                                    System.out.println(p.getNimi() +" on kohustatud ära võtma viimase tiku.");
                                    System.out.println("\n\tKaotas: " +p.getNimi() +"\n");
                                    tikutops.setTikud(-1);
                                    break;
                                }
                                // Kui laual on rohkem kui 1 tikk, siis saab mängija oma käigu teha ning järgneb (üldjuhul)
                                // Ka järgmise mängija käik
                                if (tikutops.getTikud() > 1){
                                    System.out.println("Laual on " +tikutops.getTikud() +" tikku");
                                    System.out.println(p.getNimi() +" kord teha oma käik.");
                                    p.setTikkeLaual(tikutops.getTikud());
                                    tikutops.eemalda(p.play());
                                    System.out.println("\n");
                                }
                            }
                        }
                        // "Tikutopsi"/lauale pannakse uuesti mängueelne arv tikke tagasi
                        tikutops.setTikud(tikke);
                        System.out.println();
                        break;

                    // Tikutopsi suuruse muutmine toimib läbi lisameetodi.
                    // Lisaks kontrollitakse ja oodatakse sobivat sisendit, kuni kasutaja selle ka sisestab (täisarv)
                    case 2:
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

                    // Uute mängijate loomine läbi abimeetodi
                    case 3:
                        setup();
                        break;

                    // Programmi sulgemine
                    case 4:
                        System.out.println("Programm suletakse.");
                        System.exit(1);

                    // Peamenüüs vigane sisend, väljastatakse veateade ning oodatakse uut sisendit
                    default:
                        System.out.println("Vigane sisend.\n");
                        break;
                }
            }
        }
    }
}
