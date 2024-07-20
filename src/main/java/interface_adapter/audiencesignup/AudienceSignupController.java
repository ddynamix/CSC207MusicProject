package interface_adapter.audiencesignup;

import usecase.signup.audiencesignup.AudienceSignupInputBoundary;
import usecase.signup.audiencesignup.AudienceSignupInputData;

public class AudienceSignupController {
    final AudienceSignupInputBoundary userSignupUseCaseInteractor;

    public AudienceSignupController(AudienceSignupInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    public void execute(String username, String password, String repeatPass, String email, String firstName, String lastName) {
        AudienceSignupInputData signupInputData = new AudienceSignupInputData(username, password, repeatPass, email, firstName, lastName);

        userSignupUseCaseInteractor.attemptSignUp(signupInputData);
    }
}