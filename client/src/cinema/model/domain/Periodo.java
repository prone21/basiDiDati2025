package cinema.model.domain;

public class Periodo {
    private int anno;
    private int mese;

    public Periodo(int anno, int mese) {
        this.anno = anno;
        this.mese = mese;
    }
    public int getAnno() {
        return anno;
    }
    public int getMese() {
        return mese;
    }
}
