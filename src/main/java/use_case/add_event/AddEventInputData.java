package use_case.add_event;

import entity.event.Event;

/**
 * input data for add event use case
 */
public class AddEventInputData {
    private final Event eventToAddOrRemove;

    /**
     * create instance of data
     * @param eventToAddOrRemove event in question
     */
    public AddEventInputData(Event eventToAddOrRemove) {
        this.eventToAddOrRemove = eventToAddOrRemove;
    }

    /**
     * access specific event
     * @return event in question
     */
    public Event getEventToAddOrRemove() {
        return eventToAddOrRemove;
    }
}
