package use_case.eventscreen;

public interface EventScreenOutputBoundary {
    void prepareCreateEventView(EventScreenOutputData eventScreenOutputData);
    void updateEvents(EventScreenGetEventOutputData eventScreenGetEventOutputData);
    void prepareHomescreenView();
}
