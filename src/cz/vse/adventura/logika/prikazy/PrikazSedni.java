package cz.vse.adventura.logika.prikazy;

import cz.vse.adventura.logika.*;

/**
 * Příkaz pro sednutí na židli nebo na zem.
 *
 * @author Kajetan Franciszek Wielgosz
 */
public class PrikazSedni implements IPrikaz {
    private static final String NAZEV = "sedni";
    private HerniPlan plan;
    private Hra hra;

    public PrikazSedni(HerniPlan plan, Hra hra) {
        this.plan = plan;
        this.hra = hra;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        Prostor aktualniProstor = plan.getAktualniProstor();

        if (!aktualniProstor.jeVecVProstoru("volná_sedacka")) {
            return "Tady si nemáš kam sednout.";
        }

        String nazevProstoru = aktualniProstor.getNazev();

        if (nazevProstoru.equals("likesova_aula")) {
            if (hra.isVypitoSavo()) {
                hra.setKonecHryS_Epilogem(StavHry.BAD_ENDING,
                        "Koho sakra napadne pit cokoliv z lahve od sava? očividně tebe… Jak velkou hloupost jsi udělal sis uvědomil teprve při otravě na zemi auly.");
            } else if (!hra.isUspokojenaZizen()) {
                hra.setKonecHryS_Epilogem(StavHry.BAD_ENDING,
                        "Během přednášky jsi se kvůli své žízni nemohl vůbec soustředit, po několika minutách jsi zkolaboval kvůli vážné dehydrataci.");
            } else if (hra.isUspokojenaZizen() && hra.isRozbityAutomat()) {
                hra.setKonecHryS_Epilogem(StavHry.CHAOTIC_ENDING,
                        "Uspokojil sis svojí žízeň a stihnul přednášku… ale za jakou cenu? Teď se do konce studia budeš ohlížet za záda jestli tě náhodou nechce zabít vrátný nebo uklízečka, kteří tam po tobě ten nepořádek uklidili.");
            } else {
                hra.setKonecHryS_Epilogem(StavHry.GOOD_ENDING,
                        "Uspokojil sis svojí žízeň a stihnul přednášku, další přežitý den na VŠE… teď jen dodělat zkouškoví a konečně si užít prázdniny.");
            }
            return "Posadil ses v Likešově aule.\n\n" + hra.vratEpilog();
        } else if (nazevProstoru.equals("vencovskeho_aula")) {
            if (hra.isVypitoSavo()) {
                hra.setKonecHryS_Epilogem(StavHry.BAD_ENDING,
                        "Koho sakra napadne pit cokoliv z lahve od sava? očividně tebe… Jak velkou hloupost jsi udělal sis uvědomil teprve při otravě na zemi auly.");
            } else {
                hra.setKonecHryS_Epilogem(StavHry.BAD_ENDING,
                        "Po usazení jsi zapnul mobil a prvních 30 minut přednášky jsi scroloval tiktok, poté jsi si najednou uvědomil, že jsi na špatné přednášce. Navíc přednáška, na které jsi měl být byla povinná a rozhodovala o tvé akademické budoucnosti. Po této realizaci jsi na místě zkolaboval kvůli záchvatu srdce.");
            }
            return "Posadil ses ve Vencovského aule.\n\n" + hra.vratEpilog();
        }

        return "Posadil ses, ale nic zvláštního se neděje.";
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }

    @Override
    public String getPouziti() {
        return "sedni <volná_sedacka/židle>";
    }
}
