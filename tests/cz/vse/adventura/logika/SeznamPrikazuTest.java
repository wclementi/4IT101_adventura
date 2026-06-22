package cz.vse.adventura.logika;

import cz.vse.adventura.logika.prikazy.PrikazJdi;
import cz.vse.adventura.logika.prikazy.PrikazKonec;
import cz.vse.adventura.logika.prikazy.PrikazNapoveda;
import cz.vse.adventura.logika.prikazy.SeznamPrikazu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeznamPrikazuTest
{
    private Hra hra;
    private PrikazKonec prKonec;
    private PrikazJdi prJdi;
    private PrikazNapoveda prNapoveda;
    
    @BeforeEach
    public void setUp() {
        hra = new Hra();
        prKonec = new PrikazKonec(hra);
        prJdi = new PrikazJdi(hra.getHerniPlan(), hra);
    }

    @Test
    public void testVlozeniVybrani() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        assertEquals(prKonec, seznPrikazu.vratPrikaz("konec"));
        assertEquals(prJdi, seznPrikazu.vratPrikaz("jdi"));
        assertEquals(null, seznPrikazu.vratPrikaz("nápověda"));
    }
    
    @Test
    public void testJePlatnyPrikaz() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        PrikazNapoveda prNap = new PrikazNapoveda(seznPrikazu);
        seznPrikazu.vlozPrikaz(prNap);
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("konec"));
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("jdi"));
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("nápověda"));
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("nApoVedA"));
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("Konec"));
        assertEquals(false, seznPrikazu.jePlatnyPrikaz("seber"));
        assertEquals(false, seznPrikazu.jePlatnyPrikaz("promluv s"));
        assertEquals(false, seznPrikazu.jePlatnyPrikaz("naplň"));
    }
    
    @Test
    public void testNazvyPrikazu() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        String nazvy = seznPrikazu.vratNazvyPrikazu();
        assertEquals(true, nazvy.contains("konec"));
        assertEquals(true, nazvy.contains("jdi"));
        assertEquals(false, nazvy.contains("nápověda"));
        assertEquals(false, nazvy.contains("Konec"));
    }
}