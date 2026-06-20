package cz.vse.adventura.logika.prikazy;
import cz.vse.adventura.logika.*;
import cz.vse.adventura.logika.predmety.*;
import cz.vse.adventura.logika.prikazy.*;
import cz.vse.adventura.logika.npc.*;

public class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "jdi";
    private HerniPlan plan;
    
    public PrikazJdi(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Kam mám jít? Musíš zadat jméno východu";
        }

        String smer = parametry[0];

        Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(smer);

        if (sousedniProstor == null) {
            return "Tam se odsud jít nedá!";
        }
        else {
            if (Batoh.getInstance().jeVecVBatohu("telefon_s_aplikaci_lokni_si")) {
                String nazev = sousedniProstor.getNazev();
                if (!nazev.equals("chodba_do_nove_budovy") && !nazev.equals("recepce") && !nazev.equals("chodba_prizemi")) {
                    return "Nezapomněl jsem někomu něco vrátit?";
                }
            }

            plan.setAktualniProstor(sousedniProstor);
            return sousedniProstor.dlouhyPopis();
        }
    }
    
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
