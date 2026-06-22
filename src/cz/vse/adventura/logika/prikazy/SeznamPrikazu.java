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
     * @param prikaz instance příkazu, který chceme přidat
     */
    public void vlozPrikaz(IPrikaz prikaz) {
        mapaSPrikazy.put(prikaz.getNazev(),prikaz);
    }
    
    /**
     * Vrátí odkaz na instanci příkazu podle zadaného názvu.
     * @param retezec název příkazu (s diakritikou i bez ní)
     * @return instance příkazu, nebo null, pokud příkaz neexistuje
     */
    public IPrikaz vratPrikaz(String retezec) {
        return mapaSPrikazy.values().stream()
                .filter(p -> p.getNazev().equalsIgnoreCase(retezec) || p.getNazevBD().equalsIgnoreCase(retezec))
                .findFirst()
                .orElse(null);
    }

    /**
     * Zjišťuje, zda je zadané slovo platným příkazem.
     * @param retezec testované slovo
     * @return true, pokud je příkaz platný, jinak false
     */
    public boolean jePlatnyPrikaz(String retezec) {
        return vratPrikaz(retezec) != null;
    }

    /**
     * Vrátí seznam názvů všech platných příkazů oddělených mezerou.
     * @return řetězec s názvy příkazů
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
