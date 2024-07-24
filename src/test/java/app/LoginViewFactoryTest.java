package app;

import app.swing_view_factories.LoginViewFactory;
import data_access.UserDataAccessInterface;
import app.interface_adapter_tools.ViewManagerModel;
import use_case.homescreen.interface_adapter.HomescreenViewModel;
import use_case.login.interface_adapter.LoginViewModel;
import use_case.splash.interface_adapter.SplashViewModel;
import view.jswing_views.LoginView;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class LoginViewFactoryTest {

    @Test
    void testCreateLoginView() {
        // Mocking the dependencies
        ViewManagerModel viewManagerModel = mock(ViewManagerModel.class);
        LoginViewModel loginViewModel = mock(LoginViewModel.class);
        SplashViewModel splashViewModel = mock(SplashViewModel.class);
        HomescreenViewModel homescreenViewModel = mock(HomescreenViewModel.class);
        UserDataAccessInterface userDataAccessObject = mock(UserDataAccessInterface.class);

        LoginView loginView = LoginViewFactory.createLoginView(
                viewManagerModel,
                loginViewModel,
                splashViewModel,
                homescreenViewModel,
                userDataAccessObject
        );

        assertNotNull(loginView, "LoginView should not be null");
    }
}
