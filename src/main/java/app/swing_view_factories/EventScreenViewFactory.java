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

/**
 * Event screen view factory
 */
public class EventScreenViewFactory {

    private EventScreenViewFactory() {}

    public static EventScreenView createEventScreenView(ViewManagerModel viewManagerModel, EventCrafterViewModel eventCrafterViewModel, HomescreenViewModel homescreenViewModel, EventScreenViewModel eventScreenViewModel, UserDataAccessInterface userDataAccessObject) {
        EventScreenOutputBoundary eventScreenPresenter = new EventScreenPresenter(viewManagerModel, eventCrafterViewModel, homescreenViewModel, eventScreenViewModel);
        EventScreenInputBoundary eventScreenInteractor = new EventScreenInteractor(eventScreenPresenter, userDataAccessObject);
        EventScreenController eventScreenController = new EventScreenController(eventScreenInteractor);

        return new EventScreenView(eventScreenViewModel, eventScreenController);
    }
}
