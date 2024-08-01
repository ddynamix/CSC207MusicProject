package use_case.edit_event;

import entity.event.Event;

public class EditEventInputData {
    private final Event eventToAlter;

    public EditEventInputData(Event eventToAlter) {
        this.eventToAlter = eventToAlter;
    }

    public Event getEventToAlter() {
        return eventToAlter;
    }
}
