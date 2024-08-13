package use_case.login;

/**
 * interface for input data for login use case
 */
public interface LoginInputBoundary {
    void attemptLogin(LoginInputData loginInputData);
}
