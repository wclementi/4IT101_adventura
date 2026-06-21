package cz.vse.adventura;

import cz.vse.adventura.logika.Hra;
import cz.vse.adventura.logika.IHra;
import cz.vse.adventura.uiText.TextoveRozhrani;

/**
 * Hlavní spouštěcí třída aplikace.
 *
 * @author Kajetan Franciszek Wielgosz
 */
public class Start
{
    /**
     * Metoda pro main.
     * @param args požadovaný vstup args
     */
    public static void main(String[] args)
    {
        IHra hra = new Hra();
        TextoveRozhrani ui = new TextoveRozhrani(hra);
        
        if (args.length == 0) {
            ui.hraj();
        } else {
            ui.hrajZeSouboru(args[0]);
        }
    }
}
