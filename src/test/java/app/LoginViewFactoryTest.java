package app;

import dataaccess.UserDataAccessInterface;
import interface_adapter.ViewManagerModel;
import interface_adapter.homescreen.HomescreenViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.splash.SplashViewModel;
import usecase.login.LoginInputBoundary;
import usecase.login.LoginInteractor;
import view.LoginView;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
