package use_case.login;

import app.interface_adapter_tools.UserSession;
import data_access.UserDataAccessInterface;
import data_access.UserDataAccessObject;
import entity.user.User;

public class LoginInteractor implements LoginInputBoundary {
    final UserDataAccessInterface userDataAccessObject;
    final LoginOutputBoundary loginPresenter;

    public LoginInteractor(UserDataAccessInterface userDataAccessObject, LoginOutputBoundary loginPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.loginPresenter = loginPresenter;
    }

    @Override
    public void attemptLogin(LoginInputData loginInputData) {
        if (!userDataAccessObject.userExistsInDatabase(loginInputData.getUsername())) {
            loginPresenter.prepareFailView("User does not exist.");
        } else if (!userDataAccessObject.passwordMatches(loginInputData.getUsername(), loginInputData.getPassword())) {
            loginPresenter.prepareFailView("Incorrect password.");
        } else {
            try {
                User user = userDataAccessObject.getUserFromUsername(loginInputData.getUsername());
                UserSession.getInstance().setLoggedInUser(user);
                loginPresenter.prepareSuccessView(new LoginOutputData(user));
                System.out.println("Login successful!");
            } catch (UserDataAccessObject.UserNotFoundException e) {
                e.printStackTrace();
            }


        }
    }
}
