package data_access;

import entity.event.Event;

import java.util.Map;

public interface EventDataAccessInterface {
    boolean eventExists(String eventName);
    void createEvent(Event event) throws EventAlreadyExistsException;
    void deleteEvent(Event event) throws EventDoesntExistException;
    Event getEventFromTitle(String eventName) throws EventDoesntExistException;
    Map<String, Event> getEvents();
}
