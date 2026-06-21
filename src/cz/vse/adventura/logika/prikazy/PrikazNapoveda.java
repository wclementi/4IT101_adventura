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
        return "Tvým úkolem je najít automat 'lokni si' a koupit si nápoj.\n"
        + "Najdi na škole bufet a prohledej okolí, jestli nemáš nějaké mince.\n"
        + "\n"
        + "Můžeš zadat tyto příkazy:\n"
        + platnePrikazy.vratNazvyPrikazu();
    }
    
    @Override
      public String getNazev() {
        return NAZEV;
     }
}
