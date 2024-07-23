package interface_adapter.usersignup;

import usecase.usersignup.UserSignupInputBoundary;

import usecase.usersignup.UserSignupData;


public class UserSignupController {
    final UserSignupInputBoundary userSignupUseCaseInteractor;

    public UserSignupController(UserSignupInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    public void execute(String type, String username, String password, String repeatPass, String email, String name) {
        UserSignupData signupInputData = new UserSignupData(type, username, password, repeatPass, email, name);

        userSignupUseCaseInteractor.attemptSignUp(signupInputData);
    }
}