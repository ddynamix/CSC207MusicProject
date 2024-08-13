package use_case.edit_event;

/**
 * interface for edit event use case
 */
public interface EditEventInputBoundary {
    void editEvent(EditEventInputData inputData);
    void deleteEvent(EditEventInputData inputData);
    void updateEvent(EditEventInputData inputData);
}
