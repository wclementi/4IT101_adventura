package cz.vse.adventura.logika;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class HraTest {

    private Hra hra;

    @BeforeEach
    public void setUp() {
        hra = new Hra();
        // Clear inventory to ensure tests are isolated
        cz.vse.adventura.logika.predmety.Batoh.getInstance().vymazBatoh();
    }

    @Test
    public void testGoodEnding() {
        hra.zpracujPrikaz("jdi chodba_1_patro");
        hra.zpracujPrikaz("jdi schodiste");
        hra.zpracujPrikaz("jdi chodba_prizemi");
        hra.zpracujPrikaz("seber mince");
        hra.zpracujPrikaz("jdi chodba_do_nove_budovy");
        hra.zpracujPrikaz("jdi chodba_v_nove_budove");
        hra.zpracujPrikaz("kup nápoj");
        hra.zpracujPrikaz("seber nápoj");
        hra.zpracujPrikaz("pij nápoj");
        hra.zpracujPrikaz("jdi likesova_aula");
        hra.zpracujPrikaz("sedni");

        assertTrue(hra.konecHry());
        assertTrue(
                hra.vratEpilog().contains("Uspokojil sis svojí žízeň a stihnul přednášku, další přežitý den na VŠE"));
    }

    @Test
    public void testGoodEndingMobil() {
        hra.zpracujPrikaz("jdi chodba_1_patro");
        hra.zpracujPrikaz("seber učebnice_ekonomie");
        hra.zpracujPrikaz("jdi schodiste");
        hra.zpracujPrikaz("jdi chodba_2_patro");
        hra.zpracujPrikaz("promluv dominik"); // předá lahev za učebnici
        hra.zpracujPrikaz("jdi schodiste");
        hra.zpracujPrikaz("jdi chodba_prizemi");
        hra.zpracujPrikaz("promluv typecka"); // půjčí telefon za to, že máme lahev
        hra.zpracujPrikaz("jdi chodba_do_nove_budovy");
        hra.zpracujPrikaz("naplň prázdná_lahev");
        hra.zpracujPrikaz("pij plná_lahev");
        hra.zpracujPrikaz("jdi chodba_prizemi");
        hra.zpracujPrikaz("promluv typecka"); // vrácení telefonu
        hra.zpracujPrikaz("jdi chodba_do_nove_budovy");
        hra.zpracujPrikaz("jdi chodba_v_nove_budove");
        hra.zpracujPrikaz("jdi likesova_aula");
        hra.zpracujPrikaz("sedni");

        assertTrue(hra.konecHry());
        assertTrue(
                hra.vratEpilog().contains("Uspokojil sis svojí žízeň a stihnul přednášku, další přežitý den na VŠE"));
    }

    @Test
    public void testChaoticEndingRozbiti() {
        hra.zpracujPrikaz("jdi chodba_1_patro");
        hra.zpracujPrikaz("jdi schodiste");
        hra.zpracujPrikaz("jdi chodba_2_patro");
        hra.zpracujPrikaz("jdi uklidova_mistnost");
        hra.zpracujPrikaz("seber páčidlo");
        hra.zpracujPrikaz("jdi chodba_2_patro");
        hra.zpracujPrikaz("jdi schodiste");
        hra.zpracujPrikaz("jdi chodba_prizemi");
        hra.zpracujPrikaz("jdi chodba_do_nove_budovy");
        hra.zpracujPrikaz("rozbij LOKNI_SI_automat");
        hra.zpracujPrikaz("polož páčidlo");
        hra.zpracujPrikaz("seber nápoj");
        hra.zpracujPrikaz("pij nápoj");
        hra.zpracujPrikaz("jdi chodba_v_nove_budove");
        hra.zpracujPrikaz("jdi likesova_aula");
        hra.zpracujPrikaz("sedni");

        assertTrue(hra.konecHry());
        assertTrue(hra.vratEpilog().contains("ale za jakou cenu?"));
    }

    @Test
    public void testChaoticEndingOdchod() {
        hra.zpracujPrikaz("jdi chodba_1_patro");
        hra.zpracujPrikaz("jdi schodiste");
        hra.zpracujPrikaz("jdi chodba_prizemi");
        hra.zpracujPrikaz("jdi recepce");
        hra.zpracujPrikaz("jdi vychod_z_VSE");

        assertTrue(hra.konecHry());
        assertTrue(hra.vratEpilog().contains("Stejně nejsem studyjní typ"));
    }

    @Test
    public void testBadEndingLimitTahu() {
        for (int i = 0; i < 35; i++) {
            hra.zpracujPrikaz("jdi blbost");
        }

        assertTrue(hra.konecHry());
        assertTrue(hra.vratEpilog().contains("Kouknul jsi na hodinky a uvědomil sis, že jdeš pozdě."));
    }

    @Test
    public void testBadEndingZizen() {
        hra.zpracujPrikaz("jdi chodba_1_patro");
        hra.zpracujPrikaz("jdi schodiste");
        hra.zpracujPrikaz("jdi chodba_prizemi");
        hra.zpracujPrikaz("jdi chodba_do_nove_budovy");
        hra.zpracujPrikaz("jdi chodba_v_nove_budove");
        hra.zpracujPrikaz("jdi likesova_aula");
        hra.zpracujPrikaz("sedni");

        assertTrue(hra.konecHry());
        assertTrue(hra.vratEpilog().contains("zkolaboval kvůli vážné dehydrataci"));
    }

    @Test
    public void testBadEndingSpatnaAula() {
        hra.zpracujPrikaz("jdi chodba_1_patro");
        hra.zpracujPrikaz("jdi schodiste");
        hra.zpracujPrikaz("jdi chodba_prizemi");
        hra.zpracujPrikaz("jdi chodba_do_nove_budovy");
        hra.zpracujPrikaz("jdi chodba_v_nove_budove");
        hra.zpracujPrikaz("jdi vencovskeho_aula");
        hra.zpracujPrikaz("sedni");

        assertTrue(hra.konecHry());
        assertTrue(hra.vratEpilog().contains("jsi na špatné přednášce"));
    }

    @Test
    public void testNeprenositelnaVec() {
        hra.zpracujPrikaz("jdi chodba_1_patro");
        hra.zpracujPrikaz("jdi schodiste");
        hra.zpracujPrikaz("jdi chodba_prizemi");
        hra.zpracujPrikaz("jdi chodba_do_nove_budovy");

        String odpoved = hra.zpracujPrikaz("seber LOKNI_SI_automat");

        assertTrue(odpoved.contains("Tuto věc nelze přenášet"),
                "Měla by se zobrazit zpráva o nepřenositelném předmětu");
        assertFalse(cz.vse.adventura.logika.predmety.Batoh.getInstance().jeVecVBatohu("LOKNI_SI_automat"),
                "Automat by neměl být v batohu");
    }

    @Test
    public void testSeberVecABatoh() {
        hra.zpracujPrikaz("jdi chodba_1_patro");
        String odpoved = hra.zpracujPrikaz("seber učebnice_ekonomie");

        assertTrue(odpoved.contains("je nyní v batohu"), "Zpráva by měla potvrdit sebrání");
        assertTrue(cz.vse.adventura.logika.predmety.Batoh.getInstance().jeVecVBatohu("učebnice_ekonomie"),
                "Věc by měla být v batohu");
    }
}