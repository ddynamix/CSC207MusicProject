package use_case.search_events;

import data_access.EventDataAccessInterface;

public class SearchEventsInteractor implements SearchEventsInputBoundary {
    private final SearchEventsOutputBoundary searchEventsPresenter;
    private final EventDataAccessInterface eventDataAccess;

    public SearchEventsInteractor(SearchEventsOutputBoundary searchEventsPresenter, EventDataAccessInterface eventDataAccess) {
        this.searchEventsPresenter = searchEventsPresenter;
        this.eventDataAccess = eventDataAccess;
    }

    @Override
    public void searchForEvents() {
        SearchEventsOutputData outputData = new SearchEventsOutputData(eventDataAccess.getEvents());
        searchEventsPresenter.updateDisplayedEvents(outputData);
    }
}
