package util;

/**
 * Interface for observable object that propagates changes of {@code Room} object
 * to its observers.
 *
 * @author Martin
 */
public interface RoomChangeObservable {

    /**
     * Method registers oberserver for the observable {@code Room} object.
     *
     * @param observer room observer
     */
    void registerObserver(RoomChangeObserver observer);

    /**
     * Method unregisters observer for the observable {@code Room} object.
     *
     * @param observer unregistered observer
     */
    void unregisterObserver(RoomChangeObserver observer);

    /**
     * Method notifies all observers of the change of state of the room.
     */
    void notifyObservers();
}
