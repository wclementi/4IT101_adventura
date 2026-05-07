package cz.vse.adventura.logika;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HraTest {

    private Hra hra1;

    @BeforeEach
    public void setUp() {
        hra1 = new Hra();
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testPrubehHry() {
        assertEquals("domeček", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi les");
        assertFalse(hra1.konecHry());
        assertEquals("les", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi hluboký_les");
        assertFalse(hra1.konecHry());
        assertEquals("hluboký_les", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("konec");
        assertTrue(hra1.konecHry());
    }
}