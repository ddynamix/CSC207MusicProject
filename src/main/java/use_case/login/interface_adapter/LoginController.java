package use_case.login.interface_adapter;

import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

public class LoginController {
    final LoginInputBoundary loginInteractor;

    public LoginController(LoginInputBoundary loginInteractor) {
        this.loginInteractor = loginInteractor;
    }

    public void execute(String username, String password) {
        loginInteractor.attemptLogin(new LoginInputData(username, password));
    }

    public void cancelClicked() {
        loginInteractor.cancelLogin();
    }
}
