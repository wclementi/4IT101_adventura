package cz.vse.adventura.logika.prikazy;
import cz.vse.adventura.logika.*;

/**
 * Příkaz pro vypití nápoje za účelem zahnání žízně.
 *
 * @author Kajetan Franciszek Wielgosz
 */
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
            hra.setVypitoSavo(true);
            return "Vypil jsi něco z lahve od sava. Udělalo se ti docela špatně, ale zatím se držíš na nohou. Měl by sis jít rychle sednout na přednášku.";
        } else {
            hra.setUspokojenaZizen(true);
            return "Vypil jsi '" + nazev + "'. Výborně osvěžující! Žízeň je zahnána. Můžeš pokračovat na přednášku a usadit se.";
        }
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
