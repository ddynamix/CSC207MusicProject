package use_case.add_event;

import app.interface_adapter_tools.UserSession;
import data_access.EventDataAccessInterface;
import data_access.UserDataAccessInterface;
import data_access.csv.EventLocalCSVDataStorage;
import data_access.csv.UserLocalCSVDataStorage;
import data_access.csv.UsersEventsRelationalCSVDataStorage;
import entity.event.Event;
import entity.user.ArtistUser;
import entity.user.AudienceUser;
import entity.user.User;
import entity.user.VenueUser;
import org.junit.jupiter.api.*;
import use_case.add_event.interface_adapter.AddEventController;
import use_case.add_event.interface_adapter.AddEventPresenter;
import view_model.EventScreenViewModel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AddEventITCSV {

    private AddEventController addEventController;
    private UsersEventsRelationalCSVDataStorage relationalEventsStorage;
    private AddEventPresenter addEventPresenter;
    private EventScreenViewModel eventScreenViewModel;
    private File relationalEventsFile;

    @BeforeEach
    public void setUp() throws IOException {
        // Create temp files
        File userDataFile = Files.createTempFile("test-user", ".csv").toFile();
        File eventDataFile = Files.createTempFile("test-event", ".csv").toFile();
        relationalEventsFile = Files.createTempFile("test-relational", ".csv").toFile();

        // Create data access objects
        UserDataAccessInterface userDataAccess = new UserLocalCSVDataStorage(userDataFile.getAbsolutePath());
        EventDataAccessInterface eventDataAccess = new EventLocalCSVDataStorage(eventDataFile.getAbsolutePath(), userDataAccess);
        relationalEventsStorage = new UsersEventsRelationalCSVDataStorage(relationalEventsFile.getAbsolutePath(), userDataAccess, eventDataAccess);
        eventScreenViewModel = new EventScreenViewModel();
        addEventPresenter = new AddEventPresenter(eventScreenViewModel);
        AddEventInteractor addEventInteractor = new AddEventInteractor(addEventPresenter, relationalEventsStorage);
        addEventController = new AddEventController(addEventInteractor);

        // Set up a logged-in user
        UserSession.getInstance().setLoggedInUser(new AudienceUser("testUser", "password", "test@example.com", "Test User"));
    }

    @AfterEach
    public void tearDown() {
        // Delete the temporary file
        relationalEventsFile.delete();
    }

    @Test
    public void testAddEvent() throws IOException {
        ArtistUser artist = new ArtistUser("testUser", "Test User", "testPass", "testMail");
        VenueUser venue = new VenueUser("testUser", "Test User", "testPass", "testMail");
        LocalDateTime testDate = LocalDateTime.now();
        ArrayList<String> testTags = new ArrayList<>();
        Event event = new Event("Test Event", artist, venue, testDate, "Test Description", testTags, testDate, "testMedia");
        addEventController.addEvent(event);

        // Read the contents of the CSV file
        String fileContent = new String(Files.readAllBytes(relationalEventsFile.toPath()));

        // Verify that the event was added to the CSV file
        assertTrue(fileContent.contains("testUser,Test Event"));

        // Verify that the presenter updated the view model
        assertEquals(1, eventScreenViewModel.getState().getEvents().size());
        assertEquals("Test Event", eventScreenViewModel.getState().getEvents().get(0).getTitle());
    }

    @Test
    public void testRemoveEvent() throws IOException {
        ArtistUser artist = new ArtistUser("testUser", "Test User", "testPass", "testMail");
        VenueUser venue = new VenueUser("testUser", "Test User", "testPass", "testMail");
        LocalDateTime testDate = LocalDateTime.now();
        ArrayList<String> testTags = new ArrayList<>();
        Event event = new Event("Test Event", artist, venue, testDate, "Test Description", testTags, testDate, "testMedia");        addEventController.addEvent(event);
        addEventController.removeEvent(event);

        // Read the contents of the CSV file
        String fileContent = new String(Files.readAllBytes(relationalEventsFile.toPath()));

        // Verify that the event was added to the CSV file
        assertTrue(fileContent.contains("testUser,Test Event"));

        // Verify that the presenter updated the view model
        assertTrue(eventScreenViewModel.getState().getEvents().isEmpty());
    }
}