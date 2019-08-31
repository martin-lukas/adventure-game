package commands;

import gamelogic.Backpack;
import gamelogic.GamePlan;
import gameobjects.Room;
import gameobjects.Thing;

/**
 * Instance třídy PrikazSeber představují ...
 *
 * @author Martin Lukáš
 * @version 1.0
 */
public class TakeCommand implements ICommand {
    private final String name;
    private final GamePlan plan;
    private final Backpack backpack;
    
    public TakeCommand(GamePlan plan, Backpack backpack, String name) {
        this.plan = plan;
        this.backpack = backpack;
        this.name = name;
    }
    
    /**
     * Method executes the take command by taking the thing from the room and inserting it
     * into the backpack, if possible.
     *
     * @param parameters - parameters of the command
     * @return success message or an error message
     */
    @Override
    public String execute(String... parameters) {
        if (parameters.length == 0) {
            // pokud chybí druhé slovo , tak ....
            return "Co mám sebrat? Musíš zadat jméno věci.";
        }
        String thingName = parameters[0];
        Room currentRoom = plan.getCurrentRoom();
        if (currentRoom.containsThing(thingName)) {
            Thing foundThing = plan.getCurrentRoom().takeThing(thingName);
            if (foundThing == null) {
                if (thingName.equals("svícen")) {
                    return "Teď jej nemůžeš vzít. Kníže by se probudil.";
                } else {
                    return "Tato věc není přenositelná.";
                }
            }
            // means we successfully took the thing
            
            // Attempting to put the thing in the backpack (might not fit)
            backpack.insertThing(foundThing);
            if (backpack.containsThing(foundThing.getName())) {
                plan.notifyObservers();
                backpack.notifyObservers();
                return "Sebral jsi " + thingName + ".";
            }
            
            currentRoom.insertThing(foundThing);
            return "Věc " + thingName + " se nevejde do batohu.";
        }
        return "Taková věc tu není.";
    }
    
    @Override
    public String getName() {
        return name;
    }
}
