package gamelogic;

import java.util.ArrayList;
import java.util.List;

import gameobjects.Character;
import gameobjects.Room;
import gameobjects.Thing;
import util.RoomChangeObserver;
import util.RoomChangeObservable;

/**
 * Class for creating the game environment with all the elements, like rooms, their connections,
 * things and characters in the rooms... It also uses the {@code Observer} pattern where whenever there
 * is a change in the room the player is in, the class updates the state of all the observers as well.
 *
 * @author Martin Lukáš
 * @version 1.0
 */
public class GamePlan implements RoomChangeObservable {

    private Room currentRoom;
    private Room endRoom;
    private final List<RoomChangeObserver> observerList;

    public GamePlan() {
        initGameMap();
        observerList = new ArrayList<>();
    }

    /**
     * Initializes the entire game map with all the rooms, characters and things in them.
     * Also sets the starting and end room.
     */
    private void initGameMap() {
        // Creating rooms
        Room fountain = new Room("kašna", "kašna se sousoším", false);
        Room arcade = new Room("podloubí", "podloubí, kde v noci prochází stráž", false);
        Room courtyard = new Room("nádvoří", "nádvoří, centrum zámku", true);
        Room hallway = new Room("chodba", "chodba, která vede ke komnatám", false);
        Room dukeChamber = new Room("komnata_knížete", "komnata, ve které sídlí kníže", false);
        Room gate = new Room("brána", "brána, průchod do vnějšího světa", false);
        Room freedom = new Room("svoboda", "", true);

        // Creating the graph representing all the room connections
        fountain.setExit(arcade);
        arcade.setExit(fountain);
        arcade.setExit(courtyard);
        courtyard.setExit(hallway);
        courtyard.setExit(gate);
        hallway.setExit(courtyard);
        hallway.setExit(dukeChamber);
        dukeChamber.setExit(hallway);
        gate.setExit(courtyard);
        gate.setExit(freedom);

        // Creating all the things in the game
        Thing sculpture = new Thing("sousoší", false, true);
        Thing wine = new Thing("víno", true, false, "wine.jpg");
        Thing chest = new Thing("truhla", false, true);
        Thing chloroform = new Thing("chloroform", true, false, "chloroform.jpg");
        Thing whiskey = new Thing("whisky", true, false, "whiskey.jpg");
        Thing candleholder = new Thing("svícen", false, false, "candleholder.jpg");

        // Inserts the things into their correct places.
        fountain.insertThing(sculpture);
        sculpture.insertThing(wine);
        hallway.insertThing(chest);
        chest.insertThing(chloroform);
        chest.insertThing(whiskey);
        dukeChamber.insertThing(candleholder);

        // Creates and iserts the characters into their positions.
        Character straz = new Character("stráž", "Stráž: Hej ty! Půjdeš se mnou... Ale ještě bych ti mohl dát šanci.\n"
                + "Když mi najdeš nějaké dobré víno, tak tě pustím dál.", wine);
        arcade.setCharacter(straz);
        Character knize = new Character("kníže", "Kníže spí, ale jeho spaní je lehké. Abys mohl ukrást svícen,\n"
                + "musíš jeho spaní něčím prohloubit", chloroform);
        dukeChamber.setCharacter(knize);
        Character strazceBrany = new Character("strážce_brány", "Strážce: Ty tu kradeš? Ále, mně už je to jedno, stejně si chci najít\n"
                + "jinou práci. Jen tak tě však nepustím, je mi zima a bodlo by něco ostřejšího.", whiskey);
        gate.setCharacter(strazceBrany);

        currentRoom = fountain;  // hra začíná u kašny
        endRoom = freedom; // hra končí na svobodě
    }
    
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Method sets the current room to the provided parameter, and notifies all oberserves.
     *
     * @param room new current room
     */
    public void setCurrentRoom(Room room) {
        currentRoom = room;
        this.notifyObservers();
    }
    
    public Room getEndRoom() {
        return endRoom;
    }
    
    @Override
    public void registerObserver(RoomChangeObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void unregisterObserver(RoomChangeObserver observer) {
        observerList.remove(observer);
    }
    
    @Override
    public void notifyObservers() {
        for (RoomChangeObserver observer : observerList) {
            observer.update(currentRoom);
        }
    }
}
