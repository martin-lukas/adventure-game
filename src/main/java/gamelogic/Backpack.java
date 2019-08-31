package gamelogic;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

import gameobjects.Thing;
import util.BackpackChangeObserver;
import util.BackpackChangeObservable;

/**
 * Backpack symbolizes a place where the player carries things that they collect.
 * It will have a limited capacity. Also, this class implements the {@code Observer}
 * design pattern, so whenever there is a change in the backpack's contents, all the registered
 * observers will be notified.
 *
 * @author Martin Lukáš
 * @version 1.0
 */
public class Backpack implements BackpackChangeObservable {
    
    private final Set<Thing> setOfThings = new HashSet<>();
    private final static int CAPACITY = 2; //maximum unositelných věcí
    private final List<BackpackChangeObserver> observerList;
    
    public Backpack() {
        observerList = new ArrayList<>();
    }
    
    /**
     * Inserts the thing into the backpack, if the capacity allows it.
     * Then it notifies all the observers about the change.
     *
     * @param thing the inserted thing
     */
    public void insertThing(Thing thing) {
        if (setOfThings.size() <= CAPACITY) {
            setOfThings.add(thing);
            this.notifyObservers();
        }
    }
    
    /**
     * Method removes the provided thing from the backpack.
     * Then it notifies all the observers about the change.
     *
     * @param thing the removed thing
     */
    public void removeThing(Thing thing) {
        if (setOfThings.contains(thing)) {
            setOfThings.remove(thing);
            this.notifyObservers();
        }
    }
    
    /**
     * Method checks, if the backpacks contains thing with a given name.
     *
     * @param thingName name of the sought after thing
     * @return {@code true} if it contains it, {@code false} otherwise
     */
    public boolean containsThing(String thingName) {
        for (Thing thing : setOfThings) {
            if (thing.getName().equals(thingName)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Method returns the {@code Thing} object with the given name from the backpack.
     *
     * @param name name of the thing
     * @return the {@code Thing} object, or if not found, {@code null}
     */
    public Thing getThing(String name) {
        for (Thing thing : setOfThings) {
            if (thing.getName().equals(name)) {
                return thing;
            }
        }
        return null;
    }
    
    public Set<Thing> getSetOfThings() {
        return setOfThings;
    }
    
    @Override
    public void registerObserver(BackpackChangeObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void unregisterObserver(BackpackChangeObserver observer) {
        observerList.remove(observer);
    }
   
    @Override
    public void notifyObservers() {
        for (BackpackChangeObserver observer : observerList) {
            observer.update(this);
        }
    }
}
