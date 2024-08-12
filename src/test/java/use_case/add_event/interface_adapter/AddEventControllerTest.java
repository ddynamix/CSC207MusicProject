package use_case.add_event.interface_adapter;

import entity.event.Event;
import entity.user.ArtistUser;
import entity.user.VenueUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_case.add_event.AddEventInputBoundary;
import use_case.add_event.AddEventInputData;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AddEventControllerTest {

    private AddEventController addEventController;
    private AddEventInputBoundary addEventInteractor;

    @BeforeEach
    public void setUp() {
        addEventInteractor = mock(AddEventInputBoundary.class);
        addEventController = new AddEventController(addEventInteractor);
    }

    @Test
    public void testAddEvent() {
        ArtistUser artist = new ArtistUser("testUser", "Test User", "testPass", "testMail");
        VenueUser venue = new VenueUser("testUser", "Test User", "testPass", "testMail");
        LocalDateTime testDate = LocalDateTime.now();
        ArrayList<String> testTags = new ArrayList<>();
        Event event = new Event("Test Event", artist, venue, testDate, "Test Description", testTags, testDate, "testMedia");
        addEventController.addEvent(event);

        ArgumentCaptor<AddEventInputData> inputDataCaptor = ArgumentCaptor.forClass(AddEventInputData.class);
        verify(addEventInteractor, times(1)).addEvent(inputDataCaptor.capture());

        AddEventInputData capturedInputData = inputDataCaptor.getValue();
        assertEquals(event, capturedInputData.getEventToAddOrRemove());
    }

    @Test
    public void testRemoveEvent() {
        ArtistUser artist = new ArtistUser("testUser", "Test User", "testPass", "testMail");
        VenueUser venue = new VenueUser("testUser", "Test User", "testPass", "testMail");
        LocalDateTime testDate = LocalDateTime.now();
        ArrayList<String> testTags = new ArrayList<>();
        Event event = new Event("Test Event", artist, venue, testDate, "Test Description", testTags, testDate, "testMedia");
        addEventController.removeEvent(event);

        ArgumentCaptor<AddEventInputData> inputDataCaptor = ArgumentCaptor.forClass(AddEventInputData.class);
        verify(addEventInteractor, times(1)).removeEvent(inputDataCaptor.capture());

        AddEventInputData capturedInputData = inputDataCaptor.getValue();
        assertEquals(event, capturedInputData.getEventToAddOrRemove());
    }
}