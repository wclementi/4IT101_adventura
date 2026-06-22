package cz.vse.adventura.logika.prikazy;

import java.util.HashMap;
import java.util.Map;

/**
 * Správce platných příkazů. Udržuje mapu příkazů a provádí je.
 *
 * @author Kajetan Franciszek Wielgosz
 */
public class SeznamPrikazu {
    private  Map<String,IPrikaz> mapaSPrikazy;
    
    public SeznamPrikazu() {
        mapaSPrikazy = new HashMap<>();
    }
    
    /**
     * Vloží nový platný příkaz do seznamu příkazů.
     * @param prikaz požadovaný vstup prikaz
     */
    public void vlozPrikaz(IPrikaz prikaz) {
        mapaSPrikazy.put(prikaz.getNazev(),prikaz);
    }
    
    /**
     * Vrátí odkaz na instanci příkazu podle zadaného názvu.
     * @param retezec požadovaný vstup retezec
     * @return logický výsledek volání
     */
    public IPrikaz vratPrikaz(String retezec) {
        return mapaSPrikazy.values().stream()
                .filter(p -> p.getNazev().equalsIgnoreCase(retezec) || p.getNazevBD().equalsIgnoreCase(retezec))
                .findFirst()
                .orElse(null);
    }

    /**
     * Zjišťuje, zda je zadané slovo platným příkazem.
     * @param retezec požadovaný vstup retezec
     * @return logický výsledek volání
     */
    public boolean jePlatnyPrikaz(String retezec) {
        return vratPrikaz(retezec) != null;
    }

    /**
     * Metoda pro vratNazvyPrikazu.
     * @return logický výsledek volání
     */
    public String vratNazvyPrikazu() {
        String seznam = "";
        for (String slovoPrikazu : mapaSPrikazy.keySet()){
            seznam += slovoPrikazu + " ";
        }
        return seznam;
    }
    
    /**
     * Vrátí kolekci všech platných příkazů.
     * @return kolekce implementací IPrikaz
     */
    public java.util.Collection<IPrikaz> vratVsechnyPrikazy() {
        return mapaSPrikazy.values();
    }
}
