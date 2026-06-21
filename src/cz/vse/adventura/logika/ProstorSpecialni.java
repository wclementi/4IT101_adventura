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
     * Metoda pro isZamceno.
     * @return logický výsledek volání
     */
    public boolean isZamceno() {
        return zamceno;
    }

    /**
     * Metoda pro setZamceno.
     * @param zamceno požadovaný vstup zamceno
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
