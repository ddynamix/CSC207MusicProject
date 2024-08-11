package app.controller_factories;

import app.interface_adapter_tools.ViewManagerModel;
import data_access.UserDataAccessInterface;
import view_model.HomescreenViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.login.interface_adapter.LoginController;
import use_case.login.interface_adapter.LoginPresenter;
import view_model.SplashViewModel;

public class LoginControllerFactory {

    private LoginControllerFactory() {}

    public static LoginController createLoginController(ViewManagerModel viewManagerModel, SplashViewModel splashViewModel, HomescreenViewModel homescreenViewModel, UserDataAccessInterface userDataAccessObject) {
        LoginOutputBoundary loginPresenter = new LoginPresenter(splashViewModel, homescreenViewModel, viewManagerModel);
        LoginInputBoundary loginInteractor = new LoginInteractor(userDataAccessObject, loginPresenter);

        return new LoginController(loginInteractor);
    }
}
