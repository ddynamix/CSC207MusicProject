package use_case.search_events;

/**
 * interface for output data for event search use case
 */
public interface SearchEventsOutputBoundary {
    void updateDisplayedEvents(SearchEventsOutputData outputData);
}
