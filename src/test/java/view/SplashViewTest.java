package view;

import use_case.splash.interface_adapter.SplashPresenter;
import view_model.SplashViewModel;
import org.mockito.Mock;
import view.jswing_views.SplashView;

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
