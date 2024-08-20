package use_case.screen_switcher;

import app.interface_adapter_tools.UserSession;
import app.interface_adapter_tools.ViewManagerModel;
import data_access.EventDataAccessInterface;
import data_access.PostDataAccessInterface;
import data_access.UserDataAccessInterface;
import data_access.csv.EventLocalCSVDataStorage;
import data_access.csv.PostLocalCSVDataStorage;
import data_access.csv.UserLocalCSVDataStorage;
import entity.user.AudienceUser;
import org.junit.jupiter.api.*;
import use_case.screen_switcher.interface_adapter.ScreenSwitcherController;
import use_case.screen_switcher.interface_adapter.ScreenSwitcherPresenter;
import view_model.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

public class ScreenSwitcherITTest {

    private ScreenSwitcherController screenSwitcherController;
    private ScreenSwitcherPresenter screenSwitcherPresenter;
    private ViewManagerModel viewManagerModel;
    private File userFile;

    @BeforeEach
    public void setUp() throws IOException {
        userFile = Files.createTempFile("test-user", ".csv").toFile();
        File eventFile = Files.createTempFile("test-event", ".csv").toFile();
        File postFile = Files.createTempFile("test-post", ".csv").toFile();

        // Create data access objects
        UserDataAccessInterface userDataAccess = new UserLocalCSVDataStorage(userFile.getAbsolutePath());
        EventDataAccessInterface eventDataAccess = new EventLocalCSVDataStorage(eventFile.getAbsolutePath(), userDataAccess);
        PostDataAccessInterface postDataAccess = new PostLocalCSVDataStorage(postFile.getAbsolutePath(), userDataAccess);
        viewManagerModel = new ViewManagerModel();
        SplashViewModel splashViewModel = new SplashViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        UserSignupViewModel signupViewModel = new UserSignupViewModel();
        HomescreenViewModel homescreenViewModel = new HomescreenViewModel();
        EventScreenViewModel eventScreenViewModel = new EventScreenViewModel();
        SearchEventsViewModel searchEventsViewModel = new SearchEventsViewModel();
        EventCrafterViewModel eventCrafterViewModel = new EventCrafterViewModel();
        SearchUsersViewModel searchUsersViewModel = new SearchUsersViewModel();
        MyFollowersViewModel myFollowersViewModel = new MyFollowersViewModel();
        IsFollowingViewModel isFollowingViewModel = new IsFollowingViewModel();
        PostMakerViewModel postMakerViewModel = new PostMakerViewModel();
        ProfileViewModel profileViewModel = new ProfileViewModel();
        ProfileEditViewModel editProfileViewModel = new ProfileEditViewModel();
        screenSwitcherPresenter = new ScreenSwitcherPresenter(viewManagerModel, loginViewModel, splashViewModel, signupViewModel, homescreenViewModel, eventScreenViewModel, searchEventsViewModel, eventCrafterViewModel, searchUsersViewModel, myFollowersViewModel, isFollowingViewModel, postMakerViewModel, profileViewModel, editProfileViewModel);
        ScreenSwitcherInteractor screenSwitcherInteractor = new ScreenSwitcherInteractor(screenSwitcherPresenter, userDataAccess, eventDataAccess, postDataAccess);
        screenSwitcherController = new ScreenSwitcherController(screenSwitcherInteractor);
    }

    @AfterEach
    public void tearDown() {
        userFile.delete();
    }

    @Test
    public void testSwitchToHomeScreen() {
        screenSwitcherController.switchToHome();

        assertEquals("home", viewManagerModel.getActiveView());
    }

    @Test
    public void testSwitchToProfileScreen() {
        UserSession.getInstance().setLoggedInUser(new AudienceUser("Test User", "testUser", "password", "test@email.com"));
        screenSwitcherController.switchToMyProfile();

        assertEquals("profile", viewManagerModel.getActiveView());
    }
}
