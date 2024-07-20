package app;

import dataaccess.UserDataAccessInterface;
import interface_adapter.ViewManagerModel;
import interface_adapter.homescreen.HomescreenViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.splash.SplashViewModel;
import usecase.login.LoginInputBoundary;
import usecase.login.LoginInteractor;
import view.LoginView;

public class LoginViewFactory {

    private LoginViewFactory() {
    }

    public static LoginView createLoginView(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SplashViewModel splashViewModel, HomescreenViewModel homescreenViewModel, UserDataAccessInterface userDataAccessObject) {
        LoginPresenter loginPresenter = new LoginPresenter(splashViewModel, homescreenViewModel, viewManagerModel);

        LoginInputBoundary loginInteractor = new LoginInteractor(userDataAccessObject, loginPresenter);

        LoginController loginController = new LoginController(userDataAccessObject, loginInteractor);
        return new LoginView(loginController, loginPresenter, loginViewModel);
    }
}
