package app;

import interface_adapter.signupselector.SignupSelectorViewModel;
import interface_adapter.splash.SplashPresenter;
import interface_adapter.splash.SplashViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import view.SplashView;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class SplashViewFactoryTest {

    @Test
    void testCreateSplashView() {
        // Mocking the dependencies
        ViewManagerModel viewManagerModel = mock(ViewManagerModel.class);
        LoginViewModel loginViewModel = mock(LoginViewModel.class);
        SignupSelectorViewModel signupSelectorViewModel = mock(SignupSelectorViewModel.class);
        SplashViewModel splashViewModel = mock(SplashViewModel.class);

        SplashView splashView = SplashViewFactory.createSplashView(
                viewManagerModel,
                loginViewModel,
                signupSelectorViewModel,
                splashViewModel
        );

        assertNotNull(splashView, "SplashView should not be null");
    }
}
