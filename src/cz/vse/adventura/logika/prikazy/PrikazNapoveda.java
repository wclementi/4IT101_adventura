package cz.vse.adventura.logika.prikazy;

/**
 * Příkaz pro zobrazení nápovědy a seznamu příkazů.
 *
 * @author Kajetan Franciszek Wielgosz
 */
public class PrikazNapoveda implements IPrikaz {
    
    private static final String NAZEV = "nápověda";
    private SeznamPrikazu platnePrikazy;
    
    public PrikazNapoveda(SeznamPrikazu platnePrikazy) {
        this.platnePrikazy = platnePrikazy;
    }
    
    @Override
    public String provedPrikaz(String... parametry) {
        StringBuilder sb = new StringBuilder();
        sb.append("Tvým úkolem je najít automat 'lokni si' a koupit si nápoj.\n");
        sb.append("Najdi na škole bufet a prohledej okolí, jestli nemáš nějaké mince.\n\n");
        sb.append("Můžeš zadat tyto příkazy:\n");
        for (IPrikaz prikaz : platnePrikazy.vratVsechnyPrikazy()) {
            sb.append("  ").append(prikaz.getPouziti()).append("\n");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }
    
    @Override
    public String getNazev() {
        return NAZEV;
    }

    @Override
    public String getPouziti() {
        return "nápověda";
    }
}
