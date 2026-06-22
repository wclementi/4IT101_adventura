package cz.vse.adventura.logika.npc;

import cz.vse.adventura.logika.predmety.Batoh;
import cz.vse.adventura.logika.predmety.Vec;

/**
 * Třída představující specifické NPC - Spolužačku, která půjčuje telefon.
 *
 * @author Kajetan Franciszek Wielgosz
 */
public class NPCSpoluzacka extends NPCPouzeMluvici {
    private boolean pujcilaMobil = false;
    private boolean vraceno = false;

    public NPCSpoluzacka(String jmeno) {
        super(jmeno, "");
    }

    @Override
    public String mluv() {
        Batoh batoh = Batoh.getInstance();

        if (vraceno) {
            return "(Spolužačka): \"Ještě jednou ahoj, uvidíme se na přednášce.\"";
        }

        if (pujcilaMobil) {
            if (batoh.jeVecVBatohu("telefon_s_aplikací_lokni_si")) {
                if (!batoh.isTelefonPouzit()) {
                    return "(Spolužačka): \"Ještě sis nenatočil vodu? Tak pospěš, já tu počkám.\"";
                }
                try {
                    batoh.odeberVec("telefon_s_aplikací_lokni_si");
                    vraceno = true;
                    return "(ty): \"Tady je ten mobil, ještě jednou ti moc děkuji.\"\n" +
                           "(Spolužačka): \"Nemáš za co děkovat. Uvidíme se na přednášce.\"";
                } catch (Exception e) {
                    return "(ty): \"Mám ten mobil, ale nedaří se mi ho teď vytáhnout z batohu...\"";
                }
            } else {
                return "(Spolužačka): \"Kde máš ten mobil? Budu zde čekat, pospěš si.\"";
            }
        }

        String zakladDialogu = "(ty): \"Ahoj, poznávám tě z přednášek. Na co zde čekáš?\"\n" +
                "(Spolužačka): \"Ahoj, čekám zde na kamarádku, za chvíli jdeme na přednášku.\"\n" +
                "(ty): \"To jdeme pravděpodobně na stejnou přednášku. Na ekonomii, že ano?\"\n" +
                "(Spolužačka): \"Přesně tak.\"\n" +
                "(ty): \"Nepamatuješ si náhodou, v jaké místnosti se koná?\"\n" +
                "(Spolužačka): \"V Likešově aule, ukazuje mi to zde v mobilní aplikaci My College.\"\n";

        if (batoh.jeVecVBatohu("prázdná_lahev") || batoh.jeVecVBatohu("prázdná_lahev_od_Sava") || batoh.jeVecVBatohu("nápoj")) {
            try {
                batoh.pridejVec(new Vec("telefon_s_aplikací_lokni_si", true, 1));
                pujcilaMobil = true;
                return zakladDialogu +
                        "(ty): \"Myslíš, že bys mi mohla půjčit svůj mobil s aplikací na točení vody? Mám strašnou žízeň a zrovna se mi vybil telefon.\"\n" +
                        "(Spolužačka): \"Určitě, zde je, jen mi ho prosím brzy vrať.\"\n" +
                        "(ty): \"Rozumím, mockrát děkuji. Hned se vrátím.\"\n" +
                        "(Spolužačka): \"Budu zde čekat.\"";
            } catch (Exception e) {
                return zakladDialogu + "(ty): \"Máš mobil s aplikací? Škoda, že mám nyní tak plný batoh...\"";
            }
        } else {
            return zakladDialogu;
        }
    }
}
