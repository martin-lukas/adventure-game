package commands;

import gamelogic.Backpack;
import gamelogic.GamePlan;
import gameobjects.Character;
import gameobjects.Room;
import gameobjects.Thing;

/**
 * Class for the command give. The method {@code execute()} gives a thing to a character
 * in the same room as the player.
 *
 * @author Martin Lukáš
 * @version 1.0
 */
public class GiveCommand implements ICommand {

    private final String name;
    private final GamePlan plan;
    private final Backpack backpack;

    public GiveCommand(GamePlan plan, Backpack backpack, String name) {
        this.plan = plan;
        this.backpack = backpack;
        this.name = name;
    }
    
    /**
     * This method executes the give command. A thing is given to the character present in the
     * current room. The thing given is specified in the first parameter of this command.
     * The return value is either the answer of the character, or error message.
     *
     * @param parameters parameters of the command
     * @return answer of the character, or an error message
     */
    @Override
    public String execute(String... parameters) {
        if (parameters.length == 0) {
            return "Co mám dát? Musíš napsat jméno věci.";
        }
        String thingName = parameters[0];
        Room currentRoom = plan.getCurrentRoom();
        Thing thing = backpack.getThing(thingName);
        Character character;
        if (currentRoom.containsCharacter()) {
            character = currentRoom.getCharacter();
        } else {
            return "Není tu nikdo, kdo by tuto věc chtěl.";
        }

        if (thing == null) {
            return "Tuto věc nemáš v batohu.";
        }

        if (thing.equals(character.getRequiredThing())) {
            backpack.removeThing(thing);
            backpack.notifyObservers();
            plan.notifyObservers();
            switch (thing.getName()) {
                case "víno":
                    currentRoom.getNeighborRoom("nádvoří").setLocked(false);
                    character.setDialog("Stráž: Co ještě chceš? Už můžeš jít.");
                    return "Díky, teď tedy můžeš projít.";
                case "chloroform":
                    currentRoom.getThing("svícen").setMoveable(true);
                    character.setDialog("Kníže tvrdě spí, takže už můžeš sebrat svícen.");
                    return "Kníže teď tvrdě spí. Seber svícen a rychle odsud!";
                case "whisky":
                    currentRoom.getNeighborRoom("svoboda").setLocked(false);
                    character.setDialog("Strážce: No tak jdi!");
                    return "Dobře, udělal jsi, co jsem chtěl. Teď můžeš jít. A už se nevracej!";
                default:
                    return "Tato věc neexistuje.";
            }
        }
        return "Postava v tomto prostoru tuto věc nechce.";
    }
    
    @Override
    public String getName() {
        return name;
    }
}
