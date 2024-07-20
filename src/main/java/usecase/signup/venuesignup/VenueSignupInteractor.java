package usecase.signup.venuesignup;

import dataaccess.UserDataAccessInterface;
import entity.user.VenueUser;
import usecase.signup.SignupOutputBoundary;
import usecase.signup.SignupOutputData;

import java.time.LocalDateTime;

public class VenueSignupInteractor implements VenueSignupInputBoundary {
    final UserDataAccessInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter;

    public VenueSignupInteractor(UserDataAccessInterface userSignupDataAccessInterface,
                                    SignupOutputBoundary signupOutputBoundary) {
        this.userDataAccessObject = userSignupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
    }

    @Override
    public void attemptSignUp(VenueSignupInputData venueSignupInputData) {
        if (userDataAccessObject.userExistsInDatabase(venueSignupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists.");
        } else if (!venueSignupInputData.getPassword().equals(venueSignupInputData.getRepeatPass())) {
            userPresenter.prepareFailView("Passwords don't match.");
        } else {
            VenueUser venue = new VenueUser(venueSignupInputData.getUsername(), venueSignupInputData.getPassword(), venueSignupInputData.getEmail(), venueSignupInputData.getVenueName(), venueSignupInputData.getLocation());
            userDataAccessObject.saveVenueUser(venue);

            LocalDateTime now = LocalDateTime.now();
            SignupOutputData signupOutputData = new SignupOutputData(venue.getUsername(), now.toString());
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }
}
