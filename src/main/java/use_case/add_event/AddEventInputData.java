package use_case.add_event;

import entity.event.Event;

public class AddEventInputData {
    private final Event eventToAddOrRemove;

    public AddEventInputData(Event eventToAddOrRemove) {
        this.eventToAddOrRemove = eventToAddOrRemove;
    }

    public Event getEventToAddOrRemove() {
        return eventToAddOrRemove;
    }
}
