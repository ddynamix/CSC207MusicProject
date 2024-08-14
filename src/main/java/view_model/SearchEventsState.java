package view_model;

import entity.event.Event;

import java.util.ArrayList;

public class SearchEventsState {
    private ArrayList<Event> eventsToDisplay;

    public SearchEventsState() {
        this.eventsToDisplay = null;
    }

    public void setEventsToDisplay(ArrayList<Event> eventsToDisplay) {
        this.eventsToDisplay = eventsToDisplay;
    }

    public ArrayList<Event> getEventsToDisplay() {
        return eventsToDisplay;
    }
}
