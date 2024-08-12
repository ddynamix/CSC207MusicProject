package use_case.usersignup.interface_adapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view_model.*;
import app.interface_adapter_tools.ViewManagerModel;
import use_case.usersignup.SignupOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.mockito.Mockito.*;

public class UserSignupPresenterTest {

    private UserSignupPresenter userSignupPresenter;
    private UserSignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private SplashViewModel splashViewModel;
    private ViewManagerModel viewManagerModel;

    @BeforeEach
    public void setUp() {
        signupViewModel = mock(UserSignupViewModel.class);
        loginViewModel = mock(LoginViewModel.class);
        splashViewModel = mock(SplashViewModel.class);
        viewManagerModel = mock(ViewManagerModel.class);
        userSignupPresenter = new UserSignupPresenter(viewManagerModel, signupViewModel, splashViewModel, loginViewModel);
    }

    @Test
    public void testPrepareSuccessView() {
        SignupOutputData response = new SignupOutputData("testUser", LocalDateTime.now().toString());
        LoginState loginState = new LoginState();
        when(loginViewModel.getState()).thenReturn(loginState);

        userSignupPresenter.prepareSuccessView(response);

        verify(loginViewModel, times(1)).setState(loginState);
        verify(loginViewModel, times(1)).firePropertyChanged();
        verify(viewManagerModel, times(1)).setActiveView(loginViewModel.getViewName());
        verify(viewManagerModel, times(1)).firePropertyChanged();
    }

    @Test
    public void testPrepareFailView() {
        String error = "Signup failed";
        UserSignupState signupState = new UserSignupState();
        when(signupViewModel.getState()).thenReturn(signupState);

        userSignupPresenter.prepareFailView(error);

        verify(signupViewModel, times(1)).firePropertyChanged();
    }
}