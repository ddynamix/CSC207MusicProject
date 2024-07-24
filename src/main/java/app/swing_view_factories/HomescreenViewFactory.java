package app.swing_view_factories;

import data_access.EventDataAccessInterface;
import data_access.UserDataAccessInterface;
import interface_adapter.ViewManagerModel;
import use_case.eventcrafter.interface_adapter.EventCrafterViewModel;
import use_case.homescreen.interface_adapter.HomescreenController;
import use_case.homescreen.interface_adapter.HomescreenPresenter;
import use_case.homescreen.interface_adapter.HomescreenViewModel;
import use_case.homescreen.HomescreenInputBoundary;
import use_case.homescreen.HomescreenInteractor;
import use_case.homescreen.HomescreenOutputBoundary;
import view.jswing_views.HomescreenView;

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
