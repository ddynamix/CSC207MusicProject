package interface_adapter.audiencesignup;


import interface_adapter.UserSignupState;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.signupselector.SignupSelectorViewModel;
import usecase.signup.SignupOutputBoundary;
import usecase.signup.SignupOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AudienceSignupPresenter implements SignupOutputBoundary {

    private final AudienceSignupViewModel audienceSignupViewModel;
    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;
    private final SignupSelectorViewModel signupSelectorViewModel;

    public AudienceSignupPresenter(ViewManagerModel viewManagerModel,
                                   AudienceSignupViewModel audienceSignupViewModel,
                                   LoginViewModel loginViewModel,
                                   SignupSelectorViewModel signupSelectorViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.audienceSignupViewModel = audienceSignupViewModel;
        this.loginViewModel = loginViewModel;
        this.signupSelectorViewModel = signupSelectorViewModel;
    }

    // on cancel, switch to the signup selector view
    public void prepareSignupSelectorView() {
        viewManagerModel.setActiveView(signupSelectorViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

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

    @Override
    public void prepareFailView(String error) {
        UserSignupState userSignupState = audienceSignupViewModel.getState();
        userSignupState.setUsernameError(error);
        audienceSignupViewModel.firePropertyChanged();
    }
}
