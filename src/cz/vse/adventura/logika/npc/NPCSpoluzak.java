package cz.vse.adventura.logika.npc;
import cz.vse.adventura.logika.predmety.*;

// ukol 12. tyden: Dědičnost
/**
 * Třída představující specifické NPC - Spolužáka, který dává mince.
 *
 * @author Kajetan Franciszek Wielgosz
 */
public class NPCSpoluzak extends NPCPouzeMluvici {
    private boolean dalPenize;

    public NPCSpoluzak(String jmeno, String text) {
        super(jmeno, text);
        this.dalPenize = false;
    }

    /**
     * Metoda pro dejPenize.
     * @param batoh parametr metody
     * @return hodnota z metody
     */
    public boolean dejPenize(Batoh batoh) {
        if (!dalPenize) {
            try {
                batoh.pridejVec(new Vec("mince", true, 1));
                dalPenize = true;
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public String mluv() {
        if (dalPenize) {
            return jmeno + " říká: \"Už jsem ti peníze dal, víc nemám!\"";
        }
        return super.mluv();
    }
}
