package use_case.login.interface_adapter;

import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

/**
 * controller for login use case
 */
public class LoginController {
    final LoginInputBoundary loginInteractor;

    /**
     * create instance of controller for login use case
     * @param loginInteractor interactor for login use case
     */
    public LoginController(LoginInputBoundary loginInteractor) {
        this.loginInteractor = loginInteractor;
    }

    /**
     * try to login in and continue actions
     * @param username of login
     * @param password of login
     */
    public void execute(String username, String password) {
        loginInteractor.attemptLogin(new LoginInputData(username, password));
    }
}
