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
        if (mapaSPrikazy.containsKey(retezec)) {
            return mapaSPrikazy.get(retezec);
        }
        else {
            return null;
        }
    }

    /**
     * Zjišťuje, zda je zadané slovo platným příkazem.
     * @param retezec požadovaný vstup retezec
     * @return logický výsledek volání
     */
    public boolean jePlatnyPrikaz(String retezec) {
        return mapaSPrikazy.containsKey(retezec);
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
}
