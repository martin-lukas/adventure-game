package com.lukas.adventuregame.commands;

import com.lukas.adventuregame.gamelogic.Game;
import com.lukas.adventuregame.gameobjects.Room;

/**
 * Command go to a room. With this, the player can move around the map.
 *
 * @author Martin Lukáš
 * @version 1.0
 */
public class GoCommand implements ICommand {
    
    private final Game game;
    private final String name;
    
    public GoCommand(Game game, String name) {
        this.game = game;
        this.name = name;
    }
    
    /**
     * The method executes the go command. With this command, the player can move around the map.
     * The mothd checks if the room provided as a parameter is a valid location to go to. Also check if the
     * room is the end room.
     *
     * @param parameters parameters of the command
     * @return if successful, returns the long description of the new room, otherwise an error message
     */
    @Override
    public String execute(String... parameters) {
        if (parameters.length == 0) {
            return "Kam mám jít? Musíš zadat jméno východu.";
        }
        
        String targetRoomName = parameters[0];
        if (game.getGamePlan().getCurrentRoom().getName().equals(targetRoomName)) {
            return "Tady už jsi.";
        }
        
        // trying to enter
        Room targetRoom = game.getGamePlan().getCurrentRoom().getNeighborRoom(targetRoomName);
        
        if (targetRoom == null) {
            return "Tam se odsud jít nedá!";
        } else if (targetRoom.isLocked()) {
            return "Tento prostor je zamčený. Teď tam nemůžeš.";
        } else if (targetRoomName.equals("svoboda")) {
            if (game.getBackpack().containsThing("svícen")) {
                game.getGamePlan().setCurrentRoom(targetRoom);
                return "A jsi na svobodě. Teď jenom najít další místo na vyloupení.";
            } else {
                return "Tam ještě nemůžeš. Něco jsi zapomněl.";
            }
        } else {
            game.getGamePlan().setCurrentRoom(targetRoom);
            return targetRoom.longDescription();
        }
    }
    
    @Override
    public String getName() {
        return name;
    }
}

