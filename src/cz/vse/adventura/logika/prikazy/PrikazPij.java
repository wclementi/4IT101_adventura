package cz.vse.adventura.logika.prikazy;
import cz.vse.adventura.logika.*;
import cz.vse.adventura.logika.predmety.*;
import cz.vse.adventura.logika.prikazy.*;
import cz.vse.adventura.logika.npc.*;

public class PrikazPij implements IPrikaz {
    private static final String NAZEV = "pij";
    private HerniPlan plan;

    public PrikazPij(HerniPlan plan) {
        this.plan = plan;
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

        // ukol 12. tyden
        return "Vypil jsi '" + nazev + "'. Výborně osvěžující! Žízeň je zahnána. Můžeš pokračovat na přednášku.";
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
