package cz.vse.adventura.logika;
import cz.vse.adventura.logika.predmety.*;
import cz.vse.adventura.logika.prikazy.*;
import cz.vse.adventura.logika.npc.*;

import java.util.*;
import java.util.stream.Collectors;

public class Prostor {

    private String nazev;
    private String popis;
    private Set<Prostor> vychody;
    private Map<String, Vec> veci;

    public Prostor(String nazev, String popis) {
        this.nazev = nazev;
        this.popis = popis;
        vychody = new HashSet<>();
        veci = new HashMap<>();
    }

    public boolean jeVecVProstoru(String nazev)
    {
        return veci.containsKey(nazev);
    }

    public Vec getVec(String nazev)
    {
        return veci.get(nazev);
    }

    public Vec pridejVec(Vec vec)
    {
        if(jeVecVProstoru(vec.getNazev()))
        {
            return getVec(vec.getNazev());
        }

        return veci.put(vec.getNazev(), vec);
    }

    public Vec odeberVec(Vec vec)
    {
        return veci.remove(vec.getNazev());
    }

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

    public String getNazev() {
        return nazev;       
    }

    public String dlouhyPopis() {
        return "Jsi v mistnosti/prostoru " + popis + ".\n"
                + popisVychodu() + "\n"
                + popisVeci();
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

    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }
}
