package use_case.search_events.interface_adapter;

import use_case.search_events.SearchEventsInputBoundary;

public class SearchEventsController {
    private final SearchEventsInputBoundary searchEventsInteractor;

    public SearchEventsController(SearchEventsInputBoundary searchEventsInteractor) {
        this.searchEventsInteractor = searchEventsInteractor;
    }

    public void searchEvents() {
        searchEventsInteractor.searchForEvents();
    }
}
