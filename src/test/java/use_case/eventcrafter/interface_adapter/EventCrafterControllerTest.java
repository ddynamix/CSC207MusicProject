package use_case.eventcrafter.interface_adapter;

import data_access.UserDataAccessInterface;
import entity.user.ArtistUser;
import entity.user.VenueUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_case.eventcrafter.EventCrafterInputBoundary;
import use_case.eventcrafter.EventCrafterInputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EventCrafterControllerTest {

    private EventCrafterController eventCrafterController;
    private EventCrafterInputBoundary eventCrafterInteractor;
    private UserDataAccessInterface userDataAccessInterface;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @BeforeEach
    public void setUp() {
        eventCrafterInteractor = mock(EventCrafterInputBoundary.class);
        userDataAccessInterface = mock(UserDataAccessInterface.class);
        eventCrafterController = new EventCrafterController(eventCrafterInteractor, userDataAccessInterface);
    }

    @Test
    public void testExcecute() {
        String title = "Test Event";
        String artist = "testArtist";
        String venue = "testVenue";
        String dateAndTime = "2023-10-10 10:00";
        String description = "Test Description";
        String tags = "tag1;tag2";
        String postDate = "2023-10-01 10:00";
        String attachedMedia = "testMedia";

        LocalDateTime dateAndTimeFormatted = LocalDateTime.parse(dateAndTime, formatter);
        LocalDateTime postDateFormatted = LocalDateTime.parse(postDate, formatter);
        ArrayList<String> tagsFormatted = new ArrayList<>();
        tagsFormatted.add("tag1");
        tagsFormatted.add("tag2");

        ArtistUser artistUser = new ArtistUser("testArtist", "Test Artist", "testPass", "testMail");
        VenueUser venueUser = new VenueUser("testVenue", "Test Venue", "testPass", "testMail");

        when(userDataAccessInterface.getUserFromUsername(artist)).thenReturn(artistUser);
        when(userDataAccessInterface.getUserFromUsername(venue)).thenReturn(venueUser);

        eventCrafterController.excecute(title, artist, venue, dateAndTime, description, tags, postDate, attachedMedia);

        ArgumentCaptor<EventCrafterInputData> inputDataCaptor = ArgumentCaptor.forClass(EventCrafterInputData.class);
        verify(eventCrafterInteractor, times(1)).attemptPostEvent(inputDataCaptor.capture());

        EventCrafterInputData capturedInputData = inputDataCaptor.getValue();
        assertEquals(title, capturedInputData.getTitle());
        assertEquals(description, capturedInputData.getDescription());
        assertEquals(artistUser, capturedInputData.getArtist());
        assertEquals(venueUser, capturedInputData.getVenue());
        assertEquals(dateAndTimeFormatted, capturedInputData.getDateAndTime());
        assertEquals(tagsFormatted, capturedInputData.getTags());
        assertEquals(postDateFormatted, capturedInputData.getPostDate());
        assertEquals(attachedMedia, capturedInputData.getAttachedMedia());
    }
}