package interface_adapter.artistsignup;

import usecase.artistsignup.ArtistSignupInputBoundary;
import usecase.artistsignup.ArtistSignupInputData;

public class ArtistSignupController {
    final ArtistSignupInputBoundary userSignupUseCaseInteractor;

    public ArtistSignupController(ArtistSignupInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    public void execute(String username, String password, String repeatPass, String email, String stageName) {
        ArtistSignupInputData signupInputData = new ArtistSignupInputData(username, password, repeatPass, email, stageName);

        userSignupUseCaseInteractor.attemptSignUp(signupInputData);
    }
}
