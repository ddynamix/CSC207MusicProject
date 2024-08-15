package data_access;

/**
 * exception when trying to edit an event that is not present in database
 */
public class EventDoesntExistException extends Exception {
    public EventDoesntExistException() {
        super("Event does not exist in the database");
    }
}
