package use_case.edit_event.interface_adapter;

import entity.event.Event;
import entity.user.ArtistUser;
import entity.user.VenueUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_case.edit_event.EditEventInputBoundary;
import use_case.edit_event.EditEventInputData;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EditEventControllerTest {

    private EditEventController editEventController;
    private EditEventInputBoundary editEventInteractor;

    @BeforeEach
    public void setUp() {
        editEventInteractor = mock(EditEventInputBoundary.class);
        editEventController = new EditEventController(editEventInteractor);
    }

    @Test
    public void testEditEvent() {
        ArtistUser artist = new ArtistUser("testUser", "Test User", "testPass", "testMail");
        VenueUser venue = new VenueUser("testUser", "Test User", "testPass", "testMail");
        LocalDateTime testDate = LocalDateTime.now();
        ArrayList<String> testTags = new ArrayList<>();
        Event event = new Event("Test Event", artist, venue, testDate, "Test Description", testTags, testDate, "testMedia");        editEventController.editEvent(event);

        ArgumentCaptor<EditEventInputData> inputDataCaptor = ArgumentCaptor.forClass(EditEventInputData.class);
        verify(editEventInteractor, times(1)).editEvent(inputDataCaptor.capture());

        EditEventInputData capturedInputData = inputDataCaptor.getValue();
        assertEquals(event, capturedInputData.getEventToAlter());
    }

    @Test
    public void testDeleteEvent() {
        ArtistUser artist = new ArtistUser("testUser", "Test User", "testPass", "testMail");
        VenueUser venue = new VenueUser("testUser", "Test User", "testPass", "testMail");
        LocalDateTime testDate = LocalDateTime.now();
        ArrayList<String> testTags = new ArrayList<>();
        Event event = new Event("Test Event", artist, venue, testDate, "Test Description", testTags, testDate, "testMedia");        editEventController.deleteEvent(event);

        ArgumentCaptor<EditEventInputData> inputDataCaptor = ArgumentCaptor.forClass(EditEventInputData.class);
        verify(editEventInteractor, times(1)).deleteEvent(inputDataCaptor.capture());

        EditEventInputData capturedInputData = inputDataCaptor.getValue();
        assertEquals(event, capturedInputData.getEventToAlter());
    }

    @Test
    public void testUpdateEvent() {
        ArtistUser artist = new ArtistUser("testUser", "Test User", "testPass", "testMail");
        VenueUser venue = new VenueUser("testUser", "Test User", "testPass", "testMail");
        LocalDateTime testDate = LocalDateTime.now();
        ArrayList<String> testTags = new ArrayList<>();
        Event event = new Event("Test Event", artist, venue, testDate, "Test Description", testTags, testDate, "testMedia");        String title = "Updated Title";
        String description = "Updated Description";
        String date = "2023-10-10";
        String tags = "tag1,tag2";
        String media = "updatedMedia";

        editEventController.updateEvent(event, title, description, date, tags, media);

        ArgumentCaptor<EditEventInputData> inputDataCaptor = ArgumentCaptor.forClass(EditEventInputData.class);
        verify(editEventInteractor, times(1)).updateEvent(inputDataCaptor.capture());

        EditEventInputData capturedInputData = inputDataCaptor.getValue();
        assertEquals(event, capturedInputData.getEventToAlter());
        assertEquals(title, capturedInputData.getTitle());
        assertEquals(description, capturedInputData.getDescription());
        assertEquals(date, capturedInputData.getDate());
        assertEquals(tags, capturedInputData.getTags());
        assertEquals(media, capturedInputData.getMedia());
    }
}