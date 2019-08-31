package com.lukas.adventuregame.commands;

import com.lukas.adventuregame.gamelogic.GamePlan;
import com.lukas.adventuregame.gameobjects.Character;
import com.lukas.adventuregame.gameobjects.Room;

/**
 * Class for the talk command.
 *
 * @author Martin Lukáš
 * @version 1.0
 */
public class TalkCommand implements ICommand {

    private final String name;
    private final GamePlan plan;

    public TalkCommand(GamePlan plan, String name) {
        this.plan = plan;
        this.name = name;
    }

    /**
     * Method executes the talk command.
     *
     * @param parameters - parametry příkazu
     * @return proslov postavy nebo chybová hláška
     */
    public String execute(String... parameters) {
        if (parameters.length == 0) {
            return "S kým mám promluvit? Musíš zadat jméno postavy.";
        }
        String characterName = parameters[0];
        Room currentRoom = plan.getCurrentRoom();
        Character character = currentRoom.getCharacter();
       
        if (character != null && character.getName().equals(characterName)) {
            return currentRoom.getCharacter().getDialog();
        }
        return "Taková postava tu není.";
    }

    @Override
    public String getName() {
        return name;
    }
}
