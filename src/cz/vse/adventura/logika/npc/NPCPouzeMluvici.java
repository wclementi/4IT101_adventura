package cz.vse.adventura.logika.npc;

// ukol 12. tyden: Dědičnost
/**
 * Základní třída pro postavy, se kterými lze pouze komunikovat.
 *
 * @author Kajetan Franciszek Wielgosz
 */
public class NPCPouzeMluvici {
    protected String jmeno;
    protected String text;

    public NPCPouzeMluvici(String jmeno, String text) {
        this.jmeno = jmeno;
        this.text = text;
    }

    /**
     * Metoda pro getJmeno.
     * @return logický výsledek volání
     */
    public String getJmeno() {
        return jmeno;
    }

    /**
     * Vrátí text dialogu této postavy.
     * @return logický výsledek volání
     */
    public String mluv() {
        return text;
    }
}
