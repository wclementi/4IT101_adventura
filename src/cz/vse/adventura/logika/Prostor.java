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
     * Metoda pro jeVecVProstoru.
     * @param nazev parametr metody
     * @return hodnota z metody
     */
    public boolean jeVecVProstoru(String nazev)
    {
        return veci.containsKey(nazev);
    }

    /**
     * Metoda pro getVec.
     * @param nazev parametr metody
     * @return hodnota z metody
     */
    public Vec getVec(String nazev)
    {
        return veci.get(nazev);
    }

    /**
     * Metoda pro pridejVec.
     * @param vec parametr metody
     * @return hodnota z metody
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
     * Metoda pro odeberVec.
     * @param vec parametr metody
     * @return hodnota z metody
     */
    public Vec odeberVec(Vec vec)
    {
        return veci.remove(vec.getNazev());
    }

    /**
     * Metoda pro pridejPostavu.
     * @param postava parametr metody
     */
    public void pridejPostavu(NPCPouzeMluvici postava) {
        postavy.put(postava.getJmeno(), postava);
    }

    /**
     * Metoda pro getPostava.
     * @param jmeno parametr metody
     * @return hodnota z metody
     */
    public NPCPouzeMluvici getPostava(String jmeno) {
        return postavy.get(jmeno);
    }

    /**
     * Metoda pro setVychod.
     * @param vedlejsi parametr metody
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
     * Metoda pro getNazev.
     * @return hodnota z metody
     */
    public String getNazev() {
        return nazev;       
    }

    /**
     * Metoda pro dlouhyPopis.
     * @return hodnota z metody
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
     * Metoda pro vratSousedniProstor.
     * @param nazevSouseda parametr metody
     * @return hodnota z metody
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
     * Metoda pro getVychody.
     * @return hodnota z metody
     */
    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }
}
