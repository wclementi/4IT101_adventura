package cz.vse.adventura.logika.prikazy;

/**
 * Rozhraní, které musí implementovat všechny příkazy ve hře.
 *
 * @author Kajetan Franciszek Wielgosz
 */
public interface IPrikaz {
    /**
     * Provede příkaz s danými parametry a vrátí textový výsledek pro hráče.
     * @param parametry parametry příkazu zadané uživatelem
     * @return výsledek provedení příkazu, který se vypíše na konzoli
     */
    public String provedPrikaz(String... parametry);

    /**
     * Vrátí název příkazu (klíčové slovo, např. "jdi").
     * @return název příkazu
     */
    public String getNazev();

    /**
     * Vrátí nápovědu k použití příkazu (např. "jdi <prostor>").
     * @return použití příkazu
     */
    public String getPouziti();

    /**
     * Vrátí název příkazu bez diakritiky.
     * @return název příkazu bez diakritiky
     */
    default String getNazevBD() {
        return cz.vse.adventura.logika.TextUtil.odstranDiakritiku(getNazev());
    }
}
