package cz.vse.adventura.logika.predmety;

import java.util.HashMap;
import java.util.Map;

// ukol 12. tyden: Singleton pro batoh
/**
 * Třída představující hráčův inventář s omezenou kapacitou.
 *
 * @author Kajetan Franciszek Wielgosz
 */
public class Batoh
{
    private static Batoh instance;

    private int maximalniKapacita = 5;
    private int maximalniNosnost = 6;
    private Map<String, Vec> veci = new HashMap<>();
    private boolean telefonPouzit = false;

    private Batoh() {}

    /**
     * Vrátí jedinou (singleton) instanci batohu.
     * @return logický výsledek volání
     */
    public static Batoh getInstance() {
        if (instance == null) {
            instance = new Batoh();
        }
        return instance;
    }

    /**
     * Zjišťuje, zda se v batohu nachází předmět daného jména.
     * @param nazev hledaný název
     * @return logický výsledek volání
     */
    public boolean jeVecVBatohu(String nazev)
    {
        return veci.values().stream()
                .anyMatch(v -> v.getNazev().equalsIgnoreCase(nazev) || v.getNazevBD().equalsIgnoreCase(nazev));
    }

    /**
     * Vrátí odkaz na předmět zadaného jména, který se nachází v prostoru (nebo batohu).
     * @param nazev hledaný název
     * @return logický výsledek volání
     */
    public Vec getVec(String nazev) throws Exception
    {
        Vec found = veci.values().stream()
                .filter(v -> v.getNazev().equalsIgnoreCase(nazev) || v.getNazevBD().equalsIgnoreCase(nazev))
                .findFirst()
                .orElse(null);
        if(found == null){
            throw new Exception("V batohu se tato věc nenachází.");
        }else{
            return found;
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

    /**
     * Odebere předmět zadaného jména z prostoru (nebo batohu) a vrátí na něj odkaz.
     * @param nazev hledaný název
     * @return logický výsledek volání
     */
    public Vec odeberVec(String nazev) throws Exception
    {
        Vec found = veci.values().stream()
                .filter(v -> v.getNazev().equalsIgnoreCase(nazev) || v.getNazevBD().equalsIgnoreCase(nazev))
                .findFirst()
                .orElse(null);
        if(found == null){
            throw new Exception("Tato věc není v batohu.");
        }else {
            return veci.remove(found.getNazev());
        }
    }

    /**
     * Přidá zadaný předmět do prostoru (nebo batohu).
     * @param vec požadovaný vstup vec
     * @return logický výsledek volání
     */
    public Vec pridejVec(Vec vec) throws Exception
    {
        if(jeVecVBatohu(vec.getNazev()))
        {
            throw new Exception("Tato věc již v batohu je.");
        }
        else if(maximalniKapacita < veci.size() + 1)
        {
            throw new Exception("Maximální kapacita nesmí být překročena.");
        }
        if(maximalniNosnost < getHmotnost() + vec.getHmotnost())
        {
            throw new Exception("Nosnost nesmí být překročena.");
        }
        return veci.put(vec.getNazev(), vec);
    }

    /**
     * Vrátí textový výpis aktuálního obsahu batohu.
     * @return logický výsledek volání
     */
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

    /**
     * Kompletně vyprázdní batoh (slouží zejména pro testování).
     */
    public void vymazBatoh() {
        veci.clear();
        telefonPouzit = false;
    }

    /**
     * Zjišťuje, zda již hráč použil telefon u automatu.
     * @return logický výsledek volání
     */
    public boolean isTelefonPouzit() {
        return telefonPouzit;
    }

    /**
     * Nastaví příznak, zda byl telefon u automatu použit.
     * @param telefonPouzit požadovaný vstup telefonPouzit
     */
    public void setTelefonPouzit(boolean telefonPouzit) {
        this.telefonPouzit = telefonPouzit;
    }
}
