package cz.vse.adventura.logika;

import cz.vse.adventura.logika.predmety.*;
import cz.vse.adventura.logika.npc.*;

/**
 * Třída představující herní mapu.
 * Definuje jednotlivé prostory a jejich propojení.
 *
 * @author Kajetan Franciszek Wielgosz
 */
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
        uklidovaMistnost.pridejVec(new Vec("prázdná_lahev_od_Sava", true, 1));
        chodbaPrizemi.pridejVec(new Vec("mince", true, 1));
        chodbaNovaBudova.pridejVec(new Vec("LOKNI_SI_automat", false, 100));
        chodbaVnoveBudove.pridejVec(new Vec("nápojový_automat", false, 100));
        vencovskehoAula.pridejVec(new Vec("volná_sedačka", false, 50));
        vencovskehoAula.pridejVec(new Vec("mince", true, 1));
        likesovaAula.pridejVec(new Vec("volná_sedačka", false, 50));
        likesovaAula.pridejVec(new Vec("mince", true, 1));

        // Vytvoření a přidání NPC postav do prostorů
        NPCPouzeMluvici ucitel = new NPCPouzeMluvici("učitel",
                "(učitel): Po sbalení svých věcí zastavil studenty, aby jim něco připomněl. \"Pamatujte si, odevzdání semestrální práce je vypsáno v odevzdávárně na 25. 6., ale jestli chcete absolvovat obhajobu, musíte ji odevzdat před ní.\"\n(ty): \"To bych asi mohl udělat doma, teď na to ale ani nemůžu myslet kvůli té žízni\", pomyslel sis.");
        ucebnaProgramovani.pridejPostavu(ucitel);

        NPCPouzeMluvici typecek = new NPCPouzeMluvici("týpeček",
                "(ty): \"Ahoj, nevěděl bys, kde můžu sehnat něco k pití?\"\n(týpeček): ... \"S tím ti asi nepomůžu.\"\n(ty): \"Jak to??\"\n(týpeček): \"Tady ani nestuduji...\"\n(ty): \"Ouu, tak to se nedá nic.\"");
        chodbaPatro1.pridejPostavu(typecek);

        NPCPouzeMluvici ucitelka = new NPCPouzeMluvici("učitelka_ekonomie",
                "*Všiml sis známé tváře, přednášející ekonomii, která zrovna čekala poblíž schodů s telefonem v ruce*\n(ty): \"Zdravím, mohl bych se vás prosím zeptat na to, v jaké místnosti dnes přednášíte ekonomii?\"\n(učitelka): \"To byste ale měl vědět vy, mladý pane...\"\n(ty): \"Já vím, ale mám dnes těžký den a nějak mi to vypadlo, řekla byste mi to prosím?\"\n(učitelka): \"Přednáška bude v Likešově aule... a ne abych vás viděla přijít pozdě.\"\n(ty): \"Určitě, přijdu včas a děkuji moc.\"");
        schodiste.pridejPostavu(ucitelka);

        NPCPouzeMluvici vratny = new NPCPouzeMluvici("vrátný",
                "(ty): \"Omlouvám se, mám strašnou žízeň a potřebuji se něčeho napít. Poradil byste mi prosím?\"\n(vrátný): \"Určitě, tady nedaleko je automat, kde si můžete natočit vodu do vlastní lahve s aplikací v telefonu. Měl by být na cestě do nové budovy. Nebo na chodbě v nové budově je automat, tam ale budete muset zaplatit mincemi.\"\n(ty): \"Děkuji moc, mějte se.\"");
        recepce.pridejPostavu(vratny);

        NPCPouzeMluvici student = new NPCPouzeMluvici("student",
                "(ty): \"Ahoj, na jakou přednášku tady čekáš?\"\n(student): \"Čekám na přednášku databází.\"\n(ty): \"Díky moc.\"");
        vencovskehoAula.pridejPostavu(student);

        NPCPouzeMluvici studentka = new NPCPouzeMluvici("studentka",
                "(ty): \"Ahoj, na jakou přednášku tady čekáš?\"\n(studentka): \"Čekám na přednášku ekonomie.\"\n(ty): \"Super, díky moc.\"");
        likesovaAula.pridejPostavu(studentka);

        NPCDominik dominik = new NPCDominik("dominik");
        chodbaPatro2.pridejPostavu(dominik);

        NPCTypecka typecka = new NPCTypecka("týpečka");
        chodbaPrizemi.pridejPostavu(typecka);

        // nastavení počátečního prostoru
        aktualniProstor = ucebnaProgramovani;
    }

    /**
     * Metoda pro seberVec.
     * 
     * @param nazevVeci požadovaný vstup nazevVeci
     */
    public void seberVec(String nazevVeci) throws Exception {
        Vec vec = aktualniProstor.getVec(nazevVeci);

        if (vec != null) {
            if (!vec.isPrenositelna()) {
                throw new Exception("Tuto věc nelze přenášet.");
            }
            batoh.pridejVec(vec);
            aktualniProstor.odeberVec(vec);
        } else {
            throw new Exception("Nic takového tu není.");
        }
    }

    /**
     * Metoda pro polozVec.
     * 
     * @param nazevVeci požadovaný vstup nazevVeci
     */
    public void polozVec(String nazevVeci) throws Exception {
        Vec vec = batoh.odeberVec(nazevVeci);
        aktualniProstor.pridejVec(vec);
    }

    /**
     * Metoda pro vypij.
     * 
     * @param nazev hledaný název
     */
    public void vypij(String nazev) throws Exception {
        if (!batoh.jeVecVBatohu(nazev)) {
            throw new Exception("Takovou věc nemáš v batohu k pití.");
        }
        
        String cistyNazev = cz.vse.adventura.logika.TextUtil.odstranDiakritiku(nazev);
        if (!cistyNazev.equals("napoj") && !cistyNazev.equals("plna_lahev") && !cistyNazev.equals("plna_lahev_od_sava")) {
            throw new Exception("Tohle se nedá pít.");
        }
        batoh.odeberVec(nazev);
    }

    /**
     * Metoda pro kupVec.
     * 
     * @param nazev hledaný název
     */
    public void kupVec(String nazev) throws Exception {
        if (!aktualniProstor.jeVecVProstoru("automat") && !aktualniProstor.jeVecVProstoru("LOKNI_SI_automat")
                && !aktualniProstor.jeVecVProstoru("nápojový_automat")) {
            throw new Exception("Tady není automat, kde by se dalo nakupovat.");
        }
        if (!batoh.jeVecVBatohu("mince")) {
            throw new Exception("Nemáš žádné mince na nákup.");
        }
        if (!cz.vse.adventura.logika.TextUtil.odstranDiakritiku(nazev).equals("napoj")) {
            throw new Exception("Automat prodává pouze 'nápoj'.");
        }

        batoh.odeberVec("mince");
        aktualniProstor.pridejVec(new Vec("nápoj", true, 2));
    }

    /**
     * Metoda pro prozkoumejVec.
     * 
     * @param nazev hledaný název
     * @return logický výsledek volání
     */
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

    /**
     * Vrátí odkaz na aktuální prostor (místnost), ve kterém se hráč právě nachází.
     * 
     * @return logický výsledek volání
     */
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }

    /**
     * Nastaví aktuální prostor (místnost), do kterého se hráč přesunul.
     * 
     * @param prostor požadovaný vstup prostor
     */
    public void setAktualniProstor(Prostor prostor) {
        aktualniProstor = prostor;
    }

    /**
     * Metoda pro getPopisObsahuBatohu.
     * 
     * @return logický výsledek volání
     */
    public String getPopisObsahuBatohu() {
        return batoh.getPopisObsahu();
    }
}
