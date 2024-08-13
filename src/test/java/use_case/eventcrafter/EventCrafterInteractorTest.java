package use_case.eventcrafter;

import app.interface_adapter_tools.UserSession;
import com.wrapper.spotify.model_objects.specification.Artist;
import data_access.EventAlreadyExistsException;
import data_access.EventDataAccessInterface;
import entity.event.Event;
import entity.user.ArtistUser;
import entity.user.AudienceUser;
import entity.user.User;
import entity.user.VenueUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EventCrafterInteractorTest {

    private EventCrafterInteractor eventCrafterInteractor;
    private EventDataAccessInterface eventDataAccessInterface;
    private EventCrafterOutputBoundary eventCrafterPresenter;
    private User user;
    private ArtistUser artistUser;
    private VenueUser venueUser;

    @BeforeEach
    public void setUp() {
        eventDataAccessInterface = mock(EventDataAccessInterface.class);
        eventCrafterPresenter = mock(EventCrafterOutputBoundary.class);
        eventCrafterInteractor = new EventCrafterInteractor(eventDataAccessInterface, eventCrafterPresenter);

        user = new AudienceUser("testUser", "Test User", "testPass", "testMail");
        UserSession.getInstance().setLoggedInUser(user);

        artistUser = new ArtistUser("testArtist", "Test Artist", "testPass", "testMail");
        venueUser = new VenueUser("testVenue", "Test Venue", "testPass", "testMail");
    }

    @Test
    public void testAttemptPostEvent_Success() throws EventAlreadyExistsException {
        EventCrafterInputData inputData = new EventCrafterInputData(
                "Test Event",
                "Test desc",
                artistUser,
                venueUser,
                LocalDateTime.now(),
                null,
                LocalDateTime.now(),
                "testMedia"
        );

        eventCrafterInteractor.attemptPostEvent(inputData);

        ArgumentCaptor<Event> eventCaptor = ArgumentCaptor.forClass(Event.class);
        verify(eventDataAccessInterface, times(1)).createEvent(eventCaptor.capture());

        Event capturedEvent = eventCaptor.getValue();
        assertEquals(inputData.getTitle(), capturedEvent.getTitle());
        assertEquals(inputData.getArtist(), capturedEvent.getArtist());
        assertEquals(inputData.getVenue(), capturedEvent.getVenue());
        assertEquals(inputData.getDateAndTime(), capturedEvent.getDateAndTime());
        assertEquals(inputData.getDescription(), capturedEvent.getDescription());
        assertEquals(inputData.getTags(), capturedEvent.getTags());
        assertEquals(inputData.getPostDate(), capturedEvent.getPostDate());
        assertEquals(inputData.getAttachedMedia(), capturedEvent.getAttachedMedia());

        ArgumentCaptor<EventCrafterOutputData> outputDataCaptor = ArgumentCaptor.forClass(EventCrafterOutputData.class);
        verify(eventCrafterPresenter, times(1)).prepareSuccessView(outputDataCaptor.capture());

        EventCrafterOutputData capturedOutputData = outputDataCaptor.getValue();
        assertEquals(user.getMyEvents(), capturedOutputData.getEvents());
    }

    @Test
    public void testAttemptPostEvent_EventAlreadyExists() throws EventAlreadyExistsException {
        EventCrafterInputData inputData = new EventCrafterInputData(
                "Test Event",
                "Test desc",
                artistUser,
                venueUser,
                LocalDateTime.now(),
                null,
                LocalDateTime.now(),
                "testMedia"
        );

        doThrow(new EventAlreadyExistsException()).when(eventDataAccessInterface).createEvent(any(Event.class));

        eventCrafterInteractor.attemptPostEvent(inputData);

        verify(eventCrafterPresenter, times(1)).prepareFailView("Event already exists.");
    }
}