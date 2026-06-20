package cz.vse.adventura.logika.npc;

import cz.vse.adventura.logika.predmety.Batoh;
import cz.vse.adventura.logika.predmety.Vec;

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
            return "(typecka): \"Ještě jednou ahoj, uvidíme se na přednášce.\"";
        }

        if (pujcilaMobil) {
            if (batoh.jeVecVBatohu("telefon_s_aplikaci_lokni_si")) {
                if (!batoh.jeVecVBatohu("plná_lahev") && !batoh.jeVecVBatohu("plná_lahev_od_sava")) {
                    return "(typecka): \"Ještě sis nenatočil vodu? Tak šup, já tu počkám.\"";
                }
                try {
                    batoh.odeberVec("telefon_s_aplikaci_lokni_si");
                    vraceno = true;
                    return "(ty): \"Tady mas mobil, jeste jednou diky moc\"\n" +
                           "(typecka): \"Neni za co dekovat. Uvidime se na prednasce.\"";
                } catch (Exception e) {
                    return "(ty): \"Mám ten mobil, ale nejde mi ho teď vyndat z batohu...\"";
                }
            } else {
                return "(typecka): \"Kde máš ten mobil? Budu tady čekat, pospěš si.\"";
            }
        }

        String zakladDialogu = "(ty): \"Čau, poznávám tě z přednášek. Na co tady čekáš?\"\n" +
                "(typecka): \"Ahooj, čekám tady na kamarádku, zachvíli jdem na přednášku.\"\n" +
                "(ty): \"To asi jden na stejnou, ekonomie zejo?\"\n" +
                "(typecka): \"Presne tak\"\n" +
                "(ty): \"Nepamatuješ si náhodou v jaké místnoti bude?\"\n" +
                "(typecka): \"V likešově aule, ukazuje mi to tady na mobilu v My College aplikaci.\"\n";

        if (batoh.jeVecVBatohu("prázdná_lahev") || batoh.jeVecVBatohu("prázdná_lahev_od_sava") || batoh.jeVecVBatohu("nápoj")) {
            try {
                batoh.pridejVec(new Vec("telefon_s_aplikaci_lokni_si", true, 1));
                pujcilaMobil = true;
                return zakladDialogu +
                        "(ty): \"Myslíš že bys mi mohla půjčit mobil s apkou na tocení vody? mám strašnou žízeň a zrovna se mi vybil mobil\"\n" +
                        "(typecka): \"Urcite tady mas jen mi ho prosim rychle vrať.\"\n" +
                        "(ty): \" Jasny, díky moc. Hned se vrátím.\"\n" +
                        "(typecka): \"Budu tady cekat)\"";
            } catch (Exception e) {
                return zakladDialogu + "(ty): \"Máš mobil s aplikací? Škoda, že mám teď tak plný batoh...\"";
            }
        } else {
            return zakladDialogu;
        }
    }
}
