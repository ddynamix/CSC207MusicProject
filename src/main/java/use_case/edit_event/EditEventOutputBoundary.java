package use_case.edit_event;

/**
 * interface for output data for edit event use case
 */
public interface EditEventOutputBoundary {
    void goToEventEditor(EditEventOutputData outputData);
    void prepareSuccessView(EditEventsSuccessOutputData outputData);
}
