package cz.vse.adventura.logika;

// ukol 12. tyden: Dědičnost
public class NPCPouzeMluvici {
    protected String jmeno;
    protected String text;

    public NPCPouzeMluvici(String jmeno, String text) {
        this.jmeno = jmeno;
        this.text = text;
    }

    public String getJmeno() {
        return jmeno;
    }

    public String mluv() {
        return jmeno + " říká: \"" + text + "\"";
    }
}