package use_case.usersignup;

/**
 * interface between interactor and presenter
 */
public interface SignupOutputBoundary {
    void prepareSuccessView(SignupOutputData user);

    void prepareFailView(String error);
}
