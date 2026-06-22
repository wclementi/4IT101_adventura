package cz.vse.adventura.logika;

// ukol 12. tyden: Dědičnost
/**
 * Speciální typ prostoru, který se chová odlišně (např. omezuje vstup).
 *
 * @author Kajetan Franciszek Wielgosz
 */
public class ProstorSpecialni extends Prostor {
    private boolean zamceno;

    public ProstorSpecialni(String nazev, String popis, boolean zamceno) {
        super(nazev, popis);
        this.zamceno = zamceno;
    }

    /**
     * Zjišťuje, zda je prostor zamčený.
     * @return true, pokud je prostor zamčený, jinak false
     */
    public boolean isZamceno() {
        return zamceno;
    }

    /**
     * Nastaví stav zamčení prostoru.
     * @param zamceno true pro zamčený prostor, false pro odemčený
     */
    public void setZamceno(boolean zamceno) {
        this.zamceno = zamceno;
    }

    @Override
    public String dlouhyPopis() {
        if (zamceno) {
            return "Zkoušíš nahlédnout dovnitř, ale je to tady zamčené.";
        }
        return super.dlouhyPopis() + "\n(Toto je speciální odemčený prostor)";
    }
}
