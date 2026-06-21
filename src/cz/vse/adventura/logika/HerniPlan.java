package cz.vse.adventura.logika;
import cz.vse.adventura.logika.predmety.*;
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

        // Vytvoření a přidání NPC postav do prostorů
        NPCPouzeMluvici ucitel = new NPCPouzeMluvici("ucitel", "(ucitel): Po sbaleni svých věcí zastavil studenty aby jim něco připomenul. \"Pamatujte si odevzdaní semestrální práce je vypsáno v odevzdávarně na 25.6. ale jestli chcete absolvovat obajobu musíte jí odevzdat před ní.\"\n(ty): \"To bych asi mohl udělat doma, teď na to ale ani nemůžu myslet kvůli té žízni\", pomyslel sis.");
        ucebnaProgramovani.pridejPostavu(ucitel);

        NPCPouzeMluvici typecek = new NPCPouzeMluvici("typecek", "(ty): \"Ahoj, nevěděl bys kde můžu sehnat něco k pití?\"\n(typecek): ... \"S tim ti asi nepomuzu\"\n(ty): \"Jakto??\"\n(typecek): \"Tady ani nestuduju. . .\"\n(ty): \"Ouu, tak to se neda nic\"");
        chodbaPatro1.pridejPostavu(typecek);

        NPCPouzeMluvici ucitelka = new NPCPouzeMluvici("ucitelka_ekonomie", "*vsimnul sis známé tváře, přednášející ekonomie, která zrovna čekala poblíž schodů telefonem v ruce*\n(ty): \"Zdravím, mohl bych se vás prosím zeptat na to v jaké místnoti dnes přednášíte ekonomii?\"\n(ucitelka): \"To byste ale měl vědět vy, mladý Pane...\"\n(ty): \"Ja vím ale mám dnes těžký den a nějak mi to vypadlo, řekla byste mi to prosím?\"\n(ucitelka): \"Přednáška bude v Likešově aule ... a né abych vás viděla přijít pozdě.\"\n(ty): \"Určite, příjdu včas a děkuju moc.\"");
        schodiste.pridejPostavu(ucitelka);

        NPCPouzeMluvici vratny = new NPCPouzeMluvici("vratny", "(ty): \"Omlouvám se, mám strašnou žízeň a potřebuju se něčeho napít. Poradil byste mi prosim?\"\n(vratny): \"Urcite, tady nedaleko je automat kde si muzete natocit vodu do vlastni lahve s aplikaci v telefonu, mel by byt na ceste do nove budovy nebo na chodbe v nove budove je automat, tam ale budete muset zaplatit mincemi.\"\n(ty): \"Dekuju moc, mejte se.\"");
        recepce.pridejPostavu(vratny);

        NPCPouzeMluvici student = new NPCPouzeMluvici("student", "(ty): \"Ahoj, na jakou prednasku tady cekas?\"\n(student): \"Cekam na prednasku databazi.\"\n(ty): \"Diky moc.\"");
        vencovskehoAula.pridejPostavu(student);

        NPCPouzeMluvici studentka = new NPCPouzeMluvici("studentka", "(ty): \"Ahoj, na jakou prednasku tady cekas?\"\n(student): \"Cekam na prednasku ekonomie.\"\n(ty): \"Super, diky moc.\"");
        likesovaAula.pridejPostavu(studentka);

        NPCDominik dominik = new NPCDominik("dominik");
        chodbaPatro2.pridejPostavu(dominik);

        NPCTypecka typecka = new NPCTypecka("typecka");
        chodbaPrizemi.pridejPostavu(typecka);

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
        if (!nazev.equals("nápoj") && !nazev.equals("plná_lahev") && !nazev.equals("plná_lahev_od_sava")) {
            throw new Exception("Tohle se nedá pít.");
        }
        batoh.odeberVec(nazev);
    }
    
    public void kupVec(String nazev) throws Exception {
        if (!aktualniProstor.jeVecVProstoru("automat") && !aktualniProstor.jeVecVProstoru("LOKNI_SI_automat") && !aktualniProstor.jeVecVProstoru("nápojový_automat")) {
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
