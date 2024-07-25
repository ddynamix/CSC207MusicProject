package use_case.eventcrafter;

public interface EventCrafterInputBoundary {
    void attemptPostEvent(EventCrafterInputData eventCrafterInputData);
    void switchToHomescreen();
}
