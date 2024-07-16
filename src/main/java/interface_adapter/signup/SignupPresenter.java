package interface_adapter.signup;


import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.splash.SplashViewModel;
import usecase.usersignup.UserSignupOutputBoundary;
import usecase.usersignup.UserSignupOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SignupPresenter implements UserSignupOutputBoundary {

    private final SignupViewModel signupViewModel;
    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;
    private final SplashViewModel splashViewModel;

    public SignupPresenter(ViewManagerModel viewManagerModel,
                           SignupViewModel signupViewModel,
                           LoginViewModel loginViewModel,
                           SplashViewModel splashViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
        this.splashViewModel = splashViewModel;
    }

    public void prepareSplashView() {
        viewManagerModel.setActiveView(splashViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareSuccessView(UserSignupOutputData response) {
        // On success, switch to the login view.
        LocalDateTime responseTime = LocalDateTime.parse(response.getCreationTime());
        response.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));

        SignupState signupState = signupViewModel.getState();
        LoginState loginState = loginViewModel.getState();
        loginState.setUsername(response.getUsername());
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        SignupState signupState = signupViewModel.getState();
        signupState.setUsernameError(error);
        signupViewModel.firePropertyChanged();
    }
}
