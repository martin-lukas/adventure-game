package util;

import gameobjects.Room;

/**
 * Rozhraní observeru pro prostory.
 * 
 * @author Martin Lukáš
 * @version 1.0
 */
public interface RoomChangeObserver {

    /**
     * Metoda, ve které proběhne aktualizace pozorovatele, je volána
     * prostřednictvím metody upozorniPozorovatele z rozhraní
     * SubjektZmenyProstoru
     *
     * @param aktualniRoom - aktualni prostor
     */
    void update(Room aktualniRoom);
}