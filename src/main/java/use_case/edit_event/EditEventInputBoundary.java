package use_case.edit_event;

public interface EditEventInputBoundary {
    void editEvent(EditEventInputData inputData);
    void deleteEvent(EditEventInputData inputData);
    void updateEvent(EditEventInputData inputData);
}