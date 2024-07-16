package app;

import dataaccess.TEMPFileAccessDataStorage;
import dataaccess.UserDataAccessInterface;
import entity.user.UserFactory;
import interface_adapter.*;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.splash.SplashViewModel;
import usecase.usersignup.UserSignupInputBoundary;
import usecase.usersignup.UserSignupInteractor;
import usecase.usersignup.UserSignupOutputBoundary;
import view.SignupView;

import javax.swing.*;
import java.io.IOException;

public class SignupViewFactory {

    private SignupViewFactory() {}

    public static SignupView createSignupView(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignupViewModel signupViewModel, SplashViewModel splashViewModel) {

        try {
            SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel, splashViewModel);
            SignupPresenter signupPresenter = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel, splashViewModel);
            return new SignupView(signupController, signupPresenter, signupViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static SignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel, SplashViewModel splashViewModel) throws IOException {
        UserFactory userFactoryDAO = new UserFactory();
        UserDataAccessInterface userDataAccessObject = new TEMPFileAccessDataStorage("./users.csv", userFactoryDAO);

        // Notice how we pass this method's parameters to the Presenter.
        UserSignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel, splashViewModel);

        UserFactory userFactoryInteractor = new UserFactory();

        UserSignupInputBoundary userSignupInteractor = new UserSignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactoryInteractor);

        return new SignupController(userSignupInteractor);
    }
}
