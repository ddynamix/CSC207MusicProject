package app;

import dataaccess.TEMPFileAccessDataStorage;
import dataaccess.UserDataAccessInterface;
import interface_adapter.*;
import interface_adapter.login.LoginViewModel;
import interface_adapter.audiencesignup.AudienceSignupController;
import interface_adapter.audiencesignup.AudienceSignupPresenter;
import interface_adapter.audiencesignup.AudienceSignupViewModel;
import interface_adapter.signupselector.SignupSelectorViewModel;
import usecase.audiencesignup.AudienceSignupInputBoundary;
import usecase.audiencesignup.AudienceSignupInteractor;
import usecase.SignupOutputBoundary;
import view.AudienceSignupView;

import javax.swing.*;
import java.io.IOException;

public class AudienceSignupViewFactory {

    private AudienceSignupViewFactory() {
    }

    public static AudienceSignupView createSignupView(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, AudienceSignupViewModel audienceSignupViewModel, SignupSelectorViewModel signupSelectorViewModel) {
        AudienceSignupController audienceSignupController = createUserSignupUseCase(viewManagerModel, audienceSignupViewModel, loginViewModel, signupSelectorViewModel);

        AudienceSignupPresenter audienceSignupPresenter = new AudienceSignupPresenter(viewManagerModel, audienceSignupViewModel, loginViewModel, signupSelectorViewModel);

        return new AudienceSignupView(audienceSignupController, audienceSignupPresenter, audienceSignupViewModel);
    }

    private static AudienceSignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, AudienceSignupViewModel audienceSignupViewModel, LoginViewModel loginViewModel, SignupSelectorViewModel signupSelectorViewModel) {
        try {
            UserDataAccessInterface userDataAccessObject = new TEMPFileAccessDataStorage("./users.csv");

            JOptionPane.showMessageDialog(null, "Could not open user data file.");


            SignupOutputBoundary signupOutputBoundary = new AudienceSignupPresenter(viewManagerModel, audienceSignupViewModel, loginViewModel, signupSelectorViewModel);

            AudienceSignupInputBoundary userSignupInteractor = new AudienceSignupInteractor(
                    userDataAccessObject, signupOutputBoundary);

            return new AudienceSignupController(userSignupInteractor);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
            return null;
        }
    }
}
