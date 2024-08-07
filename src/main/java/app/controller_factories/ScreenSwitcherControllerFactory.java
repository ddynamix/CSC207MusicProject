package app.controller_factories;

import app.interface_adapter_tools.ViewManagerModel;
import data_access.UserDataAccessInterface;
import view_model.*;
import use_case.screen_switcher.ScreenSwitcherInputBoundary;
import use_case.screen_switcher.ScreenSwitcherInteractor;
import use_case.screen_switcher.ScreenSwitcherOutputBoundary;
import use_case.screen_switcher.interface_adapter.ScreenSwitcherController;
import use_case.screen_switcher.interface_adapter.ScreenSwitcherPresenter;

public class ScreenSwitcherControllerFactory {

    private ScreenSwitcherControllerFactory() {}

    public static ScreenSwitcherController createScreenSwitcherController(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SplashViewModel splashViewModel, UserSignupViewModel userSignupViewModel, HomescreenViewModel homescreenViewModel, EventScreenViewModel eventScreenViewModel, EventCrafterViewModel eventCrafterViewModel, SearchUsersViewModel searchUsersViewModel, MyFollowersViewModel myFollowersViewModel, IsFollowingViewModel isFollowingViewModel, UserDataAccessInterface userDataAccessObject) {
        ScreenSwitcherOutputBoundary screenSwitcherPresenter = new ScreenSwitcherPresenter(viewManagerModel, loginViewModel, splashViewModel, userSignupViewModel, homescreenViewModel, eventScreenViewModel, eventCrafterViewModel, searchUsersViewModel, myFollowersViewModel, isFollowingViewModel);
        ScreenSwitcherInputBoundary screenSwitcherInteractor = new ScreenSwitcherInteractor(screenSwitcherPresenter, userDataAccessObject);

        return new ScreenSwitcherController(screenSwitcherInteractor);
    }
}
