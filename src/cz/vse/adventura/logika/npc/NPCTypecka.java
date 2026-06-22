package cz.vse.adventura.logika.npc;

import cz.vse.adventura.logika.predmety.Batoh;
import cz.vse.adventura.logika.predmety.Vec;

/**
 * Třída představující specifické NPC - Typečku, která půjčuje telefon.
 *
 * @author Kajetan Franciszek Wielgosz
 */
public class NPCTypecka extends NPCPouzeMluvici {
    private boolean pujcilaMobil = false;
    private boolean vraceno = false;

    public NPCTypecka(String jmeno) {
        super(jmeno, "");
    }

    @Override
    public String mluv() {
        Batoh batoh = Batoh.getInstance();

        if (vraceno) {
            return "(týpečka): \"Ještě jednou ahoj, uvidíme se na přednášce.\"";
        }

        if (pujcilaMobil) {
            if (batoh.jeVecVBatohu("telefon_s_aplikací_lokni_si")) {
                if (!batoh.isTelefonPouzit()) {
                    return "(týpečka): \"Ještě sis nenatočil vodu? Tak šup, já tu počkám.\"";
                }
                try {
                    batoh.odeberVec("telefon_s_aplikací_lokni_si");
                    vraceno = true;
                    return "(ty): \"Tady máš mobil, ještě jednou díky moc.\"\n" +
                           "(týpečka): \"Není za co děkovat. Uvidíme se na přednášce.\"";
                } catch (Exception e) {
                    return "(ty): \"Mám ten mobil, ale nejde mi ho teď vyndat z batohu...\"";
                }
            } else {
                return "(týpečka): \"Kde máš ten mobil? Budu tady čekat, pospěš si.\"";
            }
        }

        String zakladDialogu = "(ty): \"Čau, poznávám tě z přednášek. Na co tady čekáš?\"\n" +
                "(týpečka): \"Ahoj, čekám tady na kamarádku, za chvíli jdeme na přednášku.\"\n" +
                "(ty): \"To asi jdeme na stejnou, ekonomie, že jo?\"\n" +
                "(týpečka): \"Přesně tak.\"\n" +
                "(ty): \"Nepamatuješ si náhodou, v jaké místnosti bude?\"\n" +
                "(týpečka): \"V Likešově aule, ukazuje mi to tady na mobilu v aplikaci My College.\"\n";

        if (batoh.jeVecVBatohu("prázdná_lahev") || batoh.jeVecVBatohu("prázdná_lahev_od_Sava") || batoh.jeVecVBatohu("nápoj")) {
            try {
                batoh.pridejVec(new Vec("telefon_s_aplikací_lokni_si", true, 1));
                pujcilaMobil = true;
                return zakladDialogu +
                        "(ty): \"Myslíš, že bys mi mohla půjčit mobil s aplikací na točení vody? Mám strašnou žízeň a zrovna se mi vybil mobil.\"\n" +
                        "(týpečka): \"Určitě, tady máš, jen mi ho prosím rychle vrať.\"\n" +
                        "(ty): \"Jasně, díky moc. Hned se vrátím.\"\n" +
                        "(týpečka): \"Budu tady čekat.\"";
            } catch (Exception e) {
                return zakladDialogu + "(ty): \"Máš mobil s aplikací? Škoda, že mám teď tak plný batoh...\"";
            }
        } else {
            return zakladDialogu;
        }
    }
}
