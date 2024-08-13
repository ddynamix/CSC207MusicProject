package use_case.usersignup.interface_adapter;

import use_case.usersignup.UserSignupInputBoundary;

import use_case.usersignup.UserSignupData;


/**
 * controller for user signup use case
 */
public class UserSignupController {
    final UserSignupInputBoundary userSignupUseCaseInteractor;

    /**
     * create instance of controller for user sign up use case
     * @param userSignupUseCaseInteractor
     */
    public UserSignupController(UserSignupInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    /**
     * perform sign in
     * @param type of user (audience, artist, venue)
     * @param username of user
     * @param password of user
     * @param repeatPass of user sign up
     * @param email of user
     * @param name of user
     */
    public void execute(String type, String username, String password, String repeatPass, String email, String name) {
        UserSignupData signupInputData = new UserSignupData(type, username, password, repeatPass, email, name);

        userSignupUseCaseInteractor.attemptSignUp(signupInputData);
    }
}
