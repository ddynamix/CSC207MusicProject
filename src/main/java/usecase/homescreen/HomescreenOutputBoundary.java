package usecase.homescreen;

public interface HomescreenOutputBoundary {
    void prepareCreateEventView(HomescreenOutputData homescreenOutputData);

    void updateEvents(HomescreenGetEventOutputData homescreenOutputData);
}
