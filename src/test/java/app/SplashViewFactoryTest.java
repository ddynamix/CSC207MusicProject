package app;

import interface_adapter.splash.SplashViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.usersignup.UserSignupViewModel;
import view.SplashView;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class SplashViewFactoryTest {

    @Test
    void testCreateSplashView() {
        // Mocking the dependencies
        ViewManagerModel viewManagerModel = mock(ViewManagerModel.class);
        LoginViewModel loginViewModel = mock(LoginViewModel.class);
        SplashViewModel splashViewModel = mock(SplashViewModel.class);
        UserSignupViewModel userSignupViewModel = mock(UserSignupViewModel.class);

        SplashView splashView = SplashViewFactory.createSplashView(
                viewManagerModel,
                loginViewModel,
                splashViewModel,
                userSignupViewModel
        );

        assertNotNull(splashView, "SplashView should not be null");
    }
}
