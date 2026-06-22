package cz.vse.adventura.logika;

import cz.vse.adventura.logika.prikazy.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Hlavní třída logiky hry. Inicializuje hru, předává příkazy parseru a spravuje stav hry.
 *
 * @author Kajetan Franciszek Wielgosz
 */
public class Hra implements IHra {
    private SeznamPrikazu platnePrikazy;
    private HerniPlan herniPlan;
    private boolean konecHry = false;
    private int zbyvajiciTahy = 35;
    private boolean uspokojenaZizen = false;
    private boolean vypitoSavo = false;
    private boolean rozbityAutomat = false;
    private String textEpilogu = "Díky za hru! Snad už nemáš žízeň. Ahoj.";

    // ukol 12. tyden: Enum pro stav hry
    private StavHry stavHry;

    private List<String> historie = new ArrayList<>();

    public Hra() {
        herniPlan = new HerniPlan();
        platnePrikazy = new SeznamPrikazu();
        stavHry = StavHry.PROBIHA;

        // ukol 12. tyden: Využití Factory
        platnePrikazy.vlozPrikaz(PrikazFactory.vytvorPrikaz("nápověda", herniPlan, platnePrikazy, this));
        platnePrikazy.vlozPrikaz(PrikazFactory.vytvorPrikaz("jdi", herniPlan, null, this));
        platnePrikazy.vlozPrikaz(PrikazFactory.vytvorPrikaz("konec", herniPlan, null, this));
        platnePrikazy.vlozPrikaz(PrikazFactory.vytvorPrikaz("seber", herniPlan, null, this));
        platnePrikazy.vlozPrikaz(PrikazFactory.vytvorPrikaz("polož", herniPlan, null, this));
        platnePrikazy.vlozPrikaz(PrikazFactory.vytvorPrikaz("vypiš", herniPlan, null, this));
        platnePrikazy.vlozPrikaz(PrikazFactory.vytvorPrikaz("pij", herniPlan, null, this));
        platnePrikazy.vlozPrikaz(PrikazFactory.vytvorPrikaz("kup", herniPlan, null, this));
        platnePrikazy.vlozPrikaz(PrikazFactory.vytvorPrikaz("prozkoumej", herniPlan, null, this));
        platnePrikazy.vlozPrikaz(PrikazFactory.vytvorPrikaz("promluv", herniPlan, null, this));
        platnePrikazy.vlozPrikaz(PrikazFactory.vytvorPrikaz("naplň", herniPlan, null, this));
        platnePrikazy.vlozPrikaz(PrikazFactory.vytvorPrikaz("sedni", herniPlan, null, this));
        platnePrikazy.vlozPrikaz(PrikazFactory.vytvorPrikaz("rozbij", herniPlan, null, this));
        platnePrikazy.vlozPrikaz(PrikazFactory.vytvorPrikaz("log", herniPlan, null, this));
    }

    /**
     * Vrátí uvítací text pro hráče při zahájení hry.
     * @return uvítací text
     */
    public String vratUvitani() {
        return "Vítejte na VŠE!\n" +
                "Jsi v učebně informatiky, právě skončila přednáška 4IT101 a máš strašnou žízeň. Tvým úkolem je najít něco, co uhasí tvoji žízeň, a dostavit se včas na další přednášku ekonomie. Hodně štěstí!\n"
                +
                "Napište 'nápověda', pokud si nevíte rady, jak hrát dál.\n" +
                "\n" +
                herniPlan.getAktualniProstor().dlouhyPopis();
    }

    /**
     * Vrátí závěrečný text pro hráče po ukončení hry.
     * @return závěrečný epilog
     */
    public String vratEpilog() {
        return textEpilogu;
    }

    /**
     * Vrací informaci, zda hra již skončila (výhrou, prohrou nebo příkazem konec).
     * @return true, pokud hra skončila, jinak false
     */
    public boolean konecHry() {
        return konecHry;
    }

    /**
     * Zpracuje textový řetězec zadaný hráčem, rozdělí jej na slova a deleguje zpracování příslušnému příkazu. Vrací odpověď hry.
     * @param radek textový příkaz od uživatele
     * @return odpověď hry (výsledek vykonání příkazu)
     */
    public String zpracujPrikaz(String radek) {
        String[] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String[] parametry = new String[slova.length - 1];
        for (int i = 0; i < parametry.length; i++) {
            parametry[i] = slova[i + 1];
        }
        String textKVypsani = " .... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.provedPrikaz(parametry);

            if (!konecHry && !slovoPrikazu.equals("konec") && !slovoPrikazu.equals("nápověda") && !slovoPrikazu.equals("vypiš") && !slovoPrikazu.equals("sedni")) {
                zbyvajiciTahy--;
                if (zbyvajiciTahy <= 0) {
                    setKonecHryS_Epilogem(StavHry.BAD_ENDING,
                            "Podíval ses na hodinky a uvědomil sis, že jdeš pozdě. Navíc přednáška, na které jsi měl být, byla povinná a rozhodovala o tvé akademické budoucnosti. S poraženým výrazem jsi následně opustil VŠE a zamířil k nejbližšímu mekáči hledat práci.");
                    textKVypsani += "\n\n" + vratEpilog();
                } else {
                    textKVypsani += "\n(Zbývající tahy: " + zbyvajiciTahy + ")";
                }
            }
        } else {
            textKVypsani = "Nevím, co tím myslíš. Tento příkaz neznám.";
        }
        
        historie.add("> " + radek);
        historie.add(textKVypsani);
        
        return textKVypsani;
    }

    /**
     * Vrátí kompletní historii zadaných příkazů a odpovědí hry.
     * @return seznam historických záznamů
     */
    public List<String> getHistorie() {
        return historie;
    }

    /**
     * Nastaví indikátor konce hry na zadanou hodnotu.
     * @param konecHry true pro ukončení hry, false pro pokračování
     */
    public void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }

    /**
     * Ukončí hru se specifickým stavem a nastaví odpovídající epilog.
     * @param stav konečný stav hry (výhra, prohra atd.)
     * @param epilog závěrečný text po ukončení
     */
    public void setKonecHryS_Epilogem(StavHry stav, String epilog) {
        this.stavHry = stav;
        this.konecHry = true;
        this.textEpilogu = epilog;
    }

    /**
     * Vrátí aktuální stav hry (např. PROBIHA, GOOD_ENDING, atd.).
     * @return aktuální stav hry
     */
    public StavHry getStavHry() {
        return stavHry;
    }

    /**
     * Nastaví, zda si hráč již uspokojil žízeň.
     * @param stav true, pokud je žízeň uspokojena, jinak false
     */
    public void setUspokojenaZizen(boolean stav) {
        this.uspokojenaZizen = stav;
    }

    /**
     * Vrací informaci, zda již byla žízeň hráče uspokojena.
     * @return true, pokud byla žízeň uspokojena, jinak false
     */
    public boolean isUspokojenaZizen() {
        return uspokojenaZizen;
    }

    /**
     * Nastaví příznak, zda hráč vypil Savo.
     * @param stav true, pokud hráč vypil Savo, jinak false
     */
    public void setVypitoSavo(boolean stav) {
        this.vypitoSavo = stav;
    }

    /**
     * Vrací informaci, zda hráč vypil Savo.
     * @return true, pokud hráč vypil Savo, jinak false
     */
    public boolean isVypitoSavo() {
        return vypitoSavo;
    }

    /**
     * Nastaví příznak, zda byl automat rozbit páčidlem.
     * @param stav true, pokud je automat rozbitý, jinak false
     */
    public void setRozbityAutomat(boolean stav) {
        this.rozbityAutomat = stav;
    }

    /**
     * Vrací informaci, zda byl automat rozbit páčidlem.
     * @return true, pokud je automat rozbitý, jinak false
     */
    public boolean isRozbityAutomat() {
        return rozbityAutomat;
    }

    /**
     * Vrátí odkaz na aktuální herní plán (mapu) obsahující místnosti a jejich propojení.
     * @return herní plán
     */
    public HerniPlan getHerniPlan() {
        return herniPlan;
    }
}
