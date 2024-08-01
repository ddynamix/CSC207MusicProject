package app.controller_factories;

import data_access.UsersEventsRelationalAccessInterface;
import use_case.add_event.AddEventInputBoundary;
import use_case.add_event.AddEventInteractor;
import use_case.add_event.interface_adapter.AddEventController;

public class AddEventControllerFactory {

    private AddEventControllerFactory() {}

    public static AddEventController createAddEventsController(UsersEventsRelationalAccessInterface usersEventsRelationalAccessObject) {
        AddEventInputBoundary addEventInteractor = new AddEventInteractor(usersEventsRelationalAccessObject);

        return new AddEventController(addEventInteractor);
    }
}
