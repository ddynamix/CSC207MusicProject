package app;

import dataaccess.TEMPFileAccessDataStorage;
import dataaccess.UserDataAccessInterface;
import interface_adapter.ViewManagerModel;
import interface_adapter.artistsignup.ArtistSignupController;
import interface_adapter.artistsignup.ArtistSignupPresenter;
import interface_adapter.artistsignup.ArtistSignupViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signupselector.SignupSelectorViewModel;
import usecase.SignupOutputBoundary;
import usecase.artistsignup.ArtistSignupInputBoundary;
import usecase.artistsignup.ArtistSignupInteractor;
import view.ArtistSignupView;

import javax.swing.*;
import java.io.IOException;

public class ArtistSignupViewFactory {

    private ArtistSignupViewFactory() {
    }

    public static ArtistSignupView createSignupView(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, ArtistSignupViewModel artistSignupViewModel, SignupSelectorViewModel signupSelectorViewModel) {
        ArtistSignupController artistSignupController = createUserSignupUseCase(viewManagerModel, artistSignupViewModel, loginViewModel, signupSelectorViewModel);

        ArtistSignupPresenter artistSignupPresenter = new ArtistSignupPresenter(viewManagerModel, artistSignupViewModel, loginViewModel, signupSelectorViewModel);

        return new ArtistSignupView(artistSignupController, artistSignupPresenter, artistSignupViewModel);
    }

    private static ArtistSignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, ArtistSignupViewModel artistSignupViewModel, LoginViewModel loginViewModel, SignupSelectorViewModel signupSelectorViewModel) {
        try {
            UserDataAccessInterface userDataAccessObject = new TEMPFileAccessDataStorage("./users.csv");

            JOptionPane.showMessageDialog(null, "Could not open user data file.");


            SignupOutputBoundary signupOutputBoundary = new ArtistSignupPresenter(viewManagerModel, artistSignupViewModel, loginViewModel, signupSelectorViewModel);

            ArtistSignupInputBoundary userSignupInteractor = new ArtistSignupInteractor(
                    userDataAccessObject, signupOutputBoundary);

            return new ArtistSignupController(userSignupInteractor);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
            return null;
        }
    }
}
