package interface_adapter.venuesignup;

import interface_adapter.UserSignupState;
import interface_adapter.ViewManagerModel;
import interface_adapter.audiencesignup.AudienceSignupViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signupselector.SignupSelectorViewModel;
import usecase.SignupOutputBoundary;
import usecase.SignupOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VenueSignupPresenter implements SignupOutputBoundary {

    private final VenueSignupViewModel venueSignupViewModel;
    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;
    private final SignupSelectorViewModel signupSelectorViewModel;

    public VenueSignupPresenter(ViewManagerModel viewManagerModel,
                                   VenueSignupViewModel venueSignupViewModel,
                                   LoginViewModel loginViewModel,
                                   SignupSelectorViewModel signupSelectorViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.venueSignupViewModel = venueSignupViewModel;
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

        UserSignupState userSignupState = venueSignupViewModel.getState();
        LoginState loginState = loginViewModel.getState();
        loginState.setUsername(response.getUsername());
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        UserSignupState userSignupState = venueSignupViewModel.getState();
        userSignupState.setUsernameError(error);
        venueSignupViewModel.firePropertyChanged();
    }
}
