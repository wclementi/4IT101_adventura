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

        String zakladDialogu = "(dominik): \"Nazdar! Co ty tady?\"\n" +
                "(ty): \"Zrovna jdu kolem, hledám něco k pití. Nemáš náhodou něco u sebe?\"\n" +
                "(dominik): \"Bohužel ne, už jsem dopil, pak si ale půjdu napustit.\"\n" +
                "(ty): \"Ty máš lahev? Myslíš, že bys mi ji mohl dát? Fakt umírám žízní...\"\n" +
                "(dominik): \"Asi ji budu potřebovat. Jestli bys mi půjčil učebnici na ekonomii, tak bych ti ji dal.\"\n";

        if (batoh.jeVecVBatohu("učebnice_ekonomie")) {
            try {
                batoh.odeberVec("učebnice_ekonomie");
                batoh.pridejVec(new Vec("prázdná_lahev", true, 1));
                dalLahev = true;
                return zakladDialogu +
                        "(ty): \"Super, zrovna mám jednu u sebe. Tady máš.\"\n" +
                        "(dominik): \"Díky moc! Tady je ta lahev.\"";
            } catch (Exception e) {
                return zakladDialogu + "(ty): \"Mám ji u sebe, ale mám teď nějak plný batoh...\"";
            }
        } else {
            return zakladDialogu +
                    "(ty): \"To zrovna nemám u sebe, ale myslím, že jsem ji tady někde viděl. Zkusím ji najít a kdyžtak přinesu.\"\n" +
                    "(dominik): \"To zní fajn, já počkám tady.\"";
        }
    }
}
