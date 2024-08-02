package view_model;

import entity.event.Event;

public class EventEditorState {
    private Event eventToEdit;

    public EventEditorState() {
        this.eventToEdit = null;
    }

    public Event getEventToEdit() {
        return eventToEdit;
    }

    public void setEventToEdit(Event eventToEdit) {
        this.eventToEdit = eventToEdit;
    }
}
