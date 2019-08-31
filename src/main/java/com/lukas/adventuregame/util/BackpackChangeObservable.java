package com.lukas.adventuregame.util;

/**
 * Interface for the classes managing observers for their changes, based on the {@code Observer}
 * design pattern.
 * 
 * @author Martin Lukáš
 * @version 1.0
 */
public interface BackpackChangeObservable {
    
    /**
     * Method registers a new observer of the backpack contents.
     *
     * @param observer observer of the backpack contents
     */
    void registerObserver(BackpackChangeObserver observer);
    
    /**
     * Method unregisters the provided observer from the changes of the contents of the backpack.
     *
     * @param observer observer of the backpack
     */
    void unregisterObserver(BackpackChangeObserver observer);
    
    /**
     * Method notifies all observers of the changes in the backpack.
     */
    void notifyObservers();
}