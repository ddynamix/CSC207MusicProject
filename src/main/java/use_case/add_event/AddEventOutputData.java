package use_case.add_event;

import entity.event.Event;

import java.util.ArrayList;

public class AddEventOutputData {
    private final ArrayList<Event> eventsToDisplay;

    public AddEventOutputData(ArrayList<Event> eventsToDisplay) {
        this.eventsToDisplay = eventsToDisplay;
    }

    public ArrayList<Event> getEventsToDisplay() {
        return eventsToDisplay;
    }
}
