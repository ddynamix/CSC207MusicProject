package use_case.homescreen;

public interface HomescreenOutputBoundary {
    void prepareCreateEventView(HomescreenOutputData homescreenOutputData);

    void updateEvents(HomescreenGetEventOutputData homescreenOutputData);
}
