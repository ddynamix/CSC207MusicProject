package usecase.signup.audiencesignup;

import dataaccess.UserDataAccessInterface;
import entity.user.AudienceUser;
import usecase.signup.SignupOutputBoundary;
import usecase.signup.SignupOutputData;

import java.time.LocalDateTime;

public class AudienceSignupInteractor implements AudienceSignupInputBoundary {
    final UserDataAccessInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter;

    public AudienceSignupInteractor(UserDataAccessInterface userSignupDataAccessInterface,
                                    SignupOutputBoundary signupOutputBoundary) {
        this.userDataAccessObject = userSignupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
    }

    @Override
    public void attemptSignUp(AudienceSignupInputData audienceSignupInputData) {
        if (userDataAccessObject.userExistsInDatabase(audienceSignupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists.");
        } else if (!audienceSignupInputData.getPassword().equals(audienceSignupInputData.getRepeatPass())) {
            userPresenter.prepareFailView("Passwords don't match.");
        } else {
            AudienceUser user = new AudienceUser(audienceSignupInputData.getUsername(), audienceSignupInputData.getPassword(), audienceSignupInputData.getEmail(), audienceSignupInputData.getFirstName(), audienceSignupInputData.getLastName());
            userDataAccessObject.saveAudienceUser(user);

            LocalDateTime now = LocalDateTime.now();
            SignupOutputData signupOutputData = new SignupOutputData(user.getUsername(), now.toString());
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }
}