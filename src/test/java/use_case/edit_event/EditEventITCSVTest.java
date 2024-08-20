package use_case.edit_event;

import app.interface_adapter_tools.UserSession;
import app.interface_adapter_tools.ViewManagerModel;
import data_access.EventDataAccessInterface;
import data_access.UserDataAccessInterface;
import data_access.UsersEventsRelationalAccessInterface;
import data_access.csv.EventLocalCSVDataStorage;
import data_access.csv.UserLocalCSVDataStorage;
import data_access.csv.UsersEventsRelationalCSVDataStorage;
import entity.event.Event;
import entity.user.ArtistUser;
import entity.user.AudienceUser;
import entity.user.VenueUser;
import org.junit.jupiter.api.*;
import use_case.edit_event.interface_adapter.EditEventController;
import use_case.edit_event.interface_adapter.EditEventPresenter;
import view_model.EventEditorViewModel;
import view_model.EventScreenViewModel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class EditEventITCSVTest {

    private EditEventController editEventController;
    private EventLocalCSVDataStorage eventStorage;
    private EditEventPresenter editEventPresenter;
    private EventScreenViewModel eventScreenViewModel;
    private EventEditorViewModel eventEditorViewModel;
     EventDataAccessInterface eventDataAccess;
    private ViewManagerModel viewManagerModel;
    private File eventFile;
    private File relationalEventsFile;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    @BeforeEach
    public void setUp() throws IOException {
        File userDataFile = Files.createTempFile("test-user", ".csv").toFile();
        eventFile = Files.createTempFile("test-event", ".csv").toFile();
        relationalEventsFile = Files.createTempFile("test-relational", ".csv").toFile();

        UserDataAccessInterface userDataAccess = new UserLocalCSVDataStorage(userDataFile.getAbsolutePath());
        eventDataAccess = new EventLocalCSVDataStorage(eventFile.getAbsolutePath(), userDataAccess);
        UsersEventsRelationalAccessInterface relationalEventsStorage = new UsersEventsRelationalCSVDataStorage(relationalEventsFile.getAbsolutePath(), userDataAccess, eventDataAccess);
        eventEditorViewModel = new EventEditorViewModel();
        eventScreenViewModel = new EventScreenViewModel();
        viewManagerModel = new ViewManagerModel();
        editEventPresenter = new EditEventPresenter(viewManagerModel, eventEditorViewModel, eventScreenViewModel);
        EditEventInteractor editEventInteractor = new EditEventInteractor(editEventPresenter, eventDataAccess, relationalEventsStorage);
        editEventController = new EditEventController(editEventInteractor);

        UserSession.getInstance().setLoggedInUser(new AudienceUser("Test User", "testUser", "password", "test@email.com"));
    }

    @AfterEach
    public void tearDown() {
        eventFile.delete();
    }

    @Test
    public void testEditEvent() throws IOException {
        ArtistUser artistUser = new ArtistUser("Test Artist", "testArtist", "password", "test@email.com");
        VenueUser venueUser = new VenueUser("Test Venue", "testVenue", "password", "test@email.com");
        LocalDateTime dateAndTime = LocalDateTime.now();
        ArrayList<String> tags = new ArrayList<>(Arrays.asList("tag1", "tag2"));
        LocalDateTime postDate = LocalDateTime.now();
        Event event = new Event("Test Event", artistUser, venueUser, dateAndTime, "testDesc", tags, postDate, "testMedia");
        try {
            eventDataAccess.createEvent(event);
        } catch (Exception e) {
            e.printStackTrace();
        }

        editEventController.updateEvent(event, "Edited Event", "Edited Desc", "2023-10-06 20:00", "editedTag", "Edited Media");

        String fileContent = new String(Files.readAllBytes(eventFile.toPath()));
        System.out.print(fileContent);
        String nowString = LocalDateTime.now().format(formatter);
        assertTrue(fileContent.contains("Edited Event,testArtist,testVenue,2023-10-06 20:00,Edited Desc,editedTag," + nowString + ",Edited Media"));

        //assertEquals(1, eventScreenViewModel.getState().getEvents().size());
        //assertEquals("Edited Desc", eventScreenViewModel.getState().getEvents().get(0).getDescription());
    }
}
