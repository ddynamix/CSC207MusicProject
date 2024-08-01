package data_access;

import entity.event.Event;

import java.util.Map;

public interface EventDataAccessInterface {
    boolean eventExists(String eventName);
    void createEvent(Event event) throws EventAlreadyExistsException, EventDataAccessObject.EventAlreadyExistsException;
    void deleteEvent(Event event) throws EventDoesntExistException, EventDataAccessObject.EventDoesntExistException;
    Event getEventFromTitle(String eventName) throws EventDoesntExistException, EventDataAccessObject.EventDoesntExistException;
    Map<String, Event> getEvents();
}
