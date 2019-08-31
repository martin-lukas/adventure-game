package com.lukas.adventuregame.commands;

import com.lukas.adventuregame.gamelogic.Game;

/**
 * Command quit for ending the game. prematurely.
 */
public class QuitCommand implements ICommand {

    private final String name;
    private final Game game;

    public QuitCommand(Game game, String name) {
        this.game = game;
        this.name = name;
    }
    
    /**
     * Returns the ending message and ends the game, or an error message.
     *
     * @param parameters parameters of the command
     * @return end message, or an error message if there were parameters with this command
     */
    @Override
    public String execute(String... parameters) {
        if (parameters.length > 0) {
            return "Ukončit co? Nechápu, proč jste zadal druhé slovo.";
        } else {
            game.setFinished(true);
            return "Hra ukončena příkazem konec.";
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
