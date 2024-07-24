package use_case.usersignup.interface_adapter;


import use_case.login.interface_adapter.LoginState;
import use_case.login.interface_adapter.LoginViewModel;
import interface_adapter.ViewManagerModel;
import use_case.usersignup.SignupOutputBoundary;
import use_case.usersignup.SignupOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Presenter for sign up use case
 */
public class UserSignupPresenter implements SignupOutputBoundary {

    private final UserSignupViewModel signupViewModel;
    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;

    public UserSignupPresenter(ViewManagerModel viewManagerModel,
                                   UserSignupViewModel signupViewModel,
                                   LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
    }

    /**
     * Build success if the user was able to be created
     * @param response outgoing message
     */
        @Override
    public void prepareSuccessView(SignupOutputData response) {
        LocalDateTime responseTime = LocalDateTime.parse(response.getCreationTime());
        response.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));

        LoginState loginState = loginViewModel.getState();
        loginState.setUsername(response.getUsername());
        loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Build failure if the user could not be created
     * @param error failure message for user
     */
    @Override
    public void prepareFailView(String error) {
        UserSignupState userSignupState = signupViewModel.getState();
        userSignupState.setUsernameError(error);
        signupViewModel.firePropertyChanged();
    }
}
