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
     * Metoda pro vlozPrikaz.
     * @param prikaz parametr metody
     */
    public void vlozPrikaz(IPrikaz prikaz) {
        mapaSPrikazy.put(prikaz.getNazev(),prikaz);
    }
    
    /**
     * Metoda pro vratPrikaz.
     * @param retezec parametr metody
     * @return hodnota z metody
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
     * Metoda pro jePlatnyPrikaz.
     * @param retezec parametr metody
     * @return hodnota z metody
     */
    public boolean jePlatnyPrikaz(String retezec) {
        return mapaSPrikazy.containsKey(retezec);
    }

    /**
     * Metoda pro vratNazvyPrikazu.
     * @return hodnota z metody
     */
    public String vratNazvyPrikazu() {
        String seznam = "";
        for (String slovoPrikazu : mapaSPrikazy.keySet()){
            seznam += slovoPrikazu + " ";
        }
        return seznam;
    }
}
