package use_case.add_event;

import entity.event.Event;

import java.util.ArrayList;

/**
 * Output data for add event use case
 */
public class AddEventOutputData {
    private final ArrayList<Event> eventsToDisplay;

    /**
     * add event to data
     * @param eventsToDisplay new updated list
     */
    public AddEventOutputData(ArrayList<Event> eventsToDisplay) {
        this.eventsToDisplay = eventsToDisplay;
    }

    /**
     * access events
     * @return events
     */
    public ArrayList<Event> getEventsToDisplay() {
        return eventsToDisplay;
    }
}
