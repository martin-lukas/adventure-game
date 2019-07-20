package ui;

import java.nio.charset.Charset;
import java.util.Scanner;
import logika.Hra;

/**
 * Class TextoveRozhrani
 *
 * Toto je uživatelského rozhraní aplikace Adventura Tato třída vytváří instanci
 * třídy Hra, která představuje logiku aplikace. Čte vstup zadaný uživatelem a
 * předává tento řetězec logice a vypisuje odpověď logiky na konzoli. Pokud
 * chcete hrát tuto hru, vytvořte instanci této třídy a poté na ní vyvolejte
 * metodu "hraj".
 *
 *
 * @author Martin Lukáš
 * @version 1.0
 */
public class TextoveRozhrani {

    private Hra hra;

    /**
     * Vytváří hru.
     */
    public TextoveRozhrani() {
        hra = new Hra();
    }

    /**
     * Hlavní metoda hry. Vypíše úvodní text a pak opakuje čtení a zpracování
     * příkazu od hráče do konce hry (dokud metoda konecHry() z logiky nevrátí
     * hodnotu true). Nakonec vypíše text epilogu.
     */
    public void hraj() {
        System.out.println(hra.vratUvitani());
        // Základní cyklus programu - opakovaně se čtou příkazy, dokud nenastane konec hry.
        while (!hra.konecHry()) {
            String radek = prectiString();
            System.out.println(hra.zpracujPrikaz(radek));
        }
        System.out.println(hra.vratEpilog());
    }

    /**
     * Metoda přečte příkaz z příkazového řádku.
     *
     * @return vrací přečtený příkaz jako instanci třídy String
     */
    private String prectiString() {
        Scanner scanner = new Scanner(System.in, "CP1250");
        System.out.print("> ");
        return scanner.nextLine();
    }
}
