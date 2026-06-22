package cz.vse.adventura.logika.prikazy;
import cz.vse.adventura.logika.*;

/**
 * Příkaz pro sebrání předmětu z místnosti do batohu.
 *
 * @author Kajetan Franciszek Wielgosz
 */
public class PrikazSeber implements IPrikaz {
    private static final String NAZEV = "seber";
    private HerniPlan plan;

    public PrikazSeber(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co mám sebrat? Musíš napsat název věci, kterou mám sebrat.";
        }

        String nazev = parametry[0];

        try {
            this.plan.seberVec(nazev);
        } catch (Exception exception) {
            return exception.getMessage();
        }

        return "Věc '" + nazev + "' je nyní v batohu.";
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
    
    @Override
    public String getPouziti() {
        return "seber <název předmětu>";
    }
}
