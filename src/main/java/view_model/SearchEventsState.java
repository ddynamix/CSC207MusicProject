package view_model;

import entity.event.Event;

import java.util.ArrayList;

/**
 * state for search events screen
 */
public class SearchEventsState {
    private ArrayList<Event> eventsToDisplay;

    /**
     * create instance of state
     */
    public SearchEventsState() {
        this.eventsToDisplay = null;
    }

    /**
     * change events
     * @param eventsToDisplay to be set
     */
    public void setEventsToDisplay(ArrayList<Event> eventsToDisplay) {
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
