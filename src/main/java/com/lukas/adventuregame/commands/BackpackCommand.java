package com.lukas.adventuregame.commands;

import com.lukas.adventuregame.commands.ICommand;
import com.lukas.adventuregame.gamelogic.Backpack;
import com.lukas.adventuregame.gameobjects.Thing;

import java.util.Set;

/**
 * Class for the command backpack. The method {@code execute()} returns the list of things
 * in backpack in a string form. If there are parameters ater this command, the method
 * returns an error message.
 */
public class BackpackCommand implements ICommand {
    
    private final String name;
    private final Backpack backpack;
    
    public BackpackCommand(Backpack backpack, String name) {
        this.backpack = backpack;
        this.name = name;
    }
    
    /**
     * Provides the player with the contents of his backpack.
     * If no things are found, it returns an error message.
     *
     * @param parameters parameters of the command
     * @return string with contents of the backpack
     */
    @Override
    public String execute(String... parameters) {
        if (parameters.length == 0) {
            Set<Thing> setOfThings = backpack.getSetOfThings();
            if (setOfThings.isEmpty()) {
                return "V batohu nic není.\n";
            } else {
                StringBuilder listOfThings = new StringBuilder("Věci v batohu:");
                for (Thing thing : setOfThings) {
                    listOfThings.append("  ").append(thing.getName());
                }
                return listOfThings.toString();
            }
        } else {
            return "Tento příkaz nesmí mít parametry.";
        }
    }
    
    @Override
    public String getName() {
        return name;
    }
}
