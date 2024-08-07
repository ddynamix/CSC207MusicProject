package app;

import app.interface_adapter_tools.ViewManagerModel;
import data_access.EventDataAccessInterface;
import data_access.UserDataAccessInterface;
import use_case.sign_out.interface_adapter.SignOutController;
import view_model.EventCrafterViewModel;
import view_model.EventScreenViewModel;
import view_model.HomescreenViewModel;
import view_model.LoginViewModel;
import use_case.screen_switcher.interface_adapter.ScreenSwitcherController;
import view_model.SearchUsersViewModel;
import view_model.SplashViewModel;
import view_model.UserSignupViewModel;
import view.jswing_views.Header;

public class HeaderFactory {
    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;
    private final SplashViewModel splashViewModel;
    private final UserSignupViewModel signupViewModel;
    private final HomescreenViewModel homescreenViewModel;
    private final EventScreenViewModel eventScreenViewModel;
    private final EventCrafterViewModel eventCrafterViewModel;
    private final SearchUsersViewModel searchUsersViewModel;

    private final ScreenSwitcherController screenSwitcherController;
    private final SignOutController signOutController;

    private final UserDataAccessInterface userDataAccessObject;
    private final EventDataAccessInterface eventDataAccessObject;

    public HeaderFactory(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SplashViewModel splashViewModel, UserSignupViewModel signupViewModel, HomescreenViewModel homescreenViewModel, EventScreenViewModel eventScreenViewModel, EventCrafterViewModel eventCrafterViewModel, SearchUsersViewModel searchUsersViewModel, ScreenSwitcherController screenSwitcherController, SignOutController signOutController, UserDataAccessInterface userDataAccessObject, EventDataAccessInterface eventDataAccessObject) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.splashViewModel = splashViewModel;
        this.signupViewModel = signupViewModel;
        this.homescreenViewModel = homescreenViewModel;
        this.eventScreenViewModel = eventScreenViewModel;
        this.eventCrafterViewModel = eventCrafterViewModel;
        this.searchUsersViewModel = searchUsersViewModel;

        this.screenSwitcherController = screenSwitcherController;
        this.signOutController = signOutController;

        this.userDataAccessObject = userDataAccessObject;
        this.eventDataAccessObject = eventDataAccessObject;

    }

    public Header createHeader() {

        return new Header(screenSwitcherController, signOutController);
    }
}
