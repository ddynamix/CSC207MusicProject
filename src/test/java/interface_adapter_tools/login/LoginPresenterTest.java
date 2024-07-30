package interface_adapter_tools.login;

import entity.user.User;
import app.interface_adapter_tools.ViewManagerModel;
import view_model.HomescreenState;
import view_model.HomescreenViewModel;
import view_model.SplashViewModel;
import use_case.login.LoginOutputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import use_case.login.interface_adapter.LoginPresenter;

import javax.swing.*;

import static org.mockito.Mockito.*;

class LoginPresenterTest {

    private SplashViewModel splashViewModel;
    private HomescreenViewModel homescreenViewModel;
    private ViewManagerModel viewManagerModel;
    private LoginPresenter loginPresenter;

    @BeforeEach
    void setUp() {
        splashViewModel = mock(SplashViewModel.class);
        homescreenViewModel = mock(HomescreenViewModel.class);
        viewManagerModel = mock(ViewManagerModel.class);

        loginPresenter = new LoginPresenter(splashViewModel, homescreenViewModel, viewManagerModel);
    }

    @Test
    void testPrepareSuccessView() {
        User user = new User("testUser", "testUsername","testPassword", "testEmail");
        LoginOutputData outputData = new LoginOutputData(user);

        HomescreenState homescreenState = mock(HomescreenState.class);
        when(homescreenViewModel.getState()).thenReturn(homescreenState);
        when(homescreenViewModel.getViewName()).thenReturn("HomescreenView");

        loginPresenter.prepareSuccessView(outputData);

        verify(homescreenState).setSignedInAs(user);
        verify(homescreenViewModel).setState(homescreenState);
        verify(homescreenViewModel).firePropertyChanged();
        verify(viewManagerModel).setActiveView("HomescreenView");
        verify(viewManagerModel).firePropertyChanged();
    }

    @Test
    void testPrepareFailView() {
        String errorMessage = "Login failed";

        try (MockedStatic<JOptionPane> mockedJOptionPane = Mockito.mockStatic(JOptionPane.class)) {
            loginPresenter.prepareFailView(errorMessage);

            mockedJOptionPane.verify(() -> JOptionPane.showMessageDialog(null, errorMessage));
            verify(viewManagerModel).firePropertyChanged();
        }
    }

    @Test
    void testPrepareSplashView() {
        when(splashViewModel.getViewName()).thenReturn("SplashView");

        loginPresenter.prepareSplashView();

        verify(viewManagerModel).setActiveView("SplashView");
        verify(viewManagerModel).firePropertyChanged();
    }
}
