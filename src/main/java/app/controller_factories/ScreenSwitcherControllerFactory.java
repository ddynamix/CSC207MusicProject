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


public class ScreenSwitcherControllerFactory {

    private ScreenSwitcherControllerFactory() {}

    public static ScreenSwitcherController createScreenSwitcherController(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel,
                                                                          SplashViewModel splashViewModel, UserSignupViewModel userSignupViewModel,
                                                                          HomescreenViewModel homescreenViewModel, EventScreenViewModel eventScreenViewModel,
                                                                          SearchEventsViewModel searchEventsViewModel,
                                                                          EventCrafterViewModel eventCrafterViewModel, SearchUsersViewModel searchUsersViewModel,
                                                                          MyFollowersViewModel myFollowersViewModel, IsFollowingViewModel isFollowingViewModel,
                                                                          PostMakerViewModel myPostMakerViewModel,
                                                                          UserDataAccessInterface userDataAccessObject, EventDataAccessInterface eventDataAccessObject,
                                                                          PostDataAccessInterface postDataAccessObject) {
        ScreenSwitcherOutputBoundary screenSwitcherPresenter = new ScreenSwitcherPresenter(viewManagerModel, loginViewModel, splashViewModel, userSignupViewModel,
                homescreenViewModel, eventScreenViewModel, searchEventsViewModel, eventCrafterViewModel, searchUsersViewModel, myFollowersViewModel,
                isFollowingViewModel, myPostMakerViewModel);
        ScreenSwitcherInputBoundary screenSwitcherInteractor = new ScreenSwitcherInteractor(screenSwitcherPresenter, userDataAccessObject, eventDataAccessObject, postDataAccessObject);

        return new ScreenSwitcherController(screenSwitcherInteractor);
    }
}
