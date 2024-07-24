package interface_adapter_tools.splash;

import app.interface_adapter_tools.ViewManagerModel;
import use_case.login.interface_adapter.LoginViewModel;
import use_case.splash.interface_adapter.SplashPresenter;
import use_case.usersignup.interface_adapter.UserSignupViewModel;
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
    void testPrepareLoginView() {
        String expectedLoginViewName = "loginView";
        when(loginViewModel.getViewName()).thenReturn(expectedLoginViewName);

        splashPresenter.prepareLoginView();

        verify(viewManagerModel).setActiveView(expectedLoginViewName);
        verify(viewManagerModel).firePropertyChanged();
    }
}
