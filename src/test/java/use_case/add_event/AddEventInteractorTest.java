package use_case.add_event;

import app.interface_adapter_tools.UserSession;
import data_access.UsersEventsRelationalAccessInterface;
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

public class AddEventInteractorTest {

    private AddEventInteractor addEventInteractor;
    private AddEventOutputBoundary addEventPresenter;
    private UsersEventsRelationalAccessInterface usersEventsRelationalAccessInterface;
    private User user;

    @BeforeEach
    public void setUp() {
        addEventPresenter = mock(AddEventOutputBoundary.class);
        usersEventsRelationalAccessInterface = mock(UsersEventsRelationalAccessInterface.class);
        addEventInteractor = new AddEventInteractor(addEventPresenter, usersEventsRelationalAccessInterface);
        user = new AudienceUser("testUser", "Test User", "testUser", "testMail");
        UserSession.getInstance().setLoggedInUser(user);
    }

    @Test
    public void testAddEvent() {
        ArtistUser artist = new ArtistUser("testUser", "Test User", "testPass", "testMail");
        VenueUser venue = new VenueUser("testUser", "Test User", "testPass", "testMail");
        LocalDateTime testDate = LocalDateTime.now();
        ArrayList<String> testTags = new ArrayList<>();
        Event event = new Event("Test Event", artist, venue, testDate, "Test Description", testTags, testDate, "testMedia");        AddEventInputData inputData = new AddEventInputData(event);

        addEventInteractor.addEvent(inputData);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        ArgumentCaptor<Event> eventCaptor = ArgumentCaptor.forClass(Event.class);
        verify(usersEventsRelationalAccessInterface, times(1)).addEvent(userCaptor.capture(), eventCaptor.capture());

        assertEquals(user, userCaptor.getValue());
        assertEquals(event, eventCaptor.getValue());
    }

    @Test
    public void testRemoveEvent() {
        ArtistUser artist = new ArtistUser("testUser", "Test User", "testPass", "testMail");
        VenueUser venue = new VenueUser("testUser", "Test User", "testPass", "testMail");
        LocalDateTime testDate = LocalDateTime.now();
        ArrayList<String> testTags = new ArrayList<>();
        Event event = new Event("Test Event", artist, venue, testDate, "Test Description", testTags, testDate, "testMedia");
        AddEventInputData inputData = new AddEventInputData(event);
        user.addEvent(event);

        addEventInteractor.removeEvent(inputData);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        ArgumentCaptor<Event> eventCaptor = ArgumentCaptor.forClass(Event.class);
        verify(usersEventsRelationalAccessInterface, times(1)).removeEvent(userCaptor.capture(), eventCaptor.capture());

        assertEquals(user, userCaptor.getValue());
        assertEquals(event, eventCaptor.getValue());

        ArgumentCaptor<AddEventOutputData> outputDataCaptor = ArgumentCaptor.forClass(AddEventOutputData.class);
        verify(addEventPresenter, times(1)).updateEventsView(outputDataCaptor.capture());

        AddEventOutputData capturedOutputData = outputDataCaptor.getValue();
        assertEquals(new ArrayList<>(), capturedOutputData.getEventsToDisplay());
    }
}