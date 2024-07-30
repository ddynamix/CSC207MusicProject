package app.controller_factories;

import app.interface_adapter_tools.ViewManagerModel;
import data_access.UserDataAccessInterface;
import view_model.EventCrafterViewModel;
import view_model.EventScreenViewModel;
import view_model.HomescreenViewModel;
import view_model.LoginViewModel;
import use_case.screen_switcher.ScreenSwitcherInputBoundary;
import use_case.screen_switcher.ScreenSwitcherInteractor;
import use_case.screen_switcher.ScreenSwitcherOutputBoundary;
import use_case.screen_switcher.interface_adapter.ScreenSwitcherController;
import use_case.screen_switcher.interface_adapter.ScreenSwitcherPresenter;
import view_model.SearchUsersViewModel;
import view_model.SplashViewModel;
import view_model.UserSignupViewModel;

public class ScreenSwitcherControllerFactory {

    private ScreenSwitcherControllerFactory() {}

    public static ScreenSwitcherController createScreenSwitcherController(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SplashViewModel splashViewModel, UserSignupViewModel userSignupViewModel, HomescreenViewModel homescreenViewModel, EventScreenViewModel eventScreenViewModel, EventCrafterViewModel eventCrafterViewModel, SearchUsersViewModel searchUsersViewModel, UserDataAccessInterface userDataAccessObject) {
        ScreenSwitcherOutputBoundary screenSwitcherPresenter = new ScreenSwitcherPresenter(viewManagerModel, loginViewModel, splashViewModel, userSignupViewModel, homescreenViewModel, eventScreenViewModel, eventCrafterViewModel, searchUsersViewModel);
        ScreenSwitcherInputBoundary screenSwitcherInteractor = new ScreenSwitcherInteractor(screenSwitcherPresenter, userDataAccessObject);

        return new ScreenSwitcherController(screenSwitcherInteractor);
    }
}
