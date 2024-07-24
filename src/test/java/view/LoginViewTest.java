package view;

import use_case.login.interface_adapter.LoginController;
import use_case.login.interface_adapter.LoginPresenter;
import use_case.login.interface_adapter.LoginState;
import use_case.login.interface_adapter.LoginViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import view.jswing_views.LoginView;

import java.beans.PropertyChangeEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LoginViewTest {

    private LoginView loginView;

    @Mock
    private LoginViewModel loginViewModel;

    @Mock
    private LoginController loginController;

    @Mock
    private LoginPresenter loginPresenter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        loginView = new LoginView(loginController, loginPresenter, loginViewModel);
    }

    @Test
    public void testActionPerformed_logIn() {
        loginView.usernameInputField.setText("user");
        loginView.passwordInputField.setText("pass");

        loginView.logIn.doClick();

        verify(loginController).execute("user", "pass");
    }

    @Test
    public void testActionPerformed_cancel() {
        loginView.cancel.doClick();

        verify(loginPresenter).prepareSplashView();
    }

    @Test
    public void testPropertyChange_updatesFields() {
        LoginState state = mock(LoginState.class);
        when(state.getUsername()).thenReturn("user");
        when(state.getPassword()).thenReturn("pass");

        PropertyChangeEvent event = new PropertyChangeEvent(loginViewModel, "state", null, state);

        loginView.propertyChange(event);

        assertEquals("user", loginView.usernameInputField.getText());
        assertEquals("pass", loginView.passwordInputField.getText());
    }
}
