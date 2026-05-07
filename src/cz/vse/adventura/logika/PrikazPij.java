package cz.vse.adventura.logika;

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

        return "Vypil jsi '" + nazev + "'. Výborně osvěžující!";
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}