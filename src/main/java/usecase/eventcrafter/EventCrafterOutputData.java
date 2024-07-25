package usecase.eventcrafter;

import entity.event.Event;

import java.util.ArrayList;

public class EventCrafterOutputData {
    private final ArrayList<Event> events;

    public EventCrafterOutputData(ArrayList<Event> events) {
        this.events = events;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }
}
