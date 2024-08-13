package use_case.search_events.interface_adapter;

import use_case.search_events.SearchEventsInputBoundary;

/**
 * controller for event search use case
 */
public class SearchEventsController {
    private final SearchEventsInputBoundary searchEventsInteractor;

    /**
     * create instance of controller for event search use case
     * @param searchEventsInteractor interactor for event search use case
     */
    public SearchEventsController(SearchEventsInputBoundary searchEventsInteractor) {
        this.searchEventsInteractor = searchEventsInteractor;
    }

    /**
     * create classes for event search use case
     */
    public void searchEvents() {
        searchEventsInteractor.searchForEvents();
    }
}
