package cz.vse.adventura.logika.prikazy;
import cz.vse.adventura.logika.*;

public class PrikazPoloz implements IPrikaz {
    private static final String NAZEV = "polož";
    private HerniPlan plan;

    public PrikazPoloz(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co mám položit - musíš napsat název věci, kterou mám položit.";
        }

        String nazev = parametry[0];

        try {
            this.plan.polozVec(nazev);
        } catch (Exception exception) {
            return exception.getMessage();
        }

        return "Věc '" + nazev + "' je nyní v prostoru.";
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
