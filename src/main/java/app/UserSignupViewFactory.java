package app;

import dataaccess.UserDataAccessInterface;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.usersignup.UserSignupController;
import interface_adapter.usersignup.UserSignupPresenter;
import interface_adapter.usersignup.UserSignupViewModel;
import usecase.usersignup.UserSignupInputBoundary;
import usecase.usersignup.UserSignupInteractor;
import usecase.usersignup.SignupOutputBoundary;
import view.UserSignupView;

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
