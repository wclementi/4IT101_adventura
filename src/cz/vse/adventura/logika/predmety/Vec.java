package cz.vse.adventura.logika.predmety;
import cz.vse.adventura.logika.*;
import cz.vse.adventura.logika.predmety.*;
import cz.vse.adventura.logika.prikazy.*;
import cz.vse.adventura.logika.npc.*;

public class Vec
{
    private String nazev;
    private boolean prenositelna;
    private int hmotnost;

    public Vec(String nazev, boolean prenositelna, int hmotnost) {
        this.nazev = nazev;
        this.prenositelna = prenositelna;
        this.hmotnost = hmotnost;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public boolean isPrenositelna() {
        return prenositelna;
    }

    public void setPrenositelna(boolean prenositelna) {
        this.prenositelna = prenositelna;
    }

    public int getHmotnost() {
        return hmotnost;
    }

    public void setHmotnost(int hmotnost) {
        this.hmotnost = hmotnost;
    }
}
