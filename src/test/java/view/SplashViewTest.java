package view;

import use_case.splash.interface_adapter.SplashPresenter;
import use_case.splash.interface_adapter.SplashViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import view.jswing_views.SplashView;

import static org.mockito.Mockito.*;

public class SplashViewTest {

    private SplashView splashView;

    @Mock
    private SplashPresenter splashPresenter;

    @Mock
    private SplashViewModel splashViewModel;

//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//
//        splashView = new SplashView(splashPresenter, splashViewModel);
//    }
//
//    @Test
//    public void testActionPerformed_signupButton() {
//        splashView.signupB.doClick();
//
//        verify(splashPresenter).prepareSignupView();
//    }
//
//    @Test
//    public void testActionPerformed_loginButton() {
//        splashView.loginB.doClick();
//
//        verify(splashPresenter).prepareLoginView();
//    }
}
