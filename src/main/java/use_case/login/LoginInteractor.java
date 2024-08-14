package use_case.login;

import app.interface_adapter_tools.UserSession;
import data_access.UserDataAccessInterface;
import entity.user.User;

/**
 * interactor for login use case
 */
public class LoginInteractor implements LoginInputBoundary {
    final UserDataAccessInterface userDataAccessObject;
    final LoginOutputBoundary loginPresenter;

    /**
     * create instance of interactor for login use case
     * @param userDataAccessObject user DAO
     * @param loginPresenter presenter for login use case
     */
    public LoginInteractor(UserDataAccessInterface userDataAccessObject, LoginOutputBoundary loginPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.loginPresenter = loginPresenter;
    }

    /**
     * check login and continue actions
     * @param loginInputData data to check
     */
    @Override
    public void attemptLogin(LoginInputData loginInputData) {
        if (!userDataAccessObject.userExistsInDatabase(loginInputData.getUsername())) {
            loginPresenter.prepareFailView("User does not exist.");
        } else if (!userDataAccessObject.passwordMatches(loginInputData.getUsername(), loginInputData.getPassword())) {
            loginPresenter.prepareFailView("Incorrect password.");
        } else {
            User user = userDataAccessObject.getUserFromUsername(loginInputData.getUsername());
            UserSession.getInstance().setLoggedInUser(user);
            loginPresenter.prepareSuccessView(new LoginOutputData(user));
            System.out.println("Login successful!");
        }
    }
}
