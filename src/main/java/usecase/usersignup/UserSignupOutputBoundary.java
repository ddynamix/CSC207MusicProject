package usecase.usersignup;

public interface UserSignupOutputBoundary {
    void prepareSuccessView(UserSignupOutputData user);

    void prepareFailView(String error);
}
