package cz.vse.adventura.logika;

import java.text.Normalizer;

/**
 * Pomocná třída pro práci s textem.
 * 
 * @author Kajetan Franciszek Wielgosz
 */
public class TextUtil {
    
    /**
     * Odstraní z textu diakritiku a převede jej na malá písmena.
     * @param text vstupní text
     * @return text bez diakritiky a malými písmeny
     */
    public static String odstranDiakritiku(String text) {
        if (text == null) {
            return null;
        }
        return Normalizer.normalize(text, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .toLowerCase();
    }
}
