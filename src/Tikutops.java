// Lihtne klass mis vastutab tikkude arvuga laual.
// Ma eeldan, et seda seletama lahti ei pea, kuna tegu on nii lihtsa klassiga.
public class Tikutops {
    private int tikud;

    public Tikutops(int tikud) {
        this.tikud = tikud;
    }

    public int getTikud() {
        return tikud;
    }

    public void eemalda(int i){
        tikud -= i;
    }

    public void setTikud(int tikud) {
        this.tikud = tikud;
    }

    @Override
    public String toString() {
        return "Tikutopsis on " +tikud;
    }
}
