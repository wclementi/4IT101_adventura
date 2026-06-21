package cz.vse.adventura.logika.prikazy;
import cz.vse.adventura.logika.*;
import cz.vse.adventura.logika.predmety.*;
import cz.vse.adventura.logika.prikazy.*;
import cz.vse.adventura.logika.npc.*;

public class PrikazVypis implements IPrikaz {
    private static final String NAZEV = "vypiš";
    private HerniPlan plan;

    public PrikazVypis(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co mám vypsat? (možnosti: prostor, batoh)";
        }

        String parametr = parametry[0];

        if (parametr.equals("prostor")) {
            return plan.getAktualniProstor().dlouhyPopis();
        } else if (parametr.equals("batoh")) {
            return plan.getPopisObsahuBatohu();
        } else {
            return "To neznám. Můžu vypsat jen 'prostor' nebo 'batoh'.";
        }
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
