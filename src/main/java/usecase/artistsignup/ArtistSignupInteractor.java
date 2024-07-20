package usecase.artistsignup;

import dataaccess.UserDataAccessInterface;
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
        if (userDataAccessInterface.userExistsInDatabase(artistSignupInputData.getUsername())) {
            userPresenter.prepareFailView("Artist already exists.");
        } else if (!artistSignupInputData.getPassword().equals(artistSignupInputData.getRepeatPass())) {
            userPresenter.prepareFailView("Passwords don't match.");
        } else {
            ArtistUser artist = new ArtistUser(artistSignupInputData.getUsername(), artistSignupInputData.getPassword(), artistSignupInputData.getEmail(), artistSignupInputData.getStageName());
            userDataAccessInterface.create(artist);

            LocalDateTime now = LocalDateTime.now();
            SignupOutputData signupOutputData = new SignupOutputData(artist.getUsername(), now.toString());
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }
}
