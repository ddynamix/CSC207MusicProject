package interface_adapter.venuesignup;

import usecase.signup.venuesignup.VenueSignupInputBoundary;
import usecase.signup.venuesignup.VenueSignupInputData;

public class VenueSignupController {
    final VenueSignupInputBoundary userSignupUseCaseInteractor;

    public VenueSignupController(VenueSignupInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    public void execute(String username, String password, String repeatPass, String email, String venueName, String venueLocation) {
        VenueSignupInputData signupInputData = new VenueSignupInputData(username, password, repeatPass, email, venueName, venueLocation);

        userSignupUseCaseInteractor.attemptSignUp(signupInputData);
    }
}
