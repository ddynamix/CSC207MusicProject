package interface_adapter.artistsignup;

import interface_adapter.UserSignupState;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signupselector.SignupSelectorViewModel;
import usecase.signup.SignupOutputBoundary;
import usecase.signup.SignupOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ArtistSignupPresenter implements SignupOutputBoundary {
    private final ArtistSignupViewModel artistSignupViewModel;
    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;
    private final SignupSelectorViewModel signupSelectorViewModel;

    public ArtistSignupPresenter(ViewManagerModel viewManagerModel,
                                   ArtistSignupViewModel artistSignupViewModel,
                                   LoginViewModel loginViewModel,
                                   SignupSelectorViewModel signupSelectorViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.artistSignupViewModel = artistSignupViewModel;
        this.loginViewModel = loginViewModel;
        this.signupSelectorViewModel = signupSelectorViewModel;
    }

    // Switch to signup selector on cancel
    public void prepareSignupSelectorView() {
        viewManagerModel.setActiveView(signupSelectorViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    // Switch to the login view on success
    @Override
    public void prepareSuccessView(SignupOutputData response) {
        LocalDateTime responseTime = LocalDateTime.parse(response.getCreationTime());
        response.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));

        UserSignupState userSignupState = artistSignupViewModel.getState();
        LoginState loginState = loginViewModel.getState();
        loginState.setUsername(response.getUsername());
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        UserSignupState userSignupState = artistSignupViewModel.getState();
        userSignupState.setUsernameError(error);
        artistSignupViewModel.firePropertyChanged();
    }
}
