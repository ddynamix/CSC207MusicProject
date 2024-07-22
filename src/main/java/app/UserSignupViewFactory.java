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

public class UserSignupViewFactory {

    private UserSignupViewFactory() {
    }

    public static UserSignupView createSignupView(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, UserSignupViewModel signupViewModel, UserDataAccessInterface userDataAccessObject) {
        UserSignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel, userDataAccessObject);

        UserSignupPresenter signupPresenter = new UserSignupPresenter(viewManagerModel, signupViewModel, loginViewModel);

        return new UserSignupView(signupController, signupPresenter, signupViewModel);
    }

    private static UserSignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, UserSignupViewModel signupViewModel, LoginViewModel loginViewModel, UserDataAccessInterface userDataAccessObject) {
        SignupOutputBoundary signupOutputBoundary = new UserSignupPresenter(viewManagerModel, signupViewModel, loginViewModel);

        UserSignupInputBoundary signupInteractor = new UserSignupInteractor(
                userDataAccessObject, signupOutputBoundary);

        return new UserSignupController(signupInteractor);
    }
}
