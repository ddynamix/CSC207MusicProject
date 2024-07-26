package app.swing_view_factories;

import data_access.EventDataAccessInterface;
import data_access.UserDataAccessInterface;
import app.interface_adapter_tools.ViewManagerModel;
import use_case.eventcrafter.interface_adapter.EventCrafterController;
import use_case.eventcrafter.interface_adapter.EventCrafterPresenter;
import use_case.eventcrafter.interface_adapter.EventCrafterViewModel;
import use_case.eventscreen.interface_adapter.EventScreenViewModel;
import use_case.homescreen.interface_adapter.HomescreenViewModel;
import use_case.eventcrafter.EventCrafterInputBoundary;
import use_case.eventcrafter.EventCrafterInteractor;
import use_case.eventcrafter.EventCrafterOutputBoundary;
import view.jswing_views.EventCrafterView;
import view.jswing_views.Header;

/**
 * Event crafter factory
 */
public class EventCrafterViewFactory {

    private EventCrafterViewFactory() {}

    /**
     * create event crafter screen instance
     *
     * @param headerFactory         factory for header
     * @param viewManagerModel      control of view models
     * @param eventScreenViewModel  data for event screen view
     * @param eventCrafterViewModel data for this view
     * @param eventDataAccessObject data access object for events
     * @param userDataAccessObject  data access object for users
     * @return EventCrafterView     the created view
     */
    public static EventCrafterView createEventCrafterView(HeaderFactory headerFactory, ViewManagerModel viewManagerModel, EventScreenViewModel eventScreenViewModel, EventCrafterViewModel eventCrafterViewModel, EventDataAccessInterface eventDataAccessObject, UserDataAccessInterface userDataAccessObject) {
        EventCrafterOutputBoundary eventCrafterPresenter = new EventCrafterPresenter(eventScreenViewModel, viewManagerModel);
        EventCrafterInputBoundary eventCrafterInteractor = new EventCrafterInteractor(eventDataAccessObject, eventCrafterPresenter);
        EventCrafterController eventCrafterController = new EventCrafterController(eventCrafterInteractor, userDataAccessObject);

        return new EventCrafterView(eventCrafterViewModel, eventCrafterController, headerFactory.createHeader());
    }
}
