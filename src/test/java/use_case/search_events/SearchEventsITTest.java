package use_case.search_events;

import app.interface_adapter_tools.UserSession;
import data_access.EventDataAccessInterface;
import data_access.UserDataAccessInterface;
import data_access.csv.EventLocalCSVDataStorage;
import data_access.csv.UserLocalCSVDataStorage;
import entity.event.Event;
import entity.user.ArtistUser;
import entity.user.AudienceUser;
import entity.user.VenueUser;
import org.junit.jupiter.api.*;
import use_case.search_events.interface_adapter.SearchEventsController;
import use_case.search_events.interface_adapter.SearchEventsPresenter;
import view_model.SearchEventsViewModel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SearchEventsITTest {

    private SearchEventsController searchEventsController;
    private EventDataAccessInterface eventStorage;
    private SearchEventsPresenter searchEventsPresenter;
    private SearchEventsViewModel searchEventsViewModel;
    private File eventFile;

    @BeforeEach
    public void setUp() throws IOException {
        File userDataFile = Files.createTempFile("test-user", ".csv").toFile();
        eventFile = Files.createTempFile("test-event", ".csv").toFile();

        UserDataAccessInterface userDataAccess = new UserLocalCSVDataStorage(userDataFile.getAbsolutePath());
        eventStorage = new EventLocalCSVDataStorage(eventFile.getAbsolutePath(), userDataAccess);
        searchEventsViewModel = new SearchEventsViewModel();
        searchEventsPresenter = new SearchEventsPresenter(searchEventsViewModel);
        SearchEventsInteractor searchEventsInteractor = new SearchEventsInteractor(searchEventsPresenter, eventStorage);
        searchEventsController = new SearchEventsController(searchEventsInteractor);

        UserSession.getInstance().setLoggedInUser(new AudienceUser("testUser", "testUser", "password", "test@email.com"));
    }

    @AfterEach
    public void tearDown() {
        eventFile.delete();
    }

    @Test
    public void testSearchEvents() throws IOException {
        ArtistUser artistUser = new ArtistUser("Test Artist", "testArtist", "password", "test@email.com");
        VenueUser venueUser = new VenueUser("Test Venue", "testVenue", "password", "test@email.com");
        LocalDateTime dateAndTime = LocalDateTime.now();
        ArrayList<String> tags = new ArrayList<>(Arrays.asList("tag1", "tag2"));
        LocalDateTime postDate = LocalDateTime.now();
        Event event1 = new Event("Test Event 1", artistUser, venueUser, dateAndTime, "testDesc", tags, postDate, "testMedia");
        Event event2 = new Event("Test Event 2", artistUser, venueUser, dateAndTime, "testDesc", tags, postDate, "testMedia");
        try {
            eventStorage.createEvent(event1);
            eventStorage.createEvent(event2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        searchEventsController.searchEvents();

        List<Event> events = searchEventsViewModel.getState().getEventsToDisplay();
        assertEquals(2, events.size());
        assertEquals("Test Event 2", events.get(0).getTitle());
        assertEquals("Test Event 1", events.get(1).getTitle());
    }
}
