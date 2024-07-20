package usecase.artistsignup;

import dataaccess.UserDataAccessInterface;
import dataaccess.UserDataAccessObject;
import entity.user.ArtistUser;
import usecase.SignupOutputBoundary;
import usecase.SignupOutputData;

import java.time.LocalDateTime;

public class ArtistSignupInteractor implements ArtistSignupInputBoundary {
    final UserDataAccessInterface userDataAccessInterface;
    final SignupOutputBoundary userPresenter;

    public ArtistSignupInteractor(UserDataAccessInterface userSignupDataAccessInterface,
                                  SignupOutputBoundary signupOutputBoundary) {
        this.userDataAccessInterface = userSignupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
    }

    @Override
    public void attemptSignUp(ArtistSignupInputData artistSignupInputData) {
        if (!artistSignupInputData.getPassword().equals(artistSignupInputData.getRepeatPass())) {
            userPresenter.prepareFailView("Passwords don't match.");
        } else {
            try {
                ArtistUser artist = new ArtistUser(artistSignupInputData.getUsername(), artistSignupInputData.getPassword(), artistSignupInputData.getEmail(), artistSignupInputData.getStageName());
                userDataAccessInterface.create(artist);

                LocalDateTime now = LocalDateTime.now();
                SignupOutputData signupOutputData = new SignupOutputData(artist.getUsername(), now.toString());
                userPresenter.prepareSuccessView(signupOutputData);
            } catch (UserDataAccessObject.DuplicateUsernameException e) {
                userPresenter.prepareFailView("Username already exists.");
            }
        }
    }
}
