package cz.vse.adventura.logika.prikazy;
import cz.vse.adventura.logika.*;
import cz.vse.adventura.logika.predmety.*;

/**
 * Příkaz pro přesun do sousední místnosti.
 *
 * @author Kajetan Franciszek Wielgosz
 */
public class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "jdi";
    private HerniPlan plan;
    private Hra hra;
    
    public PrikazJdi(HerniPlan plan, Hra hra) {
        this.plan = plan;
        this.hra = hra;
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
            if (sousedniProstor.getNazev().equals("vychod_z_VSE")) {
                hra.setKonecHryS_Epilogem(StavHry.CHAOTIC_ENDING, "“Stejně nejsem studyjní typ” řekl sis cestou ven z VŠE.");
                return sousedniProstor.dlouhyPopis() + "\n\n" + hra.vratEpilog();
            }
            return sousedniProstor.dlouhyPopis();
        }
    }
    
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
