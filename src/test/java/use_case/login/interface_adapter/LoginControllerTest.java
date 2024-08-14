package use_case.login.interface_adapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LoginControllerTest {

    private LoginController loginController;
    private LoginInputBoundary loginInteractor;

    @BeforeEach
    public void setUp() {
        loginInteractor = mock(LoginInputBoundary.class);
        loginController = new LoginController(loginInteractor);
    }

    @Test
    public void testExecute() {
        String username = "testUser";
        String password = "testPass";

        loginController.execute(username, password);

        ArgumentCaptor<LoginInputData> inputDataCaptor = ArgumentCaptor.forClass(LoginInputData.class);
        verify(loginInteractor, times(1)).attemptLogin(inputDataCaptor.capture());

        LoginInputData capturedInputData = inputDataCaptor.getValue();
        assertEquals(username, capturedInputData.getUsername());
        assertEquals(password, capturedInputData.getPassword());
    }
}