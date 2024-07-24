package data_access;

public class EventDoesntExistException extends Exception {
    public EventDoesntExistException() {
        super("Event does not exist in the database");
    }
}
