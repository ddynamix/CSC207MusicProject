package usecase.login;

import dataaccess.UserDataAccessInterface;
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
            User user = userDataAccessObject.getUserFromUsername(loginInputData.getUsername());
            LoginOutputData loginOutputData = new LoginOutputData(user);
            loginPresenter.prepareSuccessView(loginOutputData);
            System.out.println("Login successful!");
        }
    }
}
