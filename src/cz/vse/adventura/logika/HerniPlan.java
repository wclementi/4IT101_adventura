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
        // vytvoření prostorů
        Prostor ucebnaProgramovani = new Prostor("ucebna_programovani", "učebna, kde probíhá přednáška 4IT101");
        Prostor chodbaPatro1 = new Prostor("chodba_1_patro", "chodba v prvním patře");
        Prostor chodbaPatro2 = new Prostor("chodba_2_patro", "chodba ve druhém patře");
        Prostor uklidovaMistnost = new Prostor("uklidova_mistnost", "malá místnost pro úklidové potřeby");
        Prostor schodiste = new Prostor("schodiste", "schodiště propojující patra");
        Prostor chodbaPrizemi = new Prostor("chodba_prizemi", "chodba v přízemí");
        Prostor recepce = new Prostor("recepce", "recepce u vstupu do budovy");
        Prostor vychodVSE = new Prostor("vychod_z_VSE", "východ z budovy VŠE");
        Prostor chodbaNovaBudova = new Prostor("chodba_do_nove_budovy", "prosklená chodba do nové budovy");
        Prostor chodbaVnoveBudove = new Prostor("chodba_v_nove_budove", "hlavní chodba v nové budově");
        Prostor vencovskehoAula = new Prostor("vencovskeho_aula", "Vencovského aula");
        Prostor likesovaAula = new Prostor("likesova_aula", "Likesova aula");

        // propojení prostorů
        ucebnaProgramovani.setVychod(chodbaPatro1);
        chodbaPatro1.setVychod(ucebnaProgramovani);
        chodbaPatro1.setVychod(schodiste);

        schodiste.setVychod(chodbaPatro1);
        schodiste.setVychod(chodbaPatro2);
        schodiste.setVychod(chodbaPrizemi);

        chodbaPatro2.setVychod(schodiste);
        chodbaPatro2.setVychod(uklidovaMistnost);
        uklidovaMistnost.setVychod(chodbaPatro2);

        chodbaPrizemi.setVychod(schodiste);
        chodbaPrizemi.setVychod(recepce);
        chodbaPrizemi.setVychod(chodbaNovaBudova);
        
        recepce.setVychod(chodbaPrizemi);
        recepce.setVychod(vychodVSE);
        vychodVSE.setVychod(recepce);

        chodbaNovaBudova.setVychod(chodbaPrizemi);
        chodbaNovaBudova.setVychod(chodbaVnoveBudove);

        chodbaVnoveBudove.setVychod(chodbaNovaBudova);
        chodbaVnoveBudove.setVychod(vencovskehoAula);
        chodbaVnoveBudove.setVychod(likesovaAula);

        vencovskehoAula.setVychod(chodbaVnoveBudove);
        likesovaAula.setVychod(chodbaVnoveBudove);
        
        // Přidání věcí do prostorů
        chodbaPatro1.pridejVec(new Vec("učebnice_ekonomie", true, 3));
        uklidovaMistnost.pridejVec(new Vec("páčidlo", true, 5));
        uklidovaMistnost.pridejVec(new Vec("prázdná_lahev_od_sava", true, 1));
        chodbaPrizemi.pridejVec(new Vec("mince", true, 1));
        chodbaNovaBudova.pridejVec(new Vec("LOKNI_SI_automat", false, 100));
        chodbaVnoveBudove.pridejVec(new Vec("nápojový_automat", false, 100));
        vencovskehoAula.pridejVec(new Vec("volná_sedacka", false, 50));
        vencovskehoAula.pridejVec(new Vec("mince", true, 1));
        likesovaAula.pridejVec(new Vec("volná_sedacka", false, 50));
        likesovaAula.pridejVec(new Vec("mince", true, 1));

        // nastavení počátečního prostoru
        aktualniProstor = ucebnaProgramovani;
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