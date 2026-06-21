package cz.vse.adventura.logika.prikazy;
import cz.vse.adventura.logika.*;
import cz.vse.adventura.logika.predmety.*;
import cz.vse.adventura.logika.prikazy.*;
import cz.vse.adventura.logika.npc.*;

import java.util.HashMap;
import java.util.Map;

public class SeznamPrikazu {
    private  Map<String,IPrikaz> mapaSPrikazy;
    
    public SeznamPrikazu() {
        mapaSPrikazy = new HashMap<>();
    }
    
    public void vlozPrikaz(IPrikaz prikaz) {
        mapaSPrikazy.put(prikaz.getNazev(),prikaz);
    }
    
    public IPrikaz vratPrikaz(String retezec) {
        if (mapaSPrikazy.containsKey(retezec)) {
            return mapaSPrikazy.get(retezec);
        }
        else {
            return null;
        }
    }

    public boolean jePlatnyPrikaz(String retezec) {
        return mapaSPrikazy.containsKey(retezec);
    }

    public String vratNazvyPrikazu() {
        String seznam = "";
        for (String slovoPrikazu : mapaSPrikazy.keySet()){
            seznam += slovoPrikazu + " ";
        }
        return seznam;
    }
}
