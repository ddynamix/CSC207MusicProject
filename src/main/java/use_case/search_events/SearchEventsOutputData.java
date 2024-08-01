package use_case.search_events;

import entity.event.Event;

import java.util.ArrayList;

public class SearchEventsOutputData {
    private final ArrayList<Event> allEvents;

    public SearchEventsOutputData(ArrayList<Event> allEvents) {
        this.allEvents = allEvents;
    }

    public ArrayList<Event> getAllEvents() {
        return allEvents;
    }
}
