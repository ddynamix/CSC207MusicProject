package app.swing_view_factories;

import data_access.UserDataAccessInterface;
import app.interface_adapter_tools.ViewManagerModel;
import use_case.homescreen.interface_adapter.HomescreenViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.interface_adapter.LoginController;
import use_case.login.interface_adapter.LoginPresenter;
import use_case.login.interface_adapter.LoginViewModel;
import use_case.splash.interface_adapter.SplashViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import view.jswing_views.LoginView;

/**
 * Login screen factory
 */
public class LoginViewFactory {

    private LoginViewFactory() {
    }

    /**
     * create login screen instance
     *
     * @param viewManagerModel      control of view models
     * @param loginViewModel        data for this view
     * @param splashViewModel       data for splash view
     * @param homescreenViewModel   data for homescreen view
     * @param userDataAccessObject  data access object for users
     * @return LoginView           the created view
     */
    public static LoginView createLoginView(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SplashViewModel splashViewModel, HomescreenViewModel homescreenViewModel, UserDataAccessInterface userDataAccessObject) {
        LoginOutputBoundary loginPresenter = new LoginPresenter(splashViewModel, homescreenViewModel, viewManagerModel);
        LoginInputBoundary loginInteractor = new LoginInteractor(userDataAccessObject, loginPresenter);
        LoginController loginController = new LoginController(loginInteractor);

        return new LoginView(loginViewModel, loginController);
    }
}
