package use_case.eventcrafter;

public interface EventCrafterOutputBoundary {
    void prepareSuccessView(EventCrafterOutputData eventCrafterOutputData);

    void prepareFailView(String error);

    void switchToHomescreen();
}
