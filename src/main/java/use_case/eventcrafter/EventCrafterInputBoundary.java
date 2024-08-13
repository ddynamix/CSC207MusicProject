package use_case.eventcrafter;

/**
 * interface for input boundary for event use case
 */
public interface EventCrafterInputBoundary {
    void attemptPostEvent(EventCrafterInputData eventCrafterInputData);
}
