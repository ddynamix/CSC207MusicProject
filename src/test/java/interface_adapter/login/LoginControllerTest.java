package interface_adapter.login;

import use_case.login.LoginInputBoundary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.login.interface_adapter.LoginController;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

class LoginControllerTest {

    private LoginInputBoundary loginInteractor;
    private LoginController loginController;

    @BeforeEach
    void setUp() {
        loginInteractor = mock(LoginInputBoundary.class);
        loginController = new LoginController(loginInteractor);
    }

    @Test
    void testExecute() {
        String username = "testUser";
        String password = "testPassword";

        loginController.execute(username, password);

        verify(loginInteractor).attemptLogin(argThat(inputData ->
                username.equals(inputData.getUsername()) &&
                        password.equals(inputData.getPassword())
        ));
    }
}
