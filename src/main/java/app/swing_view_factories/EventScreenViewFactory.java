package app.swing_view_factories;

import app.interface_adapter_tools.ViewManagerModel;
import data_access.UserDataAccessInterface;
import use_case.eventcrafter.interface_adapter.EventCrafterViewModel;
import use_case.eventscreen.EventScreenInputBoundary;
import use_case.eventscreen.EventScreenInteractor;
import use_case.eventscreen.EventScreenOutputBoundary;
import use_case.eventscreen.interface_adapter.EventScreenController;
import use_case.eventscreen.interface_adapter.EventScreenPresenter;
import use_case.eventscreen.interface_adapter.EventScreenViewModel;
import use_case.homescreen.interface_adapter.HomescreenViewModel;
import view.jswing_views.EventScreenView;
import view.jswing_views.Header;

/**
 * Event screen view factory
 */
public class EventScreenViewFactory {

    private EventScreenViewFactory() {}

    /**
     * craete event screen view instance
     *
     * @param headerFactory             factory for header
     * @param viewManagerModel          control of view models
     * @param eventCrafterViewModel     data for event crafter view
     * @param homescreenViewModel       data for home screen view
     * @param eventScreenViewModel      data for this view
     * @param userDataAccessObject      data access object for users
     * @return EventScreenView          the returned view object
     */
    public static EventScreenView createEventScreenView(HeaderFactory headerFactory, ViewManagerModel viewManagerModel, EventCrafterViewModel eventCrafterViewModel, HomescreenViewModel homescreenViewModel, EventScreenViewModel eventScreenViewModel, UserDataAccessInterface userDataAccessObject) {
        EventScreenOutputBoundary eventScreenPresenter = new EventScreenPresenter(viewManagerModel, eventCrafterViewModel, homescreenViewModel, eventScreenViewModel);
        EventScreenInputBoundary eventScreenInteractor = new EventScreenInteractor(eventScreenPresenter, userDataAccessObject);
        EventScreenController eventScreenController = new EventScreenController(eventScreenInteractor);

        return new EventScreenView(eventScreenViewModel, eventScreenController, headerFactory.createHeader());
    }
}
