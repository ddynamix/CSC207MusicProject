package data_access;

import entity.event.Event;

import java.util.ArrayList;
import java.util.Map;

public interface EventDataAccessInterface {
    boolean eventExists(String eventName);
    void createEvent(Event event) throws EventDataAccessObject.EventAlreadyExistsException;
    void deleteEvent(Event event) throws EventDataAccessObject.EventDoesntExistException;
    Event getEventFromTitle(String eventName) throws EventDataAccessObject.EventDoesntExistException;
    Map<String, Event> getEvents();
}
