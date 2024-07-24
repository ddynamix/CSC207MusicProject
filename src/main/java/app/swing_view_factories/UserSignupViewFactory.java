package app.swing_view_factories;

import data_access.UserDataAccessInterface;
import interface_adapter.ViewManagerModel;
import use_case.login.interface_adapter.LoginViewModel;
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

    /**
     * create view instance
     * @param viewManagerModel  controller of view models
     * @param loginViewModel    login view creator
     * @param signupViewModel   signup view creator
     * @param userDataAccessObject  access to database
     * @return UserSignupView   the created view
     */
    public static UserSignupView createSignupView(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, UserSignupViewModel signupViewModel, UserDataAccessInterface userDataAccessObject) {
        UserSignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel, userDataAccessObject);

        UserSignupPresenter signupPresenter = new UserSignupPresenter(viewManagerModel, signupViewModel, loginViewModel);

        return new UserSignupView(signupController, signupPresenter, signupViewModel);
    }

    /**
     * create controller instance
     * @param viewManagerModel  controller of view models
     * @param loginViewModel    login view creator
     * @param signupViewModel   signup view creator
     * @param userDataAccessObject  access to database
     * @return UserSignupController the created controller
     */
    private static UserSignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, UserSignupViewModel signupViewModel, LoginViewModel loginViewModel, UserDataAccessInterface userDataAccessObject) {
        SignupOutputBoundary signupOutputBoundary = new UserSignupPresenter(viewManagerModel, signupViewModel, loginViewModel);

        UserSignupInputBoundary signupInteractor = new UserSignupInteractor(
                userDataAccessObject, signupOutputBoundary);

        return new UserSignupController(signupInteractor);
    }
}
