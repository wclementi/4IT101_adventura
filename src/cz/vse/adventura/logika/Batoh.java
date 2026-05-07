package cz.vse.adventura.logika;

import java.util.HashMap;
import java.util.Map;

public class Batoh
{
    private int maximalniKapacita = 5;
    private int maximalniNosnost = 6;
    private Map<String, Vec> veci = new HashMap<>();

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
}
