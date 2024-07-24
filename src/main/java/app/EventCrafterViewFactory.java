package app;

import dataaccess.EventDataAccessInterface;
import dataaccess.EventLocalCSVDataStorage;
import dataaccess.UserDataAccessInterface;
import entity.user.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.eventcrafter.EventCrafterController;
import interface_adapter.eventcrafter.EventCrafterPresenter;
import interface_adapter.eventcrafter.EventCrafterViewModel;
import interface_adapter.homescreen.HomescreenViewModel;
import usecase.eventcrafter.EventCrafterInputBoundary;
import usecase.eventcrafter.EventCrafterInteractor;
import usecase.eventcrafter.EventCrafterOutputBoundary;
import view.EventCrafterView;

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
