package cz.vse.adventura.logika.prikazy;

import cz.vse.adventura.logika.Hra;

/**
 * Příkaz pro zobrazení historie herních kroků a dialogů.
 *
 * @author Kajetan Franciszek Wielgosz
 */
public class PrikazLog implements IPrikaz {
    private static final String NAZEV = "log";
    private Hra hra;

    public PrikazLog(Hra hra) {
        this.hra = hra;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        StringBuilder sb = new StringBuilder();
        sb.append("--- HISTORIE HRY ---\n");
        for (String zaznam : hra.getHistorie()) {
            sb.append(zaznam).append("\n");
        }
        sb.append("--------------------");
        return sb.toString();
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
