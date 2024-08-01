package use_case.search_events.interface_adapter;

import use_case.search_events.SearchEventsOutputBoundary;
import use_case.search_events.SearchEventsOutputData;
import view_model.SearchEventsState;
import view_model.SearchEventsViewModel;

public class SearchEventsPresenter implements SearchEventsOutputBoundary {
    private final SearchEventsViewModel searchEventsViewModel;

    public SearchEventsPresenter(SearchEventsViewModel searchEventsViewModel) {
        this.searchEventsViewModel = searchEventsViewModel;
    }

    @Override
    public void updateDisplayedEvents(SearchEventsOutputData outputData) {
        SearchEventsState state = searchEventsViewModel.getState();
        state.setEventsToDisplay(outputData.getAllEvents());
        searchEventsViewModel.setState(state);
        searchEventsViewModel.firePropertyChanged();
    }
}
