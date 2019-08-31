package util;

import gameobjects.Room;

/**
 * Interface for the room observers.
 * 
 * @author Martin Lukáš
 * @version 1.0
 */
public interface RoomChangeObserver {

    /**
     * Method updates the room observers states.
     *
     * @param currentRoom current room
     */
    void update(Room currentRoom);
}