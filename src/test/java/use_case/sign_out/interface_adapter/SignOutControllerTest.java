package use_case.sign_out.interface_adapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.sign_out.SignOutInputBoundary;

import static org.mockito.Mockito.*;

public class SignOutControllerTest {

    private SignOutController signOutController;
    private SignOutInputBoundary signOutInteractor;

    @BeforeEach
    public void setUp() {
        signOutInteractor = mock(SignOutInputBoundary.class);
        signOutController = new SignOutController(signOutInteractor);
    }

    @Test
    public void testExecuteSignOut() {
        signOutController.executeSignOut();
        verify(signOutInteractor, times(1)).signOut();
    }
}