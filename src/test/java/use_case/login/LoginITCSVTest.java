package use_case.login;

import app.interface_adapter_tools.UserSession;
import app.interface_adapter_tools.ViewManagerModel;
import data_access.UserDataAccessInterface;
import data_access.csv.UserLocalCSVDataStorage;
import entity.user.AudienceUser;
import entity.user.User;
import org.junit.jupiter.api.*;
import use_case.login.interface_adapter.LoginController;
import use_case.login.interface_adapter.LoginPresenter;
import view_model.HomescreenViewModel;
import view_model.LoginViewModel;
import view_model.SplashViewModel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

public class LoginITCSVTest {

    private LoginController loginController;
    private UserLocalCSVDataStorage userStorage;
    private LoginPresenter loginPresenter;
    private LoginViewModel loginViewModel;
    private SplashViewModel splashViewModel;
    private HomescreenViewModel homescreenViewModel;
    private File userFile;

    @BeforeEach
    public void setUp() throws IOException {
        userFile = Files.createTempFile("test-user", ".csv").toFile();

        userStorage = new UserLocalCSVDataStorage(userFile.getAbsolutePath());
        loginViewModel = new LoginViewModel();
        splashViewModel = new SplashViewModel();
        homescreenViewModel = new HomescreenViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        loginPresenter = new LoginPresenter(splashViewModel, homescreenViewModel, viewManagerModel);
        LoginInteractor loginInteractor = new LoginInteractor(userStorage, loginPresenter);
        loginController = new LoginController(loginInteractor);
    }

    @AfterEach
    public void tearDown() {
        userFile.delete();
    }

    @Test
    public void testLoginUser() throws IOException {
        User user = new AudienceUser("Test User", "testUser", "test@email.com", "test@email.com");
        try {
            userStorage.create(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        loginController.execute("testUser", "password");

        assertNotNull(UserSession.getInstance().getLoggedInUser());
        //assertEquals("testUser", UserSession.getInstance().getLoggedInUser().getName());
        //assertEquals("testUser", homescreenViewModel.getState().getSignedInAs().getUsername());
    }
}
