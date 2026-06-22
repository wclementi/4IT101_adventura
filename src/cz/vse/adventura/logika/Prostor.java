package cz.vse.adventura.logika;
import cz.vse.adventura.logika.predmety.*;
import cz.vse.adventura.logika.npc.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Představuje jednu místnost (lokaci) ve hře. Může obsahovat východy, předměty a postavy.
 *
 * @author Kajetan Franciszek Wielgosz
 */
public class Prostor {

    private String nazev;
    private String nazevBD;
    private String popis;
    private Set<Prostor> vychody;
    private Map<String, Vec> veci;
    private Map<String, NPCPouzeMluvici> postavy;

    public Prostor(String nazev, String popis) {
        this.nazev = nazev;
        this.nazevBD = cz.vse.adventura.logika.TextUtil.odstranDiakritiku(nazev);
        this.popis = popis;
        vychody = new HashSet<>();
        veci = new HashMap<>();
        postavy = new HashMap<>();
    }

    /**
     * Zjišťuje, zda se v prostoru nachází předmět daného názvu.
     * @param nazev hledaný název předmětu
     * @return true, pokud se předmět v prostoru nachází, jinak false
     */
    public boolean jeVecVProstoru(String nazev)
    {
        return getVec(nazev) != null;
    }

    /**
     * Vrátí odkaz na předmět zadaného názvu, který se nachází v tomto prostoru.
     * @param nazev hledaný název předmětu
     * @return předmět zadaného názvu, nebo null, pokud se v prostoru nenachází
     */
    public Vec getVec(String nazev)
    {
        return veci.values().stream()
                .filter(v -> v.getNazev().equalsIgnoreCase(nazev) || v.getNazevBD().equalsIgnoreCase(nazev))
                .findFirst()
                .orElse(null);
    }

    /**
     * Přidá zadaný předmět do tohoto prostoru.
     * @param vec předmět, který má být přidán
     * @return přidaný předmět (nebo již existující předmět se stejným názvem)
     */
    public Vec pridejVec(Vec vec)
    {
        if(jeVecVProstoru(vec.getNazev()))
        {
            return getVec(vec.getNazev());
        }

        return veci.put(vec.getNazev(), vec);
    }

    /**
     * Odebere předmět zadaného jména z tohoto prostoru a vrátí na něj odkaz.
     * @param vec předmět k odebrání
     * @return odebraný předmět
     */
    public Vec odeberVec(Vec vec)
    {
        return veci.remove(vec.getNazev());
    }

    /**
     * Přidá zadanou postavu (NPC) do tohoto prostoru.
     * @param postava postava, která má být přidána
     */
    public void pridejPostavu(NPCPouzeMluvici postava) {
        postavy.put(postava.getJmeno(), postava);
    }

    /**
     * Vrátí odkaz na postavu zadaného jména v tomto prostoru.
     * @param jmeno jméno hledané postavy
     * @return hledaná postava, nebo null, pokud se v prostoru nenachází
     */
    public NPCPouzeMluvici getPostava(String jmeno) {
        return postavy.values().stream()
                .filter(p -> p.getJmeno().equalsIgnoreCase(jmeno) || p.getJmenoBD().equalsIgnoreCase(jmeno))
                .findFirst()
                .orElse(null);
    }

    /**
     * Přidá nový východ (sousední prostor) k tomuto prostoru.
     * @param vedlejsi sousední prostor
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }
  
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Prostor)) {
            return false;
        }
        Prostor druhy = (Prostor) o;

       return (java.util.Objects.equals(this.nazev, druhy.nazev));       
    }

    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }

    /**
     * Vrátí název tohoto prostoru.
     * @return název prostoru
     */
    public String getNazev() {
        return nazev;       
    }

    /**
     * Vrátí název prostoru bez diakritiky.
     * @return název prostoru bez diakritiky (převedený na malá písmena)
     */
    public String getNazevBD() {
        return nazevBD;
    }

    /**
     * Vrátí dlouhý popis prostoru včetně seznamu východů, věcí a postav.
     * @return kompletní popis prostoru
     */
    public String dlouhyPopis() {
        return "Jsi v místnosti/prostoru " + popis + ".\n"
                + popisVychodu() + "\n"
                + popisVeci() + "\n"
                + popisPostav();
    }

    private String popisVychodu() {
        String vracenyText = "východy:";
        for (Prostor sousedni : vychody) {
            vracenyText += " " + sousedni.getNazev();
        }
        return vracenyText;
    }

    private String popisVeci() {
        String vracenyText = "věci:";
        for(Vec vec : veci.values())
        {
            vracenyText += " " + vec.getNazev();
        }
        return vracenyText;
    }

    private String popisPostav() {
        String vracenyText = "postavy:";
        for (String jmenoPostavy : postavy.keySet()) {
            vracenyText += " " + jmenoPostavy;
        }
        return vracenyText;
    }

    /**
     * Vrátí odkaz na sousední prostor zadaného názvu, pokud s ním sousedí.
     * @param nazevSouseda název sousedního prostoru
     * @return sousední prostor, nebo null, pokud neexistuje
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        List<Prostor>hledaneProstory = 
            vychody.stream()
                   .filter(sousedni -> sousedni.getNazev().equalsIgnoreCase(nazevSouseda) || sousedni.getNazevBD().equalsIgnoreCase(nazevSouseda))
                   .collect(Collectors.toList());
        if(hledaneProstory.isEmpty()){
            return null;
        }
        else {
            return hledaneProstory.get(0);
        }
    }

    /**
     * Vrátí kolekci všech prostorů, do kterých lze z aktuálního prostoru odejít.
     * @return kolekce sousedních prostorů
     */
    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }
}
