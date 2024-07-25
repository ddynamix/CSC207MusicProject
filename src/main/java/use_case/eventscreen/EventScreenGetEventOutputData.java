package use_case.eventscreen;

import entity.event.Event;

import java.util.ArrayList;

public class EventScreenGetEventOutputData {
    private ArrayList<Event> events;

    public EventScreenGetEventOutputData(ArrayList<Event> events) {
        this.events = events;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }
}
