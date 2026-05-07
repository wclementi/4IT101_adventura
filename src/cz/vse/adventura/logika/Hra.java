package cz.vse.adventura.logika;

public class Hra implements IHra {
    private SeznamPrikazu platnePrikazy;
    private HerniPlan herniPlan;
    private boolean konecHry = false;

    public Hra() {
        herniPlan = new HerniPlan();
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazJdi(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PrikazSeber(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazPoloz(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazVypis(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazPij(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazKup(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazProzkoumej(herniPlan));
    }

    public String vratUvitani() {
        return "Vítejte na VŠE!\n" +
               "Jsi v aule a máš strašnou žízeň. Tvým úkolem je najít automat 'lokni si' a koupit si nápoj.\n" +
               "Napište 'nápověda', pokud si nevíte rady, jak hrát dál.\n" +
               "\n" +
               herniPlan.getAktualniProstor().dlouhyPopis();
    }
    
    public String vratEpilog() {
        return "Díky za hru! Snad už nemáš žízeň. Ahoj.";
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
    
    void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }
    
    public HerniPlan getHerniPlan(){
        return herniPlan;
    }
}