package use_case.login;

/**
 * interface for output date for login use case
 */
public interface LoginOutputBoundary {
    void prepareSuccessView(LoginOutputData loginOutputData);
    void prepareFailView(String error);
}
