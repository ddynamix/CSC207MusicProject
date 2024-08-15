package data_access;

/**
 * Exception for creating an event when an event with the same title already exists
 */
public class EventAlreadyExistsException extends Exception{
    public EventAlreadyExistsException() {
        super("Event already exists in the database");
    }
}
