package app.controller_factories;

import app.interface_adapter_tools.ViewManagerModel;
import data_access.UserDataAccessInterface;
import view_model.LoginViewModel;
import view_model.SplashViewModel;
import use_case.usersignup.SignupOutputBoundary;
import use_case.usersignup.UserSignupInputBoundary;
import use_case.usersignup.interface_adapter.UserSignupController;
import use_case.usersignup.UserSignupInteractor;
import use_case.usersignup.interface_adapter.UserSignupPresenter;
import view_model.UserSignupViewModel;

public class SignUpControllerFactory {
    private SignUpControllerFactory() {}

    public static UserSignupController createSignUpController(ViewManagerModel viewManagerModel, UserSignupViewModel signupViewModel, SplashViewModel splashViewModel, LoginViewModel loginViewModel, UserDataAccessInterface userdataAccessObject) {
        SignupOutputBoundary userSignupPresenter = new UserSignupPresenter(viewManagerModel, signupViewModel, splashViewModel, loginViewModel);
        UserSignupInputBoundary userSignupInteractor = new UserSignupInteractor(userdataAccessObject, userSignupPresenter);

        return new UserSignupController(userSignupInteractor);
    }
}
