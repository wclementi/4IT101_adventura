package cz.vse.adventura.logika;

import cz.vse.adventura.logika.predmety.Batoh;
import cz.vse.adventura.logika.predmety.Vec;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BatohTest {

    private Batoh batoh;

    @BeforeEach
    public void setUp() {
        batoh = Batoh.getInstance();
        batoh.vymazBatoh();
    }

    @Test
    public void testPridejOdeberVec() throws Exception {
        Vec jablko = new Vec("jablko", true, 1);
        batoh.pridejVec(jablko);
        
        assertTrue(batoh.jeVecVBatohu("jablko"));
        assertEquals(jablko, batoh.getVec("jablko"));
        
        Vec odebrana = batoh.odeberVec("jablko");
        assertEquals(jablko, odebrana);
        assertFalse(batoh.jeVecVBatohu("jablko"));
    }

    @Test
    public void testKapacitaBatohu() throws Exception {
        // Batoh by měl mít nastavenou nějakou maximální kapacitu (obvykle 10)
        Vec tezkaVec1 = new Vec("cihla", true, 6);
        Vec tezkaVec2 = new Vec("kamen", true, 5);
        
        batoh.pridejVec(tezkaVec1);
        
        // Přidání druhé věci by mělo selhat na překročení kapacity
        Exception exception = assertThrows(Exception.class, () -> {
            batoh.pridejVec(tezkaVec2);
        });
        
        assertTrue(exception.getMessage().contains("Nosnost") || exception.getMessage().contains("kapacit"), 
            "Měla by vyskočit výjimka o překročení kapacity nebo nosnosti batohu.");
    }
}
