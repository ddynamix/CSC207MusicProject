package use_case.search_events;

import data_access.EventDataAccessInterface;

/**
 * interactor for event search use case
 */
public class SearchEventsInteractor implements SearchEventsInputBoundary {
    private final SearchEventsOutputBoundary searchEventsPresenter;
    private final EventDataAccessInterface eventDataAccess;

    /**
     * create instance of interactor for event search use case
     * @param searchEventsPresenter presenter for event search use case
     * @param eventDataAccess event DAO
     */
    public SearchEventsInteractor(SearchEventsOutputBoundary searchEventsPresenter, EventDataAccessInterface eventDataAccess) {
        this.searchEventsPresenter = searchEventsPresenter;
        this.eventDataAccess = eventDataAccess;
    }

    /**
     * create instance of ouput data and update presenter
     */
    @Override
    public void searchForEvents() {
        SearchEventsOutputData outputData = new SearchEventsOutputData(eventDataAccess.getEvents());
        searchEventsPresenter.updateDisplayedEvents(outputData);
    }
}
