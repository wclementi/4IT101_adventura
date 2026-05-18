package cz.vse.adventura.logika;
import cz.vse.adventura.logika.predmety.*;
import cz.vse.adventura.logika.prikazy.*;
import cz.vse.adventura.logika.npc.*;

// ukol 12. tyden: Dědičnost
public class ProstorSpecialni extends Prostor {
    private boolean zamceno;

    public ProstorSpecialni(String nazev, String popis, boolean zamceno) {
        super(nazev, popis);
        this.zamceno = zamceno;
    }

    public boolean isZamceno() {
        return zamceno;
    }

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
