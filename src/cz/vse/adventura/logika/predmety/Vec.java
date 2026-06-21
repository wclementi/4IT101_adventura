package cz.vse.adventura.logika.predmety;

/**
 * Třída představující předmět, který lze sebrat, položit nebo použít.
 *
 * @author Kajetan Franciszek Wielgosz
 */
public class Vec
{
    private String nazev;
    private boolean prenositelna;
    private int hmotnost;

    public Vec(String nazev, boolean prenositelna, int hmotnost) {
        this.nazev = nazev;
        this.prenositelna = prenositelna;
        this.hmotnost = hmotnost;
    }

    /**
     * Vrátí název této instance (předmětu, prostoru, příkazu či postavy).
     * @return logický výsledek volání
     */
    public String getNazev() {
        return nazev;
    }

    /**
     * Metoda pro setNazev.
     * @param nazev hledaný název
     */
    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    /**
     * Zjišťuje, zda je předmět možné sebrat a přenášet v batohu.
     * @return logický výsledek volání
     */
    public boolean isPrenositelna() {
        return prenositelna;
    }

    /**
     * Metoda pro setPrenositelna.
     * @param prenositelna požadovaný vstup prenositelna
     */
    public void setPrenositelna(boolean prenositelna) {
        this.prenositelna = prenositelna;
    }

    /**
     * Vrátí hmotnost předmětu.
     * @return logický výsledek volání
     */
    public int getHmotnost() {
        return hmotnost;
    }

    /**
     * Metoda pro setHmotnost.
     * @param hmotnost požadovaný vstup hmotnost
     */
    public void setHmotnost(int hmotnost) {
        this.hmotnost = hmotnost;
    }
}
