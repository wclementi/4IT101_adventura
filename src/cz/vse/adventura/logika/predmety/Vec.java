package cz.vse.adventura.logika.predmety;

/**
 * Třída představující předmět, který lze sebrat, položit nebo použít.
 *
 * @author Kajetan Franciszek Wielgosz
 */
public class Vec
{
    private String nazev;
    private String nazevBD;
    private boolean prenositelna;
    private int hmotnost;

    public Vec(String nazev, boolean prenositelna, int hmotnost) {
        this.nazev = nazev;
        this.nazevBD = cz.vse.adventura.logika.TextUtil.odstranDiakritiku(nazev);
        this.prenositelna = prenositelna;
        this.hmotnost = hmotnost;
    }

    /**
     * Vrátí název tohoto předmětu.
     * @return název předmětu
     */
    public String getNazev() {
        return nazev;
    }

    /**
     * Vrátí název bez diakritiky.
     * @return název bez diakritiky
     */
    public String getNazevBD() {
        return nazevBD;
    }

    /**
     * Nastaví nový název předmětu a přepočítá název bez diakritiky.
     * @param nazev nový název předmětu
     */
    public void setNazev(String nazev) {
        this.nazev = nazev;
        this.nazevBD = cz.vse.adventura.logika.TextUtil.odstranDiakritiku(nazev);
    }

    /**
     * Zjišťuje, zda je předmět možné sebrat a přenášet v batohu.
     * @return true, pokud je předmět přenositelný, jinak false
     */
    public boolean isPrenositelna() {
        return prenositelna;
    }

    /**
     * Nastaví přenositelnost předmětu.
     * @param prenositelna true pro přenositelný předmět, false pro nepřenositelný
     */
    public void setPrenositelna(boolean prenositelna) {
        this.prenositelna = prenositelna;
    }

    /**
     * Vrátí hmotnost (nosnostní váhu) předmětu.
     * @return hmotnost předmětu
     */
    public int getHmotnost() {
        return hmotnost;
    }

    /**
     * Nastaví hmotnost předmětu.
     * @param hmotnost nová hmotnost předmětu
     */
    public void setHmotnost(int hmotnost) {
        this.hmotnost = hmotnost;
    }
}
