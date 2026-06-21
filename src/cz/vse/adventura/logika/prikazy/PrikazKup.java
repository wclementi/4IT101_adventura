package cz.vse.adventura.logika.prikazy;
import cz.vse.adventura.logika.*;

/**
 * Příkaz pro nákup položky v automatu.
 *
 * @author Kajetan Franciszek Wielgosz
 */
public class PrikazKup implements IPrikaz {
    private static final String NAZEV = "kup";
    private HerniPlan plan;

    public PrikazKup(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co mám koupit? Musíš zadat název věci (např. z automatu).";
        }

        String nazev = parametry[0];

        try {
            plan.kupVec(nazev);
        } catch (Exception e) {
            return e.getMessage();
        }

        return "Koupil jsi '" + nazev + "' a věc se objevila v prostoru. Nyní si ji můžeš sebrat.";
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
