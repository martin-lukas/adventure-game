package util;

import gamelogic.Backpack;

/**
 * Observer interface for the change in backpack contents.
 * 
 * @author Martin Lukáš
 * @version 1.0
 */
public interface BackpackChangeObserver {
    
    /**
     * Method for updating the state of objects relying on the contents of the backpack.
     *
     * @param backpack new backpack
     */
    void update(Backpack backpack);
}