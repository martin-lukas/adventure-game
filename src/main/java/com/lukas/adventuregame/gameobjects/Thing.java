package com.lukas.adventuregame.gameobjects;

import java.util.List;
import java.util.ArrayList;

/**
 * Class for handling things in the game.
 *
 * @author Martin Lukáš
 * @version 1.0
 */
public class Thing {

    private final String name;
    private boolean isMoveable;
    private final List<Thing> listOfThings;
    private boolean isExamined = false;
    private final boolean isStorage; // if it's possible to store other things inside this thing
    private String imgUrl;
    
    public Thing(String name, boolean isMoveable, boolean isStorage) {
        this.name = name;
        this.isMoveable = isMoveable;
        this.isStorage = isStorage;
        this.listOfThings = new ArrayList<>();
    }
    
    public Thing(String name, boolean isMoveable, boolean isStorage, String imgUrl) {
        this(name, isMoveable, isStorage);
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    /**
     * Returns the list of things inside the thing.
     *
     * @return list of things inside
     */
    public List<Thing> getThings() {
        return listOfThings;
    }
    
    public boolean isStorage() {
        return isStorage;
    }

    public boolean isExamined() {
        return isExamined;
    }
    
    public void setExamined(boolean isProhledana) {
        this.isExamined = isProhledana;
    }

    /**
     * Method inserts thing inside this thing. The user should check if this thing is able to store
     * things. Otherwise the command examine won't be able to look inside this thing.
     *
     * @param thing thing to be inserted
     */
    public void insertThing(Thing thing) {
        listOfThings.add(thing);
    }
    
    public boolean isMoveable() {
        return isMoveable;
    }
    
    public void setMoveable(boolean newMoveable) {
        isMoveable = newMoveable;
    }

    /**
     *Returns the list of things inside this thing as a string.
     *
     * @return list of things inside
     */
    public String listOfThings() {
        StringBuilder listOfThings = new StringBuilder("věci ve věci:");
        for (Thing thing : this.listOfThings) {
            listOfThings.append(" ").append(thing.getName());
        }
        return listOfThings.toString();
    }
    
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * Metoda for judging equality of two things. They are equal if their names are equal.
     *
     * @param obj -compared object
     * @return {@code true} if equal, {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Thing) {
            Thing thing = (Thing) obj;
            return name.equals(thing.name);
        } else {
            return false;
        }
    }

    /**
     * Method for calculating hashcode. This method only uses the name of the thing
     * for the calculation.
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }
}