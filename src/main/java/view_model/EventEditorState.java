package view_model;

import entity.event.Event;

/**
 * state of event editor
 */
public class EventEditorState {
    private Event eventToEdit;

    /**
     * create instance of state
     */
    public EventEditorState() {
        this.eventToEdit = null;
    }

    /**
     * access event
     * @return event
     */
    public Event getEventToEdit() {
        return eventToEdit;
    }

    /**
     * update event
     * @param eventToEdit to be edited
     */
    public void setEventToEdit(Event eventToEdit) {
        this.eventToEdit = eventToEdit;
    }
}
