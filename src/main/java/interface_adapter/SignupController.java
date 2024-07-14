package interface_adapter;

import usecase.usersignup.UserSignupInputBoundary;
import usecase.usersignup.UserSignupInputData;

public class SignupController {

    final UserSignupInputBoundary userSignupUseCaseInteractor;

    public SignupController(UserSignupInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    public void execute(String username, String password, String repeatPass, String email, String firstName, String lastName) {
        UserSignupInputData signupInputData = new UserSignupInputData(username, password, repeatPass, email, firstName, lastName);

        userSignupUseCaseInteractor.execute(signupInputData);
    }
}