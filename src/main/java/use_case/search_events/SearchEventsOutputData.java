package use_case.search_events;

import entity.event.Event;

import java.util.ArrayList;

/**
 * output data for event search use case
 */
public class SearchEventsOutputData {
    private final ArrayList<Event> allEvents;

    /**
     * create instance of output data for event search use case
     * @param allEvents list of all events in databas3
     */
    public SearchEventsOutputData(ArrayList<Event> allEvents) {
        this.allEvents = allEvents;
    }

    /**
     * access events
     * @return events
     */
    public ArrayList<Event> getAllEvents() {
        return allEvents;
    }
}
