package view;

import use_case.login.interface_adapter.LoginController;
import use_case.login.interface_adapter.LoginPresenter;
import view_model.LoginViewModel;
import org.mockito.Mock;
import view.jswing_views.LoginView;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginViewTest {

    private LoginView loginView;

    @Mock
    private LoginViewModel loginViewModel;

    @Mock
    private LoginController loginController;

    @Mock
    private LoginPresenter loginPresenter;

//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//
//        loginView = new LoginView(loginController, loginPresenter, loginViewModel);
//    }
//
//    @Test
//    public void testActionPerformed_logIn() {
//        loginView.usernameInputField.setText("user");
//        loginView.passwordInputField.setText("pass");
//
//        loginView.logIn.doClick();
//
//        verify(loginController).execute("user", "pass");
//    }
//
//    @Test
//    public void testActionPerformed_cancel() {
//        loginView.cancel.doClick();
//
//        verify(loginPresenter).prepareSplashView();
//    }
//
//    @Test
//    public void testPropertyChange_updatesFields() {
//        LoginState state = mock(LoginState.class);
//        when(state.getUsername()).thenReturn("user");
//        when(state.getPassword()).thenReturn("pass");
//
//        PropertyChangeEvent event = new PropertyChangeEvent(loginViewModel, "state", null, state);
//
//        loginView.propertyChange(event);
//
//        assertEquals("user", loginView.usernameInputField.getText());
//        assertEquals("pass", loginView.passwordInputField.getText());
//    }
}
