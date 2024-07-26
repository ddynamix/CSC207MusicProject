package app.swing_view_factories;

import data_access.EventDataAccessInterface;
import data_access.UserDataAccessInterface;
import app.interface_adapter_tools.ViewManagerModel;
import use_case.eventscreen.interface_adapter.EventScreenViewModel;
import use_case.homescreen.interface_adapter.HomescreenController;
import use_case.homescreen.interface_adapter.HomescreenPresenter;
import use_case.homescreen.interface_adapter.HomescreenViewModel;
import use_case.homescreen.HomescreenInputBoundary;
import use_case.homescreen.HomescreenInteractor;
import use_case.homescreen.HomescreenOutputBoundary;
import use_case.splash.interface_adapter.SplashViewModel;
import view.jswing_views.Header;
import view.jswing_views.HomescreenView;

/**
 * Homescreen view factory
 */
public class HomescreenViewFactory {

    private HomescreenViewFactory() {}

    /**
     * create homescreen instance
     *
     * @param headerFactory         factory for header
     * @param viewManagerModel      control of view models
     * @param eventScreenViewModel  data for event screen view
     * @param homescreenViewModel   data for this view
     * @param eventDataAccessObject data access object for events
     * @param userDataAccessObject  data access object for users
     * @return HomescreenView       the created view
     */
    public static HomescreenView createHomescreenView(HeaderFactory headerFactory, ViewManagerModel viewManagerModel, EventScreenViewModel eventScreenViewModel, HomescreenViewModel homescreenViewModel, SplashViewModel splashViewModel, EventDataAccessInterface eventDataAccessObject, UserDataAccessInterface userDataAccessObject) {
        HomescreenOutputBoundary homescreenPresenter = new HomescreenPresenter(viewManagerModel,eventScreenViewModel, splashViewModel);
        HomescreenInputBoundary homescreenInteractor = new HomescreenInteractor(homescreenPresenter, userDataAccessObject, eventDataAccessObject);
        HomescreenController homescreenController = new HomescreenController(homescreenInteractor);

        return new HomescreenView(homescreenViewModel, homescreenController, headerFactory.createHeader());
    }
}
