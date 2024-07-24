package app.swing_view_factories;

import data_access.UserDataAccessInterface;
import app.interface_adapter_tools.ViewManagerModel;
import use_case.login.interface_adapter.LoginViewModel;
import use_case.splash.interface_adapter.SplashViewModel;
import use_case.usersignup.interface_adapter.UserSignupController;
import use_case.usersignup.interface_adapter.UserSignupPresenter;
import use_case.usersignup.interface_adapter.UserSignupViewModel;
import use_case.usersignup.UserSignupInputBoundary;
import use_case.usersignup.UserSignupInteractor;
import use_case.usersignup.SignupOutputBoundary;
import view.jswing_views.UserSignupView;

/**
 * User signup factory
 */
public class UserSignupViewFactory {

    private UserSignupViewFactory() {}

    /**
     * create signup screen instance
     *
     * @param viewManagerModel     controller of view models
     * @param loginViewModel       data for login view
     * @param signupViewModel      data for this view
     * @param userDataAccessObject access to database
     * @return UserSignupView      the created view
     */
    public static UserSignupView createSignupView(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, UserSignupViewModel signupViewModel, SplashViewModel splashViewModel, UserDataAccessInterface userDataAccessObject) {
        SignupOutputBoundary signupPresenter = new UserSignupPresenter(viewManagerModel, signupViewModel, splashViewModel, loginViewModel);
        UserSignupInputBoundary signupInteractor = new UserSignupInteractor(userDataAccessObject, signupPresenter);
        UserSignupController signupController = new UserSignupController(signupInteractor);

        return new UserSignupView(signupViewModel, signupController);
    }
}
