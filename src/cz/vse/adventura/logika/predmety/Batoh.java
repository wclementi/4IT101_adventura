package cz.vse.adventura.logika.predmety;

import java.util.HashMap;
import java.util.Map;

// ukol 12. tyden: Singleton pro batoh
public class Batoh
{
    private static Batoh instance;

    private int maximalniKapacita = 5;
    private int maximalniNosnost = 6;
    private Map<String, Vec> veci = new HashMap<>();
    private boolean telefonPouzit = false;

    private Batoh() {}

    public static Batoh getInstance() {
        if (instance == null) {
            instance = new Batoh();
        }
        return instance;
    }

    public boolean jeVecVBatohu(String nazev)
    {
        return veci.containsKey(nazev);
    }

    public Vec getVec(String nazev) throws Exception
    {
        if(!jeVecVBatohu(nazev)){
            throw new Exception("V batohu se tato vec nenachazi.");
        }else{
            return veci.get(nazev);
        }
    }

    private int getHmotnost()
    {
        int hmotnost = 0;
        for(Vec vec : veci.values())
        {
            hmotnost = hmotnost + vec.getHmotnost();
        }
        return hmotnost;
    }

    public Vec odeberVec(String nazev) throws Exception
    {
        if(!jeVecVBatohu(nazev)){
            throw new Exception("Tato vec neni v batohu");
        }else {
            return veci.remove(nazev);
        }
    }

    public Vec pridejVec(Vec vec) throws Exception
    {
        if(jeVecVBatohu(vec.getNazev()))
        {
            throw new Exception("Tato vec jiz v batohu je");
        }
        else if(maximalniKapacita < veci.size() + 1)
        {
            throw new Exception("Max kapacita nesmi byt prekrocena");
        }
        if(maximalniNosnost < getHmotnost() + vec.getHmotnost())
        {
            throw new Exception("Nosnost nesmi byt prekrocena");
        }
        return veci.put(vec.getNazev(), vec);
    }

    public String getPopisObsahu() {
        if (veci.isEmpty()) {
            return "Batoh je prázdný.";
        }
        String vracenyText = "Věci v batohu:";
        for (String nazev : veci.keySet()) {
            vracenyText += " " + nazev;
        }
        return vracenyText;
    }

    public void vymazBatoh() {
        veci.clear();
        telefonPouzit = false;
    }

    public boolean isTelefonPouzit() {
        return telefonPouzit;
    }

    public void setTelefonPouzit(boolean telefonPouzit) {
        this.telefonPouzit = telefonPouzit;
    }
}
