package cz.vse.adventura.logika.prikazy;
import cz.vse.adventura.logika.*;
import cz.vse.adventura.logika.predmety.*;
import cz.vse.adventura.logika.prikazy.*;
import cz.vse.adventura.logika.npc.*;

public class PrikazPij implements IPrikaz {
    private static final String NAZEV = "pij";
    private HerniPlan plan;
    private Hra hra;

    public PrikazPij(HerniPlan plan, Hra hra) {
        this.plan = plan;
        this.hra = hra;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co mám pít? Musíš zadat název nápoje.";
        }

        String nazev = parametry[0];

        try {
            plan.vypij(nazev);
        } catch (Exception e) {
            return e.getMessage();
        }

        if (nazev.equals("plná_lahev_od_sava")) {
            hra.setStavHry(StavHry.PROHRA);
            return "Vypil jsi vodu ze špinavé lahve od sava. Udělalo se ti hrozně špatně a zkolaboval jsi.";
        } else {
            hra.setStavHry(StavHry.VYHRA);
            return "Vypil jsi '" + nazev + "'. Výborně osvěžující! Žízeň je zahnána. Můžeš pokračovat na přednášku.";
        }
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
