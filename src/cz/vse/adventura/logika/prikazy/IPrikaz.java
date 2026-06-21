package cz.vse.adventura.logika.prikazy;

public interface IPrikaz {
    public String provedPrikaz(String... parametry);
    public String getNazev();
}
