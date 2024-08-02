package use_case.edit_event;

import entity.event.Event;

import java.util.ArrayList;

public class EditEventsSuccessOutputData {
    private final ArrayList<Event> events;

    public EditEventsSuccessOutputData(ArrayList<Event> events) {
        this.events = events;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }
}
