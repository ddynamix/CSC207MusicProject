package app.controller_factories;

import app.interface_adapter_tools.ViewManagerModel;
import data_access.EventDataAccessInterface;
import data_access.PostDataAccessInterface;
import data_access.UserDataAccessInterface;
import use_case.screen_switcher.ScreenSwitcherInputBoundary;
import use_case.screen_switcher.ScreenSwitcherInteractor;
import use_case.screen_switcher.ScreenSwitcherOutputBoundary;
import use_case.screen_switcher.interface_adapter.ScreenSwitcherController;
import use_case.screen_switcher.interface_adapter.ScreenSwitcherPresenter;
import view_model.*;


/**
 * Create screen switcher controllers
 */
public class ScreenSwitcherControllerFactory {

    private ScreenSwitcherControllerFactory() {}

    /**
     * Create a screen switcher controller
     * @param viewManagerModel model to change view model
     * @param loginViewModel model for login use case
     * @param splashViewModel model for splash view
     * @param userSignupViewModel model for signup use case
     * @param homescreenViewModel model for homescreen view
     * @param eventScreenViewModel model for event screen view
     * @param searchEventsViewModel model for event search use case
     * @param eventCrafterViewModel model for event use case
     * @param searchUsersViewModel model for user search use case
     * @param myFollowersViewModel model for followers view
     * @param isFollowingViewModel model for following view
     * @param myPostMakerViewModel model for post use case
     * @param userDataAccessObject DAO to access users
     * @param eventDataAccessObject DAO to access events
     * @param postDataAccessObject DAO to access posts
     * @return new instance of ScreenSwitcherController
     */
    public static ScreenSwitcherController createScreenSwitcherController(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel,
                                                                          SplashViewModel splashViewModel, UserSignupViewModel userSignupViewModel,
                                                                          HomescreenViewModel homescreenViewModel, EventScreenViewModel eventScreenViewModel,
                                                                          SearchEventsViewModel searchEventsViewModel,
                                                                          EventCrafterViewModel eventCrafterViewModel, SearchUsersViewModel searchUsersViewModel,
                                                                          MyFollowersViewModel myFollowersViewModel, IsFollowingViewModel isFollowingViewModel,
                                                                          PostMakerViewModel myPostMakerViewModel, ProfileViewModel profileViewModel,
                                                                          UserDataAccessInterface userDataAccessObject, EventDataAccessInterface eventDataAccessObject,
                                                                          PostDataAccessInterface postDataAccessObject) {
        ScreenSwitcherOutputBoundary screenSwitcherPresenter = new ScreenSwitcherPresenter(viewManagerModel, loginViewModel, splashViewModel, userSignupViewModel,
                homescreenViewModel, eventScreenViewModel, searchEventsViewModel, eventCrafterViewModel, searchUsersViewModel, myFollowersViewModel,
                isFollowingViewModel, myPostMakerViewModel, profileViewModel);
        ScreenSwitcherInputBoundary screenSwitcherInteractor = new ScreenSwitcherInteractor(screenSwitcherPresenter, userDataAccessObject, eventDataAccessObject, postDataAccessObject);

        return new ScreenSwitcherController(screenSwitcherInteractor);
    }
}
