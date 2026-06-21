package cz.vse.adventura.logika;

import cz.vse.adventura.logika.predmety.*;
import cz.vse.adventura.logika.prikazy.*;
import cz.vse.adventura.logika.npc.*;

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
    }

    public String vratUvitani() {
        return "Vítejte na VŠE!\n" +
                "Jsi v učebně informatiky, právě skončila přednáška 4IT101 a máš strašnou žízeň. Tvým úkolem je najít něco co uhasí tvoji žízeň a dostavit se včas na další přednášku ekonomie. Hodně štěstí!\n"
                +
                "Napište 'nápověda', pokud si nevíte rady, jak hrát dál.\n" +
                "\n" +
                herniPlan.getAktualniProstor().dlouhyPopis();
    }

    public String vratEpilog() {
        return textEpilogu;
    }

    public boolean konecHry() {
        return konecHry;
    }

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
                            "Kouknul jsi na hodinky a uvědomil sis, že jdeš pozdě. Navíc přednáška, na které jsi měl být byla povinná a rozhodovala o tvé akademické budoucnosti. S poraženým výrazem jsi následně opustil VŠE a zaměřil k nejbližšímu mekáči hledat práci.");
                    textKVypsani += "\n\n" + vratEpilog();
                } else {
                    textKVypsani += "\n(Zbývající tahy: " + zbyvajiciTahy + ")";
                }
            }
        } else {
            textKVypsani = "Nevím co tím myslíš? Tento příkaz neznám. ";
        }
        return textKVypsani;
    }

    public void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }

    public void setKonecHryS_Epilogem(StavHry stav, String epilog) {
        this.stavHry = stav;
        this.konecHry = true;
        this.textEpilogu = epilog;
    }

    public void setUspokojenaZizen(boolean stav) {
        this.uspokojenaZizen = stav;
    }

    public boolean isUspokojenaZizen() {
        return uspokojenaZizen;
    }

    public void setVypitoSavo(boolean stav) {
        this.vypitoSavo = stav;
    }

    public boolean isVypitoSavo() {
        return vypitoSavo;
    }

    public void setRozbityAutomat(boolean stav) {
        this.rozbityAutomat = stav;
    }

    public boolean isRozbityAutomat() {
        return rozbityAutomat;
    }

    public HerniPlan getHerniPlan() {
        return herniPlan;
    }
}
