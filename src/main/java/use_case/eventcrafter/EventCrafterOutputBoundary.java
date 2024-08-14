package use_case.eventcrafter;

/**
 * interface for output data for event use case
 */
public interface EventCrafterOutputBoundary {
    void prepareSuccessView(EventCrafterOutputData eventCrafterOutputData);

    void prepareFailView(String error);
}
