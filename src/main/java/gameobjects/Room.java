package gameobjects;

import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

/**
 * This class represents a game room, which contains things, characters, exits to other rooms,...
 * A room can be locked, so it can't be entered.
 *
 * @author Martin Lukáš
 * @version 1.0
 */
public class Room {
    private final String name;
    private final String description;
    private boolean isLocked;
    private final Set<Room> exits;
    private final Map<String, Thing> setOfThings; // TODO replace map with set
    private Character character;
    
    public Room(String name, String description, boolean isLocked) {
        this.name = name;
        this.description = description;
        this.isLocked = isLocked;
        exits = new HashSet<>();
        setOfThings = new HashMap<>();
    }
    
    /**
     * Inserts a thing into the room.
     *
     * @param thing the inserted thing
     */
    public void insertThing(Thing thing) {
        setOfThings.put(thing.getName(), thing);
    }
    
    /**
     * Method inserts the character into the room.
     *
     * @param character character being inserted into the room
     */
    public void setCharacter(Character character) {
        this.character = character;
    }
    
    /**
     * Method checks if the room contains a thing with the name provided.
     *
     * @param thingName name of the thing
     * @return {@code true} if it contains it, {@code false} otherwise
     */
    public boolean containsThing(String thingName) {
        return setOfThings.containsKey(thingName);
    }
    
    /**
     * Method checks if the room contains a character (any character).
     *
     * @return {@code true} if the room contains a character, {@code false} if it doesn't
     */
    public boolean containsCharacter() {
        return (character != null);
    }
    
    /**
     * Returns the neighboring room based on the name of the room provided
     * as a parameter. If there is no such neighboring room, returns {@code null}.
     *
     * @param neighborRoomName name of the neighboring room
     * @return neighboring room if found, {@code null} otherwise
     */
    public Room getNeighborRoom(String neighborRoomName) {
        if (!neighborRoomName.isEmpty()) {
            for (Room neighborRoom : exits) {
                if (neighborRoom.getName().equals(neighborRoomName)) {
                    return neighborRoom;
                }
            }
        }
        return null;
    }
    
    public boolean isLocked() {
        return isLocked;
    }
    
    public void setLocked(boolean newLockedValue) {
        this.isLocked = newLockedValue;
    }
    
    /**
     * Returns the thing if it's in the room, no matter if it's moveable or not.
     *
     * @param thingName name of the thing
     * @return the searched thing, or {@code null}
     */
    public Thing getThing(String thingName) {
        Thing foundThing;
        if (setOfThings.containsKey(thingName)) {
            foundThing = setOfThings.get(thingName);
            return foundThing;
        }
        return null;
    }
    
    /**
     * Methos takes the thing from the room. After taking it, the thing disappears from the room.
     * It's only possible if the thing is moveable.
     *
     * @param thingName name of the thing
     * @return {@code Thing} object, if found, {@code null} otherwise
     */
    public Thing takeThing(String thingName) {
        Thing foundThing = null;
        if (setOfThings.containsKey(thingName)) {
            foundThing = setOfThings.get(thingName);
            if (foundThing.isMoveable()) {
                setOfThings.remove(thingName);
            } else {
                foundThing = null;
            }
        }
        return foundThing;
    }
    
    /**
     * Returns the character in the room.
     *
     * @return {@code Character} object if present, {@code null} otherwise
     */
    public Character getCharacter() {
        return character;
    }
    
    /**
     * Adds exit to this room. Checks if there isn't a circular reference.
     *
     * @param neighborRoom room neighboring to this one
     */
    public void setExit(Room neighborRoom) {
        if (neighborRoom != this) {
            exits.add(neighborRoom);
        }
    }
    
    /**
     * Returns long description of the room, which includes the description itself,
     * possible exits and characters present.
     *
     * @return long description of the room
     */
    public String longDescription() {
        return "Jsi v prostoru " + name + " - " + description + ".\n"
                + listOfExits()
                + listOfThings()
                + "postavy: " + character.getName();
    }
    
    /**
     * Returns list of exits form the room as a string.
     *
     * @return list of exits as a string
     */
    public String listOfExits() {
        StringBuilder listOfExits = new StringBuilder("východy:");
        for (Room neighborRoom : exits) {
            listOfExits.append(" ").append(neighborRoom.getName());
        }
        return listOfExits + "\n";
    }
    
    public String getName() {
        return name;
    }
    
    public Set<Room> getExits() {
        return exits;
    }
    
    /**
     * Method for judging equality of rooms. Rooms are equal if they have the same name.
     *
     * @param obj compared room
     * @return {@code true} if same, {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Room) {
            Room druhy = (Room) obj;
            return name.equals(druhy.name);
        } else {
            return false;
        }
    }
    
    /**
     * Method for creating hashcode of the object. Hashcode has to consider the name only,
     * because it needs to return the same value for objects that are also equal.
     *
     * @return výsledek výpočtu
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }
    
    private String listOfThings() {
        StringBuilder listOfThings = new StringBuilder("věci:");
        for (String thingName : setOfThings.keySet()) {
            listOfThings.append(" ").append(thingName);
        }
        return listOfThings + "\n";
    }
    
    /*
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     * TODO check if I'm not passing any modifiable structures
     */
//    public Collection<Prostor> getVychody() {
//        return Collections.unmodifiableCollection(vychody);
//    }
}