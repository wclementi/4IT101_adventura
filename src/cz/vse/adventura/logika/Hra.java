package cz.vse.adventura.logika;
import cz.vse.adventura.logika.predmety.*;
import cz.vse.adventura.logika.prikazy.*;
import cz.vse.adventura.logika.npc.*;

public class Hra implements IHra {
    private SeznamPrikazu platnePrikazy;
    private HerniPlan herniPlan;
    private boolean konecHry = false;
    
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
    }

    public String vratUvitani() {
        return "Vítejte na VŠE!\n" +
               "Jsi v aule a máš strašnou žízeň. Tvým úkolem je najít automat 'lokni si' a koupit si nápoj.\n" +
               "Napište 'nápověda', pokud si nevíte rady, jak hrát dál.\n" +
               "\n" +
               herniPlan.getAktualniProstor().dlouhyPopis();
    }
    
    public String vratEpilog() {
        if (stavHry == StavHry.VYHRA) {
            return "Gratuluji! Vypil jsi nápoj, zažehnal jsi žízeň a stihl jsi přednášku. VYHRÁL JSI!";
        } else if (stavHry == StavHry.PROHRA) {
            return "Bohužel jsi nestihl vyřešit svůj problém s žízní a zkolaboval jsi. PROHRÁL JSI!";
        } else {
            return "Díky za hru! Snad už nemáš žízeň. Ahoj.";
        }
    }
    
    public boolean konecHry() {
        return konecHry;
    }

    public String zpracujPrikaz(String radek) {
        String [] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String []parametry = new String[slova.length-1];
        for(int i=0 ;i<parametry.length;i++){
           	parametry[i]= slova[i+1];  	
        }
        String textKVypsani=" .... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.provedPrikaz(parametry);
        }
        else {
            textKVypsani="Nevím co tím myslíš? Tento příkaz neznám. ";
        }
        return textKVypsani;
    }
    
    public void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }

    // ukol 12. tyden: Změnu stavu a konec hry (z příkazů)
    public void setStavHry(StavHry stav) {
        this.stavHry = stav;
        this.konecHry = true;
    }
    
    public HerniPlan getHerniPlan(){
        return herniPlan;
    }
}
