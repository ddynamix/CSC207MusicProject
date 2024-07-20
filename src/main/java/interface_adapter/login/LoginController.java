package interface_adapter.login;

import dataaccess.UserDataAccessInterface;
import usecase.login.LoginInputBoundary;
import usecase.login.LoginInputData;

public class LoginController {
    final LoginInputBoundary loginInteractor;

    public LoginController(UserDataAccessInterface userDataAccessObject, LoginInputBoundary loginInteractor) {
        this.loginInteractor = loginInteractor;
    }

    public void execute(String username, String password) {
        loginInteractor.attemptLogin(new LoginInputData(username, password));
    }
}
