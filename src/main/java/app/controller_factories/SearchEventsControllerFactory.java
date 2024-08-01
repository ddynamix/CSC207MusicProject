package app.controller_factories;

import data_access.EventDataAccessInterface;
import use_case.search_events.SearchEventsInputBoundary;
import use_case.search_events.SearchEventsInteractor;
import use_case.search_events.SearchEventsOutputBoundary;
import use_case.search_events.interface_adapter.SearchEventsController;
import use_case.search_events.interface_adapter.SearchEventsPresenter;
import view_model.SearchEventsViewModel;

public class SearchEventsControllerFactory {

    private SearchEventsControllerFactory() {}

    public static SearchEventsController createSearchEventsController(SearchEventsViewModel searchEventsViewModel, EventDataAccessInterface eventDataAccessObject) {
        SearchEventsOutputBoundary searchEventsPresenter = new SearchEventsPresenter(searchEventsViewModel);
        SearchEventsInputBoundary searchEventsInteractor = new SearchEventsInteractor(searchEventsPresenter, eventDataAccessObject);

        return new SearchEventsController(searchEventsInteractor);
    }
}
