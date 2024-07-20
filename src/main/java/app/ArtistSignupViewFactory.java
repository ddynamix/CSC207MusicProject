package app;

import dataaccess.UserDataAccessInterface;
import interface_adapter.ViewManagerModel;
import interface_adapter.artistsignup.ArtistSignupController;
import interface_adapter.artistsignup.ArtistSignupPresenter;
import interface_adapter.artistsignup.ArtistSignupViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signupselector.SignupSelectorViewModel;
import usecase.signup.SignupOutputBoundary;
import usecase.signup.artistsignup.ArtistSignupInputBoundary;
import usecase.signup.artistsignup.ArtistSignupInteractor;
import view.ArtistSignupView;

public class ArtistSignupViewFactory {

    private ArtistSignupViewFactory() {
    }

    public static ArtistSignupView createSignupView(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, ArtistSignupViewModel artistSignupViewModel, SignupSelectorViewModel signupSelectorViewModel, UserDataAccessInterface userDataAccessObject) {
        ArtistSignupController artistSignupController = createUserSignupUseCase(viewManagerModel, artistSignupViewModel, loginViewModel, signupSelectorViewModel, userDataAccessObject);

        ArtistSignupPresenter artistSignupPresenter = new ArtistSignupPresenter(viewManagerModel, artistSignupViewModel, loginViewModel, signupSelectorViewModel);

        return new ArtistSignupView(artistSignupController, artistSignupPresenter, artistSignupViewModel);
    }

    private static ArtistSignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, ArtistSignupViewModel artistSignupViewModel, LoginViewModel loginViewModel, SignupSelectorViewModel signupSelectorViewModel, UserDataAccessInterface userDataAccessObject) {
        SignupOutputBoundary signupOutputBoundary = new ArtistSignupPresenter(viewManagerModel, artistSignupViewModel, loginViewModel, signupSelectorViewModel);

        ArtistSignupInputBoundary userSignupInteractor = new ArtistSignupInteractor(
                userDataAccessObject, signupOutputBoundary);

        return new ArtistSignupController(userSignupInteractor);
    }
}
