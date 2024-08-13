package use_case.login.interface_adapter;

import entity.user.AudienceUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view_model.HomescreenState;
import view_model.HomescreenViewModel;
import view_model.SplashViewModel;
import app.interface_adapter_tools.ViewManagerModel;
import use_case.login.LoginOutputData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LoginPresenterTest {

    private LoginPresenter loginPresenter;
    private SplashViewModel splashViewModel;
    private HomescreenViewModel homescreenViewModel;
    private ViewManagerModel viewManagerModel;

    private AudienceUser signedInAs;

    @BeforeEach
    public void setUp() {
        splashViewModel = mock(SplashViewModel.class);
        homescreenViewModel = mock(HomescreenViewModel.class);
        viewManagerModel = mock(ViewManagerModel.class);
        loginPresenter = new LoginPresenter(splashViewModel, homescreenViewModel, viewManagerModel);

        signedInAs = new AudienceUser("Test User", "testUser", "testPass", "testMail");
    }

    @Test
    public void testPrepareSuccessView() {
        LoginOutputData loginOutputData = new LoginOutputData(signedInAs);

        HomescreenState homescreenState = new HomescreenState();
        when(homescreenViewModel.getState()).thenReturn(homescreenState);

        loginPresenter.prepareSuccessView(loginOutputData);

        assertEquals("testUser", homescreenState.getSignedInAs().getUsername());
        verify(homescreenViewModel, times(1)).setState(homescreenState);
        verify(homescreenViewModel, times(1)).firePropertyChanged();
        verify(viewManagerModel, times(1)).setActiveView(homescreenViewModel.getViewName());
        verify(viewManagerModel, times(1)).firePropertyChanged();
    }

    @Test
    public void testPrepareFailView() {
        loginPresenter.prepareFailView("Login failed");

        verify(viewManagerModel, times(1)).firePropertyChanged();
    }
}
