package cz.vse.adventura.logika.npc;

import cz.vse.adventura.logika.predmety.Batoh;
import cz.vse.adventura.logika.predmety.Vec;

/**
 * Třída představující specifické NPC - Dominika, který vyžaduje předmět.
 *
 * @author Kajetan Franciszek Wielgosz
 */
public class NPCDominik extends NPCPouzeMluvici {
    private boolean dalLahev = false;

    public NPCDominik(String jmeno) {
        super(jmeno, ""); // Text se bude generovat dynamicky
    }

    @Override
    public String mluv() {
        Batoh batoh = Batoh.getInstance();

        if (dalLahev) {
            return "(dominik): \"Díky ještě jednou za tu učebnici!\"";
        }

        String zakladDialogu = "(dominik): \"Nazdar! co ty tady?\"\n" +
                "(ty): \"Zrovna jdu kolem, hledam neco k piti. Nemas nahodou neco u sebe?\"\n" +
                "(dominik): \"Bohuzel ne, uz jsem vypil pak si pujdu ale napustit.\"\n" +
                "(ty): \"Ty mas lahev? myslis ze bys mi ji mohl dat? Fakt umiram zizni…\"\n" +
                "(dominik): \"Asi ji budu potrebovat, jestli bys mi pujcil ucebnici na ekonomii tak bych ti ji dal\"\n";

        if (batoh.jeVecVBatohu("učebnice_ekonomie")) {
            try {
                batoh.odeberVec("učebnice_ekonomie");
                batoh.pridejVec(new Vec("prázdná_lahev", true, 1));
                dalLahev = true;
                return zakladDialogu +
                        "(ty): \"Super, zrovna mám jednu u sebe. Tady máš\"\n" +
                        "(dominik): \"Diky moc! Tady je ta lahev.\"";
            } catch (Exception e) {
                return zakladDialogu + "(ty): \"Mám ji u sebe, ale mám teď nějak plný batoh...\"";
            }
        } else {
            return zakladDialogu +
                    "(ty): \"To zrovna nemam u sebe ale myslim ze jsem ji tady nekde videl, zkusim ji najit a kdyztak prinesu.\"\n" +
                    "(dominik): \"To zni fajn, ja pockam tady. \"";
        }
    }
}
