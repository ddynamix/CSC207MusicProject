package use_case.sign_out;

import app.interface_adapter_tools.UserSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class SignOutInteractorTest {

    private SignOutInteractor signOutInteractor;
    private UserSession userSession;

    @BeforeEach
    public void setUp() {
        userSession = mock(UserSession.class);
        signOutInteractor = new SignOutInteractor() {
            @Override
            public void signOut() {
                userSession.signOut();
            }
        };
    }

    @Test
    public void testSignOut() {
        signOutInteractor.signOut();
        verify(userSession, times(1)).signOut();
    }
}