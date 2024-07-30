package app.controller_factories;

import app.interface_adapter_tools.ViewManagerModel;
import data_access.UserDataAccessInterface;
import view_model.EventCrafterViewModel;
import use_case.eventscreen.EventScreenInputBoundary;
import use_case.eventscreen.EventScreenInteractor;
import use_case.eventscreen.EventScreenOutputBoundary;
import use_case.eventscreen.interface_adapter.EventScreenController;
import use_case.eventscreen.interface_adapter.EventScreenPresenter;
import view_model.EventScreenViewModel;
import view_model.HomescreenViewModel;

public class CreateEventClickedControllerFactory {

    private CreateEventClickedControllerFactory() {}

    public static EventScreenController createCreateEventClickedController(ViewManagerModel viewManagerModel, EventScreenViewModel eventScreenViewModel, EventCrafterViewModel eventCrafterViewModel, HomescreenViewModel homescreenViewModel, UserDataAccessInterface userDataAccessObject) {
        EventScreenOutputBoundary eventScreenPresenter = new EventScreenPresenter(viewManagerModel, eventCrafterViewModel, homescreenViewModel, eventScreenViewModel);
        EventScreenInputBoundary eventScreenInteractor = new EventScreenInteractor(eventScreenPresenter, userDataAccessObject);

        return new EventScreenController(eventScreenInteractor);
    }
}
