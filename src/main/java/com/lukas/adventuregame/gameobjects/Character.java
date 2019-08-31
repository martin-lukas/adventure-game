package com.lukas.adventuregame.gameobjects;

/**
 * Class for creating the characters in the game.
 *
 * @author Martin Lukáš
 * @version 1.0
 */
public class Character {

    private final String name;
    private String dialog;
    private final Thing requiredThing;
    
    public Character(String name, String dialog, Thing requiredThing) {
        this.name = name;
        this.dialog = dialog;
        this.requiredThing = requiredThing;
    }

    public String getName() {
        return name;
    }
    
    public String getDialog() {
        return dialog;
    }
    
    public void setDialog(String novyProslov) {
        this.dialog = novyProslov;
    }

    public Thing getRequiredThing() {
        return requiredThing;
    }
}
