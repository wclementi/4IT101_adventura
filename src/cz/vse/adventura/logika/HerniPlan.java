package cz.vse.adventura.logika;
import cz.vse.adventura.logika.predmety.*;
import cz.vse.adventura.logika.prikazy.*;
import cz.vse.adventura.logika.npc.*;

public class HerniPlan {
    
    private Prostor aktualniProstor;
    private final Batoh batoh;
    
    public HerniPlan() {
        zalozProstoryHry();
        // ukol 12. tyden: Singleton pro batoh
        batoh = Batoh.getInstance();
    }
    
    private void zalozProstoryHry() {
        Prostor aula = new Prostor("aula","velká aula na VŠE");
        Prostor chodba = new Prostor("chodba", "dlouhá chodba se skříňkami");
        Prostor bufet = new Prostor("bufet","školní bufet, kde je ale docela draho");

        aula.setVychod(chodba);
        chodba.setVychod(aula);
        chodba.setVychod(bufet);
        bufet.setVychod(chodba);
        
        aula.pridejVec(new Vec("mince", true, 1));
        bufet.pridejVec(new Vec("automat", false, 100)); // automat 'lokni si'
                
        aktualniProstor = aula;
    }

    public void seberVec(String nazevVeci) throws Exception
    {
        Vec vec = aktualniProstor.getVec(nazevVeci);

        if(vec != null)
        {
            if (!vec.isPrenositelna()) {
                throw new Exception("Tuto věc nelze přenášet.");
            }
            batoh.pridejVec(vec);
            aktualniProstor.odeberVec(vec);
        }else{
            throw new Exception("Nic takoveho tu neni");
        }
    }

    public void polozVec(String nazevVeci) throws Exception
    {
        Vec vec = batoh.odeberVec(nazevVeci);
        aktualniProstor.pridejVec(vec);
    }
    
    public void vypij(String nazev) throws Exception {
        if (!batoh.jeVecVBatohu(nazev)) {
            throw new Exception("Takovou věc nemáš v batohu k pití.");
        }
        if (!nazev.equals("nápoj")) {
            throw new Exception("Tohle se nedá pít.");
        }
        batoh.odeberVec(nazev);
    }
    
    public void kupVec(String nazev) throws Exception {
        if (!aktualniProstor.jeVecVProstoru("automat")) {
            throw new Exception("Tady není automat, kde by se dalo nakupovat.");
        }
        if (!batoh.jeVecVBatohu("mince")) {
            throw new Exception("Nemáš žádné mince na nákup.");
        }
        if (!nazev.equals("nápoj")) {
            throw new Exception("Automat prodává pouze 'nápoj'.");
        }
        
        batoh.odeberVec("mince");
        aktualniProstor.pridejVec(new Vec("nápoj", true, 2));
    }
    
    public String prozkoumejVec(String nazev) throws Exception {
        if (aktualniProstor.jeVecVProstoru(nazev)) {
            if (nazev.equals("automat")) {
                return "Je to automat 'lokni si'. Vypadá to, že prodává 'nápoj' za 'mince'.";
            }
            return "Vidíš věc: " + nazev;
        } else if (batoh.jeVecVBatohu(nazev)) {
            return "Máš v batohu věc: " + nazev;
        } else {
            throw new Exception("Taková věc tu není k prozkoumání ani v prostoru, ani v batohu.");
        }
    }

    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
    }

    public String getPopisObsahuBatohu() {
        return batoh.getPopisObsahu();
    }
}
