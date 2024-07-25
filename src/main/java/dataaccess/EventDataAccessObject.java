package dataaccess;

import entity.event.Event;

import java.util.Map;

public class EventDataAccessObject implements EventDataAccessInterface {

    public static class EventAlreadyExistsException extends Exception {
        public EventAlreadyExistsException() {
            super("Event already exists in the database");
        }
    }

    public static class EventDoesntExistException extends Exception {
        public EventDoesntExistException() {
            super("Event does not exist in the database");
        }
    }

    public EventDataAccessObject() {

    }

    @Override
    public boolean eventExists(String eventName) {
        return false;
    }

    @Override
    public void createEvent(Event event) throws EventAlreadyExistsException {
        throw new EventAlreadyExistsException();
    }

    @Override
    public void deleteEvent(Event event) throws EventDoesntExistException {
        throw new EventDoesntExistException();
    }

    @Override
    public Event getEventFromTitle(String eventName) throws EventDoesntExistException {
        throw new EventDoesntExistException();

    }
    @Override
    public Map<String, Event> getEvents() {

        return null;
    }
}
