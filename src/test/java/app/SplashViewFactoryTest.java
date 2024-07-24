package app;

import app.swing_view_factories.SplashViewFactory;
import interface_adapter.splash.SplashViewModel;
import interface_adapter.ViewManagerModel;
import use_case.login.interface_adapter.LoginViewModel;
import use_case.usersignup.interface_adapter.UserSignupViewModel;
import view.jswing_views.SplashView;
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
