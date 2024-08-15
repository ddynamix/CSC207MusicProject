package use_case.eventcrafter;

import entity.event.Event;

import java.util.ArrayList;

/**
 * output data for event use case
 */
public class EventCrafterOutputData {
    private final ArrayList<Event> events;

    /**
     * create instance of output data for event use case
     * @param events to be saved and displayed
     */
    public EventCrafterOutputData(ArrayList<Event> events) {
        this.events = events;
    }

    /**
     * access events
     * @return events
     */
    public ArrayList<Event> getEvents() {
        return events;
    }
}
