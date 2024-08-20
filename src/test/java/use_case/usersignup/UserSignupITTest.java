package use_case.usersignup;

import app.interface_adapter_tools.ViewManagerModel;
import data_access.csv.UserLocalCSVDataStorage;
import entity.user.User;
import org.junit.jupiter.api.*;
import use_case.usersignup.interface_adapter.UserSignupController;
import use_case.usersignup.interface_adapter.UserSignupPresenter;
import view_model.LoginViewModel;
import view_model.SplashViewModel;
import view_model.UserSignupViewModel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

public class UserSignupITTest {

    private UserSignupController userSignupController;
    private UserLocalCSVDataStorage userStorage;
    private UserSignupPresenter userSignupPresenter;
    private UserSignupViewModel userSignupViewModel;
    private SplashViewModel splashViewModel;
    private LoginViewModel loginViewModel;
    private File userFile;

    @BeforeEach
    public void setUp() throws IOException {
        userFile = Files.createTempFile("test-user", ".csv").toFile();

        userStorage = new UserLocalCSVDataStorage(userFile.getAbsolutePath());
        userSignupViewModel = new UserSignupViewModel();
        splashViewModel = new SplashViewModel();
        loginViewModel = new LoginViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        userSignupPresenter = new UserSignupPresenter(viewManagerModel, userSignupViewModel, splashViewModel, loginViewModel);
        UserSignupInteractor userSignupInteractor = new UserSignupInteractor(userStorage, userSignupPresenter);
        userSignupController = new UserSignupController(userSignupInteractor);
    }

    @AfterEach
    public void tearDown() {
        userFile.delete();
    }

    @Test
    public void testUserSignup() throws IOException {
        userSignupController.execute("AudienceUser", "testUser", "password", "password", "test@email.com", "Test User");

        User newUser = userStorage.getUserFromUsername("testUser");
        assertNotNull(newUser);
        assertEquals("testUser", newUser.getUsername());
        assertEquals("test@email.com", newUser.getEmail());
        assertEquals("Test User", newUser.getName());
    }
}
