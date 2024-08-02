package data_access;

import entity.event.Event;

import java.util.ArrayList;
import java.util.Map;

public interface EventDataAccessInterface {
    boolean eventExists(String eventName);
    void createEvent(Event event) throws EventAlreadyExistsException;
    void deleteEvent(Event event) throws EventDoesntExistException;
    Event getEventFromTitle(String eventName);
    ArrayList<Event> getEvents();
    void updateEvent(Event event, String title, String description, String date, String tags, String media) throws EventDoesntExistException;
}
