package usecase.usersignup;

public interface UserSignupInputBoundary {
    void execute(UserSignupData userSignupData);
    void attemptSignUp(UserSignupData userSignupData);
}
