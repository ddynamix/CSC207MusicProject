package app;

import dataaccess.EventDataAccessInterface;
import dataaccess.UserDataAccessInterface;
import interface_adapter.ViewManagerModel;
import interface_adapter.eventcrafter.EventCrafterPresenter;
import interface_adapter.eventcrafter.EventCrafterViewModel;
import interface_adapter.homescreen.HomescreenController;
import interface_adapter.homescreen.HomescreenPresenter;
import interface_adapter.homescreen.HomescreenViewModel;
import usecase.eventcrafter.EventCrafterInputBoundary;
import usecase.eventcrafter.EventCrafterInteractor;
import usecase.eventcrafter.EventCrafterOutputBoundary;
import usecase.homescreen.HomescreenInputBoundary;
import usecase.homescreen.HomescreenInteractor;
import usecase.homescreen.HomescreenOutputBoundary;
import view.HomescreenView;

public class HomescreenViewFactory {
    private HomescreenViewFactory() {
    }

    public static HomescreenView createHomescreenView(ViewManagerModel viewManagerModel, EventCrafterViewModel eventCrafterViewModel, HomescreenViewModel homescreenViewModel, UserDataAccessInterface userDataAccessObject, EventDataAccessInterface eventDataAccessObject) {
        HomescreenOutputBoundary homescreenPresenter = new HomescreenPresenter(viewManagerModel, eventCrafterViewModel, homescreenViewModel);
        HomescreenInputBoundary homescreenInteractor = new HomescreenInteractor(homescreenPresenter, userDataAccessObject, eventDataAccessObject);
        HomescreenController homescreenController = new HomescreenController(homescreenInteractor);

        return new HomescreenView(homescreenViewModel, homescreenController);
    }
}
