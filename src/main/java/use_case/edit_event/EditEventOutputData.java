package use_case.edit_event;

import entity.event.Event;

/**
 * output data for edit event use case
 */
public class EditEventOutputData {
    private final Event getEventToEdit;

    /**
     * create instance of output data for edit event use case
     * @param getEventToEdit current event
     */
    public EditEventOutputData(Event getEventToEdit) {
        this.getEventToEdit = getEventToEdit;
    }

    /**
     * access current event
     * @return current event
     */
    public Event getGetEventToEdit() {
        return getEventToEdit;
    }
}
