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

/**
 * Create Sign Up Controllers
 */
public class SignUpControllerFactory {
    private SignUpControllerFactory() {}

    /**
     * Create instance of sign up controller
     * @param viewManagerModel used for switching view models
     * @param signupViewModel model for signup use case
     * @param splashViewModel model for splash view
     * @param loginViewModel model for login use case
     * @param userdataAccessObject DAO to access user data
     * @return new instance of UserSignupController
     */
    public static UserSignupController createSignUpController(ViewManagerModel viewManagerModel, UserSignupViewModel signupViewModel, SplashViewModel splashViewModel, LoginViewModel loginViewModel, UserDataAccessInterface userdataAccessObject) {
        SignupOutputBoundary userSignupPresenter = new UserSignupPresenter(viewManagerModel, signupViewModel, splashViewModel, loginViewModel);
        UserSignupInputBoundary userSignupInteractor = new UserSignupInteractor(userdataAccessObject, userSignupPresenter);

        return new UserSignupController(userSignupInteractor);
    }
}
