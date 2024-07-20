package usecase.audiencesignup;

import dataaccess.UserDataAccessInterface;
import dataaccess.UserDataAccessObject;
import entity.user.AudienceUser;
import usecase.SignupOutputBoundary;
import usecase.SignupOutputData;

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
        if (!audienceSignupInputData.getPassword().equals(audienceSignupInputData.getRepeatPass())) {
            userPresenter.prepareFailView("Passwords don't match.");
        } else {
            try {
                AudienceUser user = new AudienceUser(audienceSignupInputData.getUsername(), audienceSignupInputData.getPassword(), audienceSignupInputData.getEmail(), audienceSignupInputData.getFirstName(), audienceSignupInputData.getLastName());
                userDataAccessObject.create(user);
                LocalDateTime now = LocalDateTime.now();
                SignupOutputData signupOutputData = new SignupOutputData(user.getUsername(), now.toString());
                userPresenter.prepareSuccessView(signupOutputData);
            } catch (UserDataAccessObject.DuplicateUsernameException e) {
                userPresenter.prepareFailView("Username already exists.");
            }
        }
    }
}