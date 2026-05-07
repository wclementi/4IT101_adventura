package cz.vse.adventura.logika;

interface IPrikaz {
    public String provedPrikaz(String... parametry);
    public String getNazev();
}