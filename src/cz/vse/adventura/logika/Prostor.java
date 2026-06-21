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
    private String popis;
    private Set<Prostor> vychody;
    private Map<String, Vec> veci;
    private Map<String, NPCPouzeMluvici> postavy;

    public Prostor(String nazev, String popis) {
        this.nazev = nazev;
        this.popis = popis;
        vychody = new HashSet<>();
        veci = new HashMap<>();
        postavy = new HashMap<>();
    }

    /**
     * Zjišťuje, zda se v prostoru nachází předmět daného jména.
     * @param nazev hledaný název
     * @return logický výsledek volání
     */
    public boolean jeVecVProstoru(String nazev)
    {
        return veci.containsKey(nazev);
    }

    /**
     * Vrátí odkaz na předmět zadaného jména, který se nachází v prostoru (nebo batohu).
     * @param nazev hledaný název
     * @return logický výsledek volání
     */
    public Vec getVec(String nazev)
    {
        return veci.get(nazev);
    }

    /**
     * Přidá zadaný předmět do prostoru (nebo batohu).
     * @param vec požadovaný vstup vec
     * @return logický výsledek volání
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
     * Odebere předmět zadaného jména z prostoru (nebo batohu) a vrátí na něj odkaz.
     * @param vec požadovaný vstup vec
     * @return logický výsledek volání
     */
    public Vec odeberVec(Vec vec)
    {
        return veci.remove(vec.getNazev());
    }

    /**
     * Přidá zadanou postavu (NPC) do aktuálního prostoru.
     * @param postava požadovaný vstup postava
     */
    public void pridejPostavu(NPCPouzeMluvici postava) {
        postavy.put(postava.getJmeno(), postava);
    }

    /**
     * Vrátí odkaz na postavu zadaného jména v aktuálním prostoru.
     * @param jmeno požadovaný vstup jmeno
     * @return logický výsledek volání
     */
    public NPCPouzeMluvici getPostava(String jmeno) {
        return postavy.get(jmeno);
    }

    /**
     * Metoda pro setVychod.
     * @param vedlejsi požadovaný vstup vedlejsi
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
     * Vrátí název této instance (předmětu, prostoru, příkazu či postavy).
     * @return logický výsledek volání
     */
    public String getNazev() {
        return nazev;       
    }

    /**
     * Vrátí dlouhý popis prostoru včetně seznamu východů, věcí a postav.
     * @return logický výsledek volání
     */
    public String dlouhyPopis() {
        return "Jsi v mistnosti/prostoru " + popis + ".\n"
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
     * Vrátí odkaz na sousední prostor zadaného jména, pokud takový existuje.
     * @param nazevSouseda požadovaný vstup nazevSouseda
     * @return logický výsledek volání
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        List<Prostor>hledaneProstory = 
            vychody.stream()
                   .filter(sousedni -> sousedni.getNazev().equals(nazevSouseda))
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
     * @return logický výsledek volání
     */
    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }
}
