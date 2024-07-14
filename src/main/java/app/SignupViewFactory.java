package app;

import dataaccess.TEMPFileAccessDataStorage;
import dataaccess.UserDataAccessInterface;
import dataaccess.UserDataAccessObject;
import dataaccess.mongodb.Connection;
import entity.user.User;
import entity.user.UserFactory;
import interface_adapter.*;
import usecase.usersignup.UserSignupInputBoundary;
import usecase.usersignup.UserSignupInteractor;
import usecase.usersignup.UserSignupOutputBoundary;
import view.SignupView;

import javax.swing.*;
import java.io.IOException;

public class SignupViewFactory {
    /** Prevent instantiation. */
    private SignupViewFactory() {}

    public static SignupView create(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignupViewModel signupViewModel) {

        try {
            SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel);
            return new SignupView(signupController, signupViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static SignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel) throws IOException {
        UserFactory userFactoryDAO = new UserFactory();
        UserDataAccessInterface userDataAccessObject = new TEMPFileAccessDataStorage("./users.csv", userFactoryDAO);

        // Notice how we pass this method's parameters to the Presenter.
        UserSignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);

        UserFactory userFactoryInteractor = new UserFactory();

        UserSignupInputBoundary userSignupInteractor = new UserSignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactoryInteractor);

        return new SignupController(userSignupInteractor);
    }
}
