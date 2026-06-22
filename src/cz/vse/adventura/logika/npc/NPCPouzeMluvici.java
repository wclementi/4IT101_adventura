package cz.vse.adventura.logika.npc;

// ukol 12. tyden: Dědičnost
/**
 * Základní třída pro postavy, se kterými lze pouze komunikovat.
 *
 * @author Kajetan Franciszek Wielgosz
 */
public class NPCPouzeMluvici {
    protected String jmeno;
    protected String jmenoBD;
    protected String text;

    public NPCPouzeMluvici(String jmeno, String text) {
        this.jmeno = jmeno;
        this.jmenoBD = cz.vse.adventura.logika.TextUtil.odstranDiakritiku(jmeno);
        this.text = text;
    }

    /**
     * Vrátí jméno postavy.
     * @return jméno postavy
     */
    public String getJmeno() {
        return jmeno;
    }

    /**
     * Vrátí jméno postavy bez diakritiky (převedené na malá písmena).
     * @return jméno postavy bez diakritiky
     */
    public String getJmenoBD() {
        return jmenoBD;
    }

    /**
     * Vrátí text dialogu této postavy.
     * @return text dialogu
     */
    public String mluv() {
        return text;
    }
}
