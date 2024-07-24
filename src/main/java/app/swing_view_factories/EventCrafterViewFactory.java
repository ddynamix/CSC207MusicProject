package app.swing_view_factories;

import data_access.EventDataAccessInterface;
import data_access.UserDataAccessInterface;
import interface_adapter.ViewManagerModel;
import use_case.eventcrafter.interface_adapter.EventCrafterController;
import use_case.eventcrafter.interface_adapter.EventCrafterPresenter;
import use_case.eventcrafter.interface_adapter.EventCrafterViewModel;
import use_case.homescreen.interface_adapter.HomescreenViewModel;
import use_case.eventcrafter.EventCrafterInputBoundary;
import use_case.eventcrafter.EventCrafterInteractor;
import use_case.eventcrafter.EventCrafterOutputBoundary;
import view.jswing_views.EventCrafterView;

public class EventCrafterViewFactory {
    private EventCrafterViewFactory() {
    }

    public static EventCrafterView createEventCrafterView(ViewManagerModel viewManagerModel, HomescreenViewModel homescreenViewModel, EventCrafterViewModel eventCrafterViewModel, EventDataAccessInterface eventDataAccessObject, UserDataAccessInterface userDataAccessObject) {
        EventCrafterOutputBoundary eventCrafterPresenter = new EventCrafterPresenter(homescreenViewModel, viewManagerModel);
        EventCrafterInputBoundary eventCrafterInteractor = new EventCrafterInteractor(eventDataAccessObject, eventCrafterPresenter);
        EventCrafterController eventCrafterController = new EventCrafterController(eventCrafterInteractor, userDataAccessObject);

        return new EventCrafterView(eventCrafterViewModel, eventCrafterController);
    }
}
