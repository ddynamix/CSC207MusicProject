package interface_adapter.splash;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.usersignup.UserSignupViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class SplashPresenterTest {

    private ViewManagerModel viewManagerModel;
    private LoginViewModel loginViewModel;
    private SplashPresenter splashPresenter;
    private UserSignupViewModel userSignupViewModel;

    @BeforeEach
    void setUp() {
        viewManagerModel = mock(ViewManagerModel.class);
        loginViewModel = mock(LoginViewModel.class);
        userSignupViewModel = mock(UserSignupViewModel.class);

        splashPresenter = new SplashPresenter(viewManagerModel, loginViewModel, userSignupViewModel);
    }

    @Test
    void testPrepareSignupView() {
        String expectedSignupViewName = "signupView";
        when(userSignupViewModel.getViewName()).thenReturn(expectedSignupViewName);

        splashPresenter.prepareSignupView();

        verify(viewManagerModel).setActiveView(expectedSignupViewName);
        verify(viewManagerModel).firePropertyChanged();
    }

    @Test
    void testPrepareLoginView() {
        String expectedLoginViewName = "loginView";
        when(loginViewModel.getViewName()).thenReturn(expectedLoginViewName);

        splashPresenter.prepareLoginView();

        verify(viewManagerModel).setActiveView(expectedLoginViewName);
        verify(viewManagerModel).firePropertyChanged();
    }
}
