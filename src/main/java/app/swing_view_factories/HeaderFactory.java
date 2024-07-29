package app.swing_view_factories;

import app.interface_adapter_tools.ViewManagerModel;
import data_access.EventDataAccessInterface;
import data_access.UserDataAccessInterface;
import use_case.eventcrafter.interface_adapter.EventCrafterViewModel;
import use_case.eventscreen.interface_adapter.EventScreenViewModel;
import use_case.homescreen.interface_adapter.HomescreenViewModel;
import use_case.login.interface_adapter.LoginViewModel;
import use_case.screen_switcher.ScreenSwitcherInputBoundary;
import use_case.screen_switcher.ScreenSwitcherInteractor;
import use_case.screen_switcher.ScreenSwitcherOutputBoundary;
import use_case.screen_switcher.interface_adapter.ScreenSwitcherController;
import use_case.screen_switcher.interface_adapter.ScreenSwitcherPresenter;
import use_case.search_users.interface_adapter.SearchUsersViewModel;
import use_case.splash.interface_adapter.SplashViewModel;
import use_case.usersignup.interface_adapter.UserSignupViewModel;
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

    private final UserDataAccessInterface userDataAccessObject;
    private final EventDataAccessInterface eventDataAccessObject;

    public HeaderFactory(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SplashViewModel splashViewModel, UserSignupViewModel signupViewModel, HomescreenViewModel homescreenViewModel, EventScreenViewModel eventScreenViewModel, EventCrafterViewModel eventCrafterViewModel, SearchUsersViewModel searchUsersViewModel, UserDataAccessInterface userDataAccessObject, EventDataAccessInterface eventDataAccessObject) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.splashViewModel = splashViewModel;
        this.signupViewModel = signupViewModel;
        this.homescreenViewModel = homescreenViewModel;
        this.eventScreenViewModel = eventScreenViewModel;
        this.eventCrafterViewModel = eventCrafterViewModel;
        this.userDataAccessObject = userDataAccessObject;
        this.eventDataAccessObject = eventDataAccessObject;
        this.searchUsersViewModel = searchUsersViewModel;
    }

    public Header createHeader() {
        ScreenSwitcherOutputBoundary screenSwitcherPresenter = new ScreenSwitcherPresenter(viewManagerModel, loginViewModel, splashViewModel, signupViewModel, homescreenViewModel, eventScreenViewModel, eventCrafterViewModel, searchUsersViewModel);
        ScreenSwitcherInputBoundary screenSwitcherInteractor = new ScreenSwitcherInteractor(screenSwitcherPresenter, userDataAccessObject);
        ScreenSwitcherController screenSwitcherController = new ScreenSwitcherController(screenSwitcherInteractor);

        return new Header(screenSwitcherController);
    }
}
