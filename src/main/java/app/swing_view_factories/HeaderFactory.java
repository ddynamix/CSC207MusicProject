package app.swing_view_factories;

import app.interface_adapter_tools.ViewManagerModel;
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
import use_case.splash.interface_adapter.SplashViewModel;
import use_case.usersignup.interface_adapter.UserSignupViewModel;
import view.jswing_views.Header;

public class HeaderFactory {
    private HeaderFactory() {

    }

    public static Header createHeader(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SplashViewModel splashViewModel, UserSignupViewModel signupViewModel, HomescreenViewModel homescreenViewModel, EventScreenViewModel eventScreenViewModel, EventCrafterViewModel eventCrafterViewModel, UserDataAccessInterface userDataAccessObject) {
        ScreenSwitcherOutputBoundary screenSwitcherPresenter = new ScreenSwitcherPresenter(viewManagerModel, loginViewModel, splashViewModel, signupViewModel, homescreenViewModel, eventScreenViewModel, eventCrafterViewModel);
        ScreenSwitcherInputBoundary screenSwitcherInteractor = new ScreenSwitcherInteractor(screenSwitcherPresenter, userDataAccessObject);
        ScreenSwitcherController screenSwitcherController = new ScreenSwitcherController(screenSwitcherInteractor);

        return new Header(screenSwitcherController);
    }
}
