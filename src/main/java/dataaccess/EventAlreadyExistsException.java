package dataaccess;

public class EventAlreadyExistsException extends Exception{
    public EventAlreadyExistsException() {
        super("Event already exists in the database");
    }
}
