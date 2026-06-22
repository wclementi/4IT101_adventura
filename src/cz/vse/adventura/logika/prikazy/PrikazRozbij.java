package cz.vse.adventura.logika.prikazy;

import cz.vse.adventura.logika.*;
import cz.vse.adventura.logika.predmety.*;

/**
 * Příkaz pro rozbití zadaného předmětu.
 *
 * @author Kajetan Franciszek Wielgosz
 */
public class PrikazRozbij implements IPrikaz {
    private static final String NAZEV = "rozbij";
    private HerniPlan plan;
    private Hra hra;

    public PrikazRozbij(HerniPlan plan, Hra hra) {
        this.plan = plan;
        this.hra = hra;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co mám rozbít?";
        }

        String nazev = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();

        if (!aktualniProstor.jeVecVProstoru(nazev)) {
            return "Tady žádné '" + nazev + "' k rozbití není.";
        }

        if (!Batoh.getInstance().jeVecVBatohu("páčidlo")) {
            return "Nemáš čím to rozbít. Hodilo by se nějaké nářadí.";
        }

        String cistyNazev = cz.vse.adventura.logika.TextUtil.odstranDiakritiku(nazev);

        if (cistyNazev.equals("automat") || cistyNazev.equals("napojovy_automat") || cistyNazev.equals("lokni_si_automat")) {
            hra.setRozbityAutomat(true);
            aktualniProstor.odeberVec(aktualniProstor.getVec(nazev));
            aktualniProstor.pridejVec(new Vec("nápoj", true, 2));
            return "Použil jsi páčidlo a rozbil jsi " + nazev + ". Z automatu vypadl 'nápoj'.";
        }

        return "To raději rozbíjet nebudu.";
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }

    @Override
    public String getPouziti() {
        return "rozbij <název předmětu>";
    }
}
