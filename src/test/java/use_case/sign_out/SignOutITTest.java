package use_case.sign_out;

import app.interface_adapter_tools.UserSession;
import entity.user.AudienceUser;
import org.junit.jupiter.api.*;
import use_case.sign_out.interface_adapter.SignOutController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

public class SignOutITTest {

    private SignOutController signOutController;
    private File userFile;

    @BeforeEach
    public void setUp() throws IOException {
        userFile = Files.createTempFile("test-user", ".csv").toFile();

        SignOutInteractor signOutInteractor = new SignOutInteractor();
        signOutController = new SignOutController(signOutInteractor);

        UserSession.getInstance().setLoggedInUser(new AudienceUser("testUser", "testUser", "password", "test@email.com"));
    }

    @AfterEach
    public void tearDown() {
        userFile.delete();
    }

    @Test
    public void testSignOut() {
        signOutController.executeSignOut();

        assertNull(UserSession.getInstance().getLoggedInUser());
    }
}
