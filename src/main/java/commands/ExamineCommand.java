package commands;

import gamelogic.GamePlan;
import gameobjects.Room;
import gameobjects.Thing;

import java.util.ArrayList;
import java.util.List;

/**
 * Search command, that examines the provided object. Usually used for finding hidden things.
 *
 * @author Martin Lukáš
 * @version 1.0
 */
public class ExamineCommand implements ICommand {

    private final String name;
    private final GamePlan plan;
    private List<Thing> listOfThings;
    
    public ExamineCommand(GamePlan plan, String name) {
        this.plan = plan;
        this.name = name;
        this.listOfThings = new ArrayList<>();
    }

    /**
     * Method executes the examine command.  Checks if the thing can be examined.
     *
     * @param parameters - jméno prohledávané věci
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String execute(String... parameters) {
        if (parameters.length == 0) {
            return "Co mám prohledat? Musíš napsat jméno věci.";
        }
        String thingName = parameters[0];
        Room currentRoom = plan.getCurrentRoom();
        Thing thing = currentRoom.getThing(thingName);
        if (thing == null) {
            return "Tato věc není v prostoru.";
        }
        if (thing.isExamined()) {
            return thing.listOfThings();
        }
        if (thing.isStorage()) {
            listOfThings = thing.getThings();
            if (listOfThings != null) {
                for (Thing listOfThing : listOfThings) {
                    currentRoom.insertThing(listOfThing);
                }
            }
            thing.setExamined(true);
            return thing.listOfThings();
        } else {
            return "Tato věc se nedá prohledat.";
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
