package use_case.eventcrafter;

import app.interface_adapter_tools.UserSession;
import app.interface_adapter_tools.ViewManagerModel;
import data_access.EventDataAccessInterface;
import data_access.UserDataAccessInterface;
import data_access.csv.EventLocalCSVDataStorage;
import data_access.csv.UserLocalCSVDataStorage;
import entity.user.ArtistUser;
import entity.user.AudienceUser;
import org.junit.jupiter.api.*;
import use_case.eventcrafter.EventCrafterInteractor;
import use_case.eventcrafter.interface_adapter.EventCrafterController;
import use_case.eventcrafter.interface_adapter.EventCrafterPresenter;
import use_case.usersignup.SignupOutputBoundary;
import use_case.usersignup.UserSignupInputBoundary;
import use_case.usersignup.UserSignupInteractor;
import use_case.usersignup.interface_adapter.UserSignupController;
import use_case.usersignup.interface_adapter.UserSignupPresenter;
import view_model.EventScreenViewModel;
import view_model.LoginViewModel;
import view_model.SplashViewModel;
import view_model.UserSignupViewModel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

public class EventCrafterITCSVTest {

    private EventCrafterController eventCrafterController;
    private EventDataAccessInterface eventStorage;
    private EventCrafterPresenter eventCrafterPresenter;
    private EventScreenViewModel eventScreenViewModel;
    private File eventFile;

    @BeforeEach
    public void setUp() throws IOException {
        File userDataFile = Files.createTempFile("test-user", ".csv").toFile();
        eventFile = Files.createTempFile("test-event", ".csv").toFile();

        UserDataAccessInterface userDataAccess = new UserLocalCSVDataStorage(userDataFile.getAbsolutePath());
        eventStorage = new EventLocalCSVDataStorage(eventFile.getAbsolutePath(), userDataAccess);
        eventScreenViewModel = new EventScreenViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        eventCrafterPresenter = new EventCrafterPresenter(eventScreenViewModel, viewManagerModel);
        EventCrafterInteractor eventCrafterInteractor = new EventCrafterInteractor(eventStorage, eventCrafterPresenter);
        eventCrafterController = new EventCrafterController(eventCrafterInteractor, userDataAccess);

        SignupOutputBoundary userSignupPresenter = new UserSignupPresenter(viewManagerModel, new UserSignupViewModel(), new SplashViewModel(), new LoginViewModel());
        UserSignupInputBoundary userSignupInteractor = new UserSignupInteractor(userDataAccess, userSignupPresenter);
        UserSignupController userSignupController = new UserSignupController(userSignupInteractor);

        userSignupController.execute("Artist", "testArtist", "password", "password", "test@email.com", "Artist");
        userSignupController.execute("Venue", "testVenue", "password", "password", "test@email.com", "Venue");

        UserSession.getInstance().setLoggedInUser(userDataAccess.getUserFromUsername("testArtist"));
    }

    @AfterEach
    public void tearDown() {
        eventFile.delete();
    }

    @Test
    public void testCreateEvent() throws IOException {
        eventCrafterController.excecute("Test Event", "testArtist", "testVenue", "2024-10-10 20:00", "testDesc", "testTag", "2024-10-10 20:00", "testMedia");

        String fileContent = new String(Files.readAllBytes(eventFile.toPath()));

        assertTrue(fileContent.contains("Test Event,testArtist,testVenue,2024-10-10 20:00,testTag,testDesc,2024-10-10 20:00,testMedia"));

        //assertEquals(1, eventScreenViewModel.getState().getEvents().size());
        //assertEquals("Test Event", eventScreenViewModel.getState().getEvents().get(0).getTitle());
    }
}
