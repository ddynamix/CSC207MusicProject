package use_case.edit_event.interface_adapter;

import entity.event.Event;
import use_case.edit_event.EditEventInputBoundary;
import use_case.edit_event.EditEventInputData;

/**
 * controller for edit event use case
 */
public class EditEventController {
    private final EditEventInputBoundary editEventInteractor;

    /**
     * create instance of controller for edit event use case
     * @param editEventInteractor
     */
    public EditEventController(EditEventInputBoundary editEventInteractor) {
        this.editEventInteractor = editEventInteractor;
    }

    /**
     * update event
     * @param event new event
     */
    public void editEvent(Event event) {
        editEventInteractor.editEvent(new EditEventInputData(event));
    }

    /**
     * remove event
     * @param event event to be removed
     */
    public void deleteEvent(Event event) {
        editEventInteractor.deleteEvent(new EditEventInputData(event));
    }

    /**
     * update event
     * @param eventToAlter current event standing
     * @param title of event
     * @param description of event
     * @param date of event
     * @param tags applied to event
     * @param media of event
     */
    public void updateEvent(Event eventToAlter, String title, String description, String date, String tags, String media) {
        editEventInteractor.updateEvent(new EditEventInputData(eventToAlter, title, description, date, tags, media));
    }
}
