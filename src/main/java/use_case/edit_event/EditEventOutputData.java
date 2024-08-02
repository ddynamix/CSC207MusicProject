package use_case.edit_event;

import entity.event.Event;

public class EditEventOutputData {
    private final Event getEventToEdit;

    public EditEventOutputData(Event getEventToEdit) {
        this.getEventToEdit = getEventToEdit;
    }

    public Event getGetEventToEdit() {
        return getEventToEdit;
    }
}
