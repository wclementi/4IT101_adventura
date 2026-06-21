package cz.vse.adventura.uiText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import cz.vse.adventura.logika.IHra;

/**
 * Třída obstarávající textové uživatelské rozhraní hry.
 *
 * @author Kajetan Franciszek Wielgosz
 */
public class TextoveRozhrani {
    private IHra hra;
    private Scanner scanner;

    public TextoveRozhrani(IHra hra) {
        this.hra = hra;
        this.scanner = new Scanner(System.in);
    }

    private void vymazKonzoli() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Nouzový fallback pro vývojová prostředí, která nepodporují příkazy OS
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }

    /**
     * Metoda pro hraj.
     */
    public void hraj() {
        String posledniOdpoved = hra.vratUvitani();

        while (!hra.konecHry()) {
            vymazKonzoli();
            if (!posledniOdpoved.isEmpty()) {
                System.out.println(posledniOdpoved);
                System.out.println("\n======================================================================\n");
            }

            String radek = prectiString();
            posledniOdpoved = hra.zpracujPrikaz(radek);

            if (radek.startsWith("promluv") || radek.startsWith("log") || radek.startsWith("nápověda")) {
                vymazKonzoli();
                System.out.println(posledniOdpoved);
                System.out.println("\n[Stiskněte ENTER pro návrat do hry...]");
                scanner.nextLine();
                
                // Po návratu ukážeme znovu lokaci, aby hráč věděl, kde je
                if (!hra.konecHry()) {
                    posledniOdpoved = hra.getHerniPlan().getAktualniProstor().dlouhyPopis();
                } else {
                    posledniOdpoved = "";
                }
            }
        }

        vymazKonzoli();
        System.out.println(hra.vratEpilog());
    }

    /**
     * Metoda pro hrajZeSouboru.
     * @param nazevSouboru parametr metody
     */
    public void hrajZeSouboru(String nazevSouboru) {
        try (
            BufferedReader cteni = new BufferedReader(new FileReader(nazevSouboru));
            PrintWriter zapis = new PrintWriter(new FileWriter("vystup.txt"))
        ) {
            System.out.println(hra.vratUvitani());
            System.out.println("\n======================================================================\n");
            
            zapis.println(hra.vratUvitani());
            zapis.println("\n======================================================================\n");

            for (String radek = cteni.readLine(); radek != null && !hra.konecHry(); radek = cteni.readLine())
            {
                System.out.println("> " + radek);
                zapis.println("> " + radek);

                String vystup = hra.zpracujPrikaz(radek);

                System.out.println("\n----------------------------------------------------------------------");
                System.out.println(vystup);
                System.out.println("======================================================================\n");
                
                zapis.println("\n----------------------------------------------------------------------");
                zapis.println(vystup);
                zapis.println("======================================================================\n");
            }

            System.out.println(hra.vratEpilog());
            zapis.println(hra.vratEpilog());

        } catch (FileNotFoundException e) {
            File file = new File(nazevSouboru);
            System.out.println("Soubor nebyl nalezen!\nProhledávaná cesta byla: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Nelze hrát hru ze souboru, něco se pokazilo: " + e.getMessage());
        }
    }

    private String prectiString() {
        System.out.print("> ");
        return scanner.nextLine();
    }
}
