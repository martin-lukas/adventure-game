package util;

import logika.Prostor;

/**
 * Rozhraní observeru pro prostory.
 * 
 * @author Martin Lukáš
 * @version 1.0
 */
public interface ObserverZmenyProstoru {

    /**
     * Metoda, ve které proběhne aktualizace pozorovatele, je volána
     * prostřednictvím metody upozorniPozorovatele z rozhraní
     * SubjektZmenyProstoru
     *
     * @param aktualniProstor - aktualni prostor
     */
    public void aktualizuj(Prostor aktualniProstor);
}