package util;

import logika.Batoh;

/**
 * Rozhraní observeru pro batoh.
 * 
 * @author Martin Lukáš
 * @version 1.0
 */
public interface ObserverZmenyBatohu {
    
    /**
     * Metoda, ve které proběhne aktualizace pozorovatele, je volána
     * prostřednictvím metody upozorniPozorovatele z rozhraní
     * SubjektZmenyBatohu
     *
     * @param aktualniVeci aktualni veci v batohu
     */
    public void aktualizuj(Batoh aktualniVeci);
}