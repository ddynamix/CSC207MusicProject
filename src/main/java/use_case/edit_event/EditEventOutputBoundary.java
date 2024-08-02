package use_case.edit_event;

public interface EditEventOutputBoundary {
    void goToEventEditor(EditEventOutputData outputData);
    void prepareSuccessView(EditEventsSuccessOutputData outputData);
}
