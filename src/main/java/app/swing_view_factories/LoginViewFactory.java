package app.swing_view_factories;

import data_access.UserDataAccessInterface;
import interface_adapter.ViewManagerModel;
import use_case.homescreen.interface_adapter.HomescreenViewModel;
import use_case.login.interface_adapter.LoginController;
import use_case.login.interface_adapter.LoginPresenter;
import use_case.login.interface_adapter.LoginViewModel;
import interface_adapter.splash.SplashViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import view.jswing_views.LoginView;

public class LoginViewFactory {

    private LoginViewFactory() {
    }

    public static LoginView createLoginView(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SplashViewModel splashViewModel, HomescreenViewModel homescreenViewModel, UserDataAccessInterface userDataAccessObject) {
        LoginPresenter loginPresenter = new LoginPresenter(splashViewModel, homescreenViewModel, viewManagerModel);

        LoginInputBoundary loginInteractor = new LoginInteractor(userDataAccessObject, loginPresenter);

        LoginController loginController = new LoginController(loginInteractor);
        return new LoginView(loginController, loginPresenter, loginViewModel);
    }
}
