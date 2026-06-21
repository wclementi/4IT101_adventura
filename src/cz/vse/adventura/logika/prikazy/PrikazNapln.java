package cz.vse.adventura.logika.prikazy;

import cz.vse.adventura.logika.*;
import cz.vse.adventura.logika.predmety.*;

/**
 * Příkaz pro naplnění lahve vodou z automatu Lokni si.
 *
 * @author Kajetan Franciszek Wielgosz
 */
public class PrikazNapln implements IPrikaz {
    private static final String NAZEV = "naplň";
    private HerniPlan plan;

    public PrikazNapln(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co mám naplnit? (Zadej např. 'naplň prázdná_lahev')";
        }

        String nazevLahve = parametry[0];

        if (!plan.getAktualniProstor().jeVecVProstoru("LOKNI_SI_automat")) {
            return "Tady není žádný automat LOKNI SI, ze kterého by se dalo natáčet.";
        }

        Batoh batoh = Batoh.getInstance();

        if (!batoh.jeVecVBatohu("telefon_s_aplikaci_lokni_si")) {
            return "K použití LOKNI SI automatu potřebuješ mobil s nainstalovanou aplikací.";
        }

        if (!batoh.jeVecVBatohu(nazevLahve)) {
            return "Takovou věc v batohu nemáš.";
        }

        if (nazevLahve.equals("prázdná_lahev")) {
            try {
                batoh.odeberVec("prázdná_lahev");
                batoh.pridejVec(new Vec("plná_lahev", true, 2));
                batoh.setTelefonPouzit(true);
                return "Přiložil jsi telefon k automatu a naplnil jsi prázdnou lahev vodou.";
            } catch (Exception e) {
                return "Máš moc plný batoh, vodu nelze napustit.";
            }
        } else if (nazevLahve.equals("prázdná_lahev_od_sava")) {
            try {
                batoh.odeberVec("prázdná_lahev_od_sava");
                batoh.pridejVec(new Vec("plná_lahev_od_sava", true, 2));
                batoh.setTelefonPouzit(true);
                return "Přiložil jsi telefon k automatu a naplnil jsi prázdnou lahev od sava vodou. (Smrdí to divně...)";
            } catch (Exception e) {
                return "Máš moc plný batoh, vodu nelze napustit.";
            }
        } else {
            return "Tohle se nedá naplnit vodou z automatu.";
        }
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
