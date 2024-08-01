package use_case.edit_event.interface_adapter;

import entity.event.Event;
import use_case.edit_event.EditEventInputBoundary;
import use_case.edit_event.EditEventInputData;

public class EditEventController {
    private final EditEventInputBoundary editEventInteractor;

    public EditEventController(EditEventInputBoundary editEventInteractor) {
        this.editEventInteractor = editEventInteractor;
    }

    public void editEvent(Event event) {
        editEventInteractor.editEvent(new EditEventInputData(event));
    }

    public void deleteEvent(Event event) {
        editEventInteractor.deleteEvent(new EditEventInputData(event));
    }
}
