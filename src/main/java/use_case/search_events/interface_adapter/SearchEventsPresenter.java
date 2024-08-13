package use_case.search_events.interface_adapter;

import use_case.search_events.SearchEventsOutputBoundary;
import use_case.search_events.SearchEventsOutputData;
import view_model.SearchEventsState;
import view_model.SearchEventsViewModel;

/**
 * presenter for event search use case
 */
public class SearchEventsPresenter implements SearchEventsOutputBoundary {
    private final SearchEventsViewModel searchEventsViewModel;

    /**
     * create instance of presenter for event search use case
     * @param searchEventsViewModel model for event seach use case
     */
    public SearchEventsPresenter(SearchEventsViewModel searchEventsViewModel) {
        this.searchEventsViewModel = searchEventsViewModel;
    }

    /**
     * set event data
     * @param outputData new event data
     */
    @Override
    public void updateDisplayedEvents(SearchEventsOutputData outputData) {
        SearchEventsState state = searchEventsViewModel.getState();
        state.setEventsToDisplay(outputData.getAllEvents());
        searchEventsViewModel.setState(state);
        searchEventsViewModel.firePropertyChanged();
    }
}
