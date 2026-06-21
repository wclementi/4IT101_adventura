package cz.vse.adventura.logika.prikazy;
import cz.vse.adventura.logika.*;
import cz.vse.adventura.logika.predmety.*;
import cz.vse.adventura.logika.prikazy.*;
import cz.vse.adventura.logika.npc.*;

public class PrikazProzkoumej implements IPrikaz {
    private static final String NAZEV = "prozkoumej";
    private HerniPlan plan;

    public PrikazProzkoumej(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co mám prozkoumat? Musíš zadat název věci v prostoru nebo v batohu.";
        }

        String nazev = parametry[0];
        
        try {
            return plan.prozkoumejVec(nazev);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
