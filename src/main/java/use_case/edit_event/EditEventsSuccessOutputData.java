package use_case.edit_event;

import entity.event.Event;

import java.util.ArrayList;

/**
 * output data for edit event use case
 */
public class EditEventsSuccessOutputData {
    private final ArrayList<Event> events;

    /**
     * create instance of output data for edit event use case
     * @param events data
     */
    public EditEventsSuccessOutputData(ArrayList<Event> events) {
        this.events = events;
    }

    /**
     * access events
     * @return list of events
     */
    public ArrayList<Event> getEvents() {
        return events;
    }
}
