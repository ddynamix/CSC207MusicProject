package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.splash.SplashViewModel;
import view.LoginView;

public class LoginViewFactory {

    private LoginViewFactory() {}

    public static LoginView createLoginView(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SplashViewModel splashViewModel) {
        LoginPresenter loginPresenter = new LoginPresenter(splashViewModel, viewManagerModel);
        LoginController loginController = new LoginController();
        return new LoginView(loginController, loginPresenter, loginViewModel);
    }
}
