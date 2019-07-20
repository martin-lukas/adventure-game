package logika;

import java.util.List;
import java.util.ArrayList;

/**
 * Instance třídy Vec představují ...
 *
 * @author Martin Lukáš
 * @version 1.0
 */
public class Vec {

    private String nazev;
    private boolean prenositelna;
    private List<Vec> seznamVeci;
    private boolean isProhledana = false;
    private boolean ulozna;

    /**
     * Konstruktor
     *
     * @param nazev - název věci
     * @param prenositelna - přenositelnost věci
     * @param ulozna - zda se do ní dá něco uložit
     */
    public Vec(String nazev, boolean prenositelna, boolean ulozna) {
        this.nazev = nazev;
        this.prenositelna = prenositelna;
        this.ulozna = ulozna;
        this.seznamVeci = new ArrayList<Vec>();
    }

    /**
     * Vrací název věci.
     *
     * @return název věci
     */
    public String getNazev() {
        return nazev;
    }

    /**
     * Vrací seznam věcí vložených do jiné věci.
     *
     * @return seznam věcí ve věci
     */
    public List<Vec> getSeznam() {
        return seznamVeci;
    }

    /**
     * Metoda zkontroluje, zda je věc ve věci.
     *
     * @param vec - objekt veci
     * @return true, když věc je v věci, false když není
     */
    public boolean obsahujeVec(Vec vec) {
        return seznamVeci.contains(vec);
    }

    /**
     * Metoda vrací hodnotu proměnné ulozna, která říká, jestli se do věci dá
     * něco vložit.
     *
     * @return true, pokud je věc úložna, false, pokud ne
     */
    public boolean isUlozna() {
        return ulozna;
    }

    /**
     * Metoda vrací zda je věc ve věci prohledaná.
     *
     * @return true, pokud je prohledaná (použil se příkaz prohledej), false,
     * pokud ne
     */
    public boolean isProhledana() {
        return isProhledana;
    }

    /**
     * Nastavuje, zda je věc prohledaná.
     *
     * @param isProhledana - true pokud je prohledana, false, pokud ne
     */
    public void setProhledana(boolean isProhledana) {
        this.isProhledana = isProhledana;
    }

    /**
     * Metoda vloží věc do jiné věci
     *
     * @param vec - věc kterou chceme vkládat
     */
    public void vlozVec(Vec vec) {
        seznamVeci.add(vec);
    }

    /**
     * Zjišťuje přenositelnost věci.
     *
     * @return true, pokud je, false, pokud není
     */
    public boolean isPrenositelna() {
        return prenositelna;
    }

    /**
     * Nastavuje přenositelnost věci.
     *
     * @param novaPrenositelnost - nová hodnota atributu prenositelna
     */
    public void setPrenositelna(boolean novaPrenositelnost) {
        prenositelna = novaPrenositelnost;
    }

    /**
     * Vrátí výpis všech věcí ve věci.
     *
     * @return výpis věcí ve věci
     */
    public String nazvyVeci() {
        String nazvy = "věci ve věci:";
        for (Vec vec : seznamVeci) {
            nazvy += " " + vec.getNazev();
        }
        return nazvy;
    }

    /**
     * Metoda equals pro porovnání dvou věcí.
     *
     * @param obj - porovnávaný objekt
     * @return true, pokud má zadaná věc stejný název, jinak false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vec) {
            Vec druha = (Vec) obj;
            return nazev.equals(druha.nazev);
        } else {
            return false;
        }
    }

    /**
     * Metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object.
     *
     * @return výsledek výpočtu
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }
}