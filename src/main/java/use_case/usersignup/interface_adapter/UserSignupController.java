package use_case.usersignup.interface_adapter;

import use_case.usersignup.UserSignupInputBoundary;

import use_case.usersignup.UserSignupData;


public class UserSignupController {
    final UserSignupInputBoundary userSignupUseCaseInteractor;

    public UserSignupController(UserSignupInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    public void execute(String type, String username, String password, String repeatPass, String email, String name) {
        UserSignupData signupInputData = new UserSignupData(type, username, password, repeatPass, email, name);

        userSignupUseCaseInteractor.attemptSignUp(signupInputData);
    }

    public void cancelClicked() {
        userSignupUseCaseInteractor.cancelSignUp();
    }
}
