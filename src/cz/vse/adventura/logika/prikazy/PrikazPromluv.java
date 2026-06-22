package cz.vse.adventura.logika.prikazy;

import cz.vse.adventura.logika.HerniPlan;
import cz.vse.adventura.logika.Prostor;
import cz.vse.adventura.logika.npc.NPCPouzeMluvici;

/**
 * Příkaz pro zahájení dialogu s NPC postavou.
 *
 * @author Kajetan Franciszek Wielgosz
 */
public class PrikazPromluv implements IPrikaz {
    private static final String NAZEV = "promluv";
    private HerniPlan plan;

    public PrikazPromluv(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "S kým mám promluvit? (Zadej např. 'promluv s ucitel' nebo 'promluv ucitel')";
        }

        String jmenoNpc;
        String cisteSlovo = cz.vse.adventura.logika.TextUtil.odstranDiakritiku(parametry[0]);
        if (cisteSlovo.equals("s") && parametry.length > 1) {
            jmenoNpc = parametry[1];
        } else if (cisteSlovo.equals("s")) {
            return "S kým?";
        } else {
            jmenoNpc = parametry[0];
        }

        Prostor aktualniProstor = plan.getAktualniProstor();
        NPCPouzeMluvici postava = aktualniProstor.getPostava(jmenoNpc);

        if (postava == null) {
            return "Nikdo s takovým jménem tu není.";
        }

        return postava.mluv();
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }

    @Override
    public String getPouziti() {
        return "promluv <jméno postavy>";
    }
}
