package use_case.usersignup;

/**
 * interface between controller and interactor
 */
public interface UserSignupInputBoundary {
    void attemptSignUp(UserSignupData userSignupData);
}
