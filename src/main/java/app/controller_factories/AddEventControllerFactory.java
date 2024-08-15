package app.controller_factories;

import data_access.UsersEventsRelationalAccessInterface;
import use_case.add_event.AddEventInputBoundary;
import use_case.add_event.AddEventInteractor;
import use_case.add_event.AddEventOutputBoundary;
import use_case.add_event.interface_adapter.AddEventController;
import use_case.add_event.interface_adapter.AddEventPresenter;
import view_model.EventScreenViewModel;

/**
 * create controllers for adding event
 */
public class AddEventControllerFactory {

    private AddEventControllerFactory() {}

    /**
     * create instance of controller for add event use case
     * @param eventScreenViewModel view model for event screen
     * @param usersEventsRelationalAccessObject data access for user -> events
     * @return AddEventController
     */
    public static AddEventController createAddEventsController(EventScreenViewModel eventScreenViewModel, UsersEventsRelationalAccessInterface usersEventsRelationalAccessObject) {
        AddEventOutputBoundary addEventPresenter = new AddEventPresenter(eventScreenViewModel);
        AddEventInputBoundary addEventInteractor = new AddEventInteractor(addEventPresenter, usersEventsRelationalAccessObject);

        return new AddEventController(addEventInteractor);
    }
}
