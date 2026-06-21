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
     * Metoda pro getNazev.
     * @return hodnota z metody
     */
    public String getNazev() {
        return nazev;
    }

    /**
     * Metoda pro setNazev.
     * @param nazev parametr metody
     */
    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    /**
     * Metoda pro isPrenositelna.
     * @return hodnota z metody
     */
    public boolean isPrenositelna() {
        return prenositelna;
    }

    /**
     * Metoda pro setPrenositelna.
     * @param prenositelna parametr metody
     */
    public void setPrenositelna(boolean prenositelna) {
        this.prenositelna = prenositelna;
    }

    /**
     * Metoda pro getHmotnost.
     * @return hodnota z metody
     */
    public int getHmotnost() {
        return hmotnost;
    }

    /**
     * Metoda pro setHmotnost.
     * @param hmotnost parametr metody
     */
    public void setHmotnost(int hmotnost) {
        this.hmotnost = hmotnost;
    }
}
