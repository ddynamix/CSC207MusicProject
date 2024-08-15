package app.controller_factories;

import app.interface_adapter_tools.ViewManagerModel;
import use_case.eventcrafter.interface_adapter.EventCrafterController;
import view_model.EventScreenViewModel;
import use_case.eventcrafter.EventCrafterInputBoundary;
import use_case.eventcrafter.EventCrafterInteractor;
import use_case.eventcrafter.EventCrafterOutputBoundary;
import data_access.EventDataAccessInterface;
import data_access.UserDataAccessInterface;
import use_case.eventcrafter.interface_adapter.EventCrafterPresenter;

/**
 * Create controllers for craft event use case
 */
public class CraftEventControllerFactory {

    private CraftEventControllerFactory() {}

    /**
     * create an instance of controller for craft event use case
     * @param viewManagerModel manager for changing views
     * @param eventScreenViewModel model for event screen view
     * @param eventDataAccessObject data access for events
     * @param userDataAccessObject data access for users
     * @return controller for event crafter use case
     */
    public static EventCrafterController createCraftEventController(ViewManagerModel viewManagerModel,
                                                                    EventScreenViewModel eventScreenViewModel,
                                                                    EventDataAccessInterface eventDataAccessObject,
                                                                    UserDataAccessInterface userDataAccessObject) {
        EventCrafterOutputBoundary eventCrafterPresenter = new EventCrafterPresenter(eventScreenViewModel, viewManagerModel);
        EventCrafterInputBoundary eventCrafterInteractor = new EventCrafterInteractor(eventDataAccessObject, eventCrafterPresenter);

        return new EventCrafterController(eventCrafterInteractor, userDataAccessObject);
    }
}
