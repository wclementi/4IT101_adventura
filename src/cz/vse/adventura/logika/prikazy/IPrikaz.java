package cz.vse.adventura.logika.prikazy;

/**
 * Rozhraní, které musí implementovat všechny příkazy ve hře.
 *
 * @author Kajetan Franciszek Wielgosz
 */
public interface IPrikaz {
    public String provedPrikaz(String... parametry);
    public String getNazev();
    public String getPouziti();
}
