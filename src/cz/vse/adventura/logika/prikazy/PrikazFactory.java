package cz.vse.adventura.logika.prikazy;
import cz.vse.adventura.logika.*;
import cz.vse.adventura.logika.predmety.*;
import cz.vse.adventura.logika.prikazy.*;
import cz.vse.adventura.logika.npc.*;

// ukol 12. tyden: Factory
public class PrikazFactory {
    
    public static IPrikaz vytvorPrikaz(String typPrikazu, HerniPlan plan, SeznamPrikazu seznamPrikazu, Hra hra) {
        switch (typPrikazu) {
            case "jdi":
                return new PrikazJdi(plan, hra);
            case "konec":
                return new PrikazKonec(hra);
            case "seber":
                return new PrikazSeber(plan);
            case "polož":
                return new PrikazPoloz(plan);
            case "vypiš":
                return new PrikazVypis(plan);
            case "pij":
                return new PrikazPij(plan, hra);
            case "kup":
                return new PrikazKup(plan);
            case "prozkoumej":
                return new PrikazProzkoumej(plan);
            case "promluv":
                return new PrikazPromluv(plan);
            case "naplň":
                return new PrikazNapln(plan);
            case "nápověda":
                return new PrikazNapoveda(seznamPrikazu);
            case "sedni":
                return new PrikazSedni(plan, hra);
            case "rozbij":
                return new PrikazRozbij(plan, hra);
            default:
                return null;
        }
    }
}
