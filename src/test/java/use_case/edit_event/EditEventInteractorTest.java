package use_case.edit_event;

import app.interface_adapter_tools.UserSession;
import data_access.EventDataAccessInterface;
import data_access.EventDoesntExistException;
import data_access.UsersEventsRelationalAccessInterface;
import entity.event.Event;
import entity.user.ArtistUser;
import entity.user.VenueUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EditEventInteractorTest {

    private EditEventInteractor editEventInteractor;
    private EditEventOutputBoundary editEventPresenter;
    private EventDataAccessInterface eventDataAccessInterface;
    private UsersEventsRelationalAccessInterface usersEventsRelationalAccessInterface;
    private ArtistUser artist;
    private VenueUser venue;
    private Event event;

    @BeforeEach
    public void setUp() {
        editEventPresenter = mock(EditEventOutputBoundary.class);
        eventDataAccessInterface = mock(EventDataAccessInterface.class);
        usersEventsRelationalAccessInterface = mock(UsersEventsRelationalAccessInterface.class);
        editEventInteractor = new EditEventInteractor(editEventPresenter, eventDataAccessInterface, usersEventsRelationalAccessInterface);

        artist = new ArtistUser("testUser", "Test User", "testPass", "testMail");
        venue = new VenueUser("testUser", "Test User", "testPass", "testMail");
        LocalDateTime testDate = LocalDateTime.now();
        ArrayList<String> testTags = new ArrayList<>();
        event = new Event("Test Event", artist, venue, testDate, "Test Description", testTags, testDate, "testMedia");

        UserSession.getInstance().setLoggedInUser(artist);
    }

    @Test
    public void testEditEvent() {
        EditEventInputData inputData = new EditEventInputData(event);

        editEventInteractor.editEvent(inputData);

        ArgumentCaptor<EditEventOutputData> outputDataCaptor = ArgumentCaptor.forClass(EditEventOutputData.class);
        verify(editEventPresenter, times(1)).goToEventEditor(outputDataCaptor.capture());

        EditEventOutputData capturedOutputData = outputDataCaptor.getValue();
        assertEquals(event, capturedOutputData.getGetEventToEdit());
    }

    @Test
    public void testDeleteEvent() throws EventDoesntExistException {
        EditEventInputData inputData = new EditEventInputData(event);
        artist.addEvent(event);

        editEventInteractor.deleteEvent(inputData);

        ArgumentCaptor<Event> eventCaptor = ArgumentCaptor.forClass(Event.class);
        verify(eventDataAccessInterface, times(1)).deleteEvent(eventCaptor.capture());
        verify(usersEventsRelationalAccessInterface, times(1)).removeEvent(eq(artist), eventCaptor.capture());

        assertEquals(event, eventCaptor.getValue());

        ArgumentCaptor<EditEventsSuccessOutputData> outputDataCaptor = ArgumentCaptor.forClass(EditEventsSuccessOutputData.class);
        verify(editEventPresenter, times(1)).prepareSuccessView(outputDataCaptor.capture());

        EditEventsSuccessOutputData capturedOutputData = outputDataCaptor.getValue();
        assertEquals(new ArrayList<>(), capturedOutputData.getEvents());
    }

    @Test
    public void testUpdateEvent() throws EventDoesntExistException {
        EditEventInputData inputData = new EditEventInputData(event, "Updated Title", "Updated Description", "2023-10-10", "tag1,tag2", "updatedMedia");

        editEventInteractor.updateEvent(inputData);

        ArgumentCaptor<Event> eventCaptor = ArgumentCaptor.forClass(Event.class);
        ArgumentCaptor<String> titleCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> descriptionCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> dateCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> tagsCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> mediaCaptor = ArgumentCaptor.forClass(String.class);

        verify(eventDataAccessInterface, times(1)).updateEvent(eventCaptor.capture(), titleCaptor.capture(), descriptionCaptor.capture(), dateCaptor.capture(), tagsCaptor.capture(), mediaCaptor.capture());

        assertEquals(event, eventCaptor.getValue());
        assertEquals("Updated Title", titleCaptor.getValue());
        assertEquals("Updated Description", descriptionCaptor.getValue());
        assertEquals("2023-10-10", dateCaptor.getValue());
        assertEquals("tag1,tag2", tagsCaptor.getValue());
        assertEquals("updatedMedia", mediaCaptor.getValue());

        ArgumentCaptor<EditEventsSuccessOutputData> outputDataCaptor = ArgumentCaptor.forClass(EditEventsSuccessOutputData.class);
        verify(editEventPresenter, times(1)).prepareSuccessView(outputDataCaptor.capture());

        EditEventsSuccessOutputData capturedOutputData = outputDataCaptor.getValue();
        assertEquals(new ArrayList<>(), capturedOutputData.getEvents());
    }
}