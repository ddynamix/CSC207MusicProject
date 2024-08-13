package use_case.add_event.interface_adapter;

import entity.event.Event;
import entity.user.ArtistUser;
import entity.user.VenueUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_case.add_event.AddEventOutputData;
import view_model.EventScreenState;
import view_model.EventScreenViewModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AddEventPresenterTest {

    private AddEventPresenter addEventPresenter;
    private EventScreenViewModel eventScreenViewModel;

    @BeforeEach
    public void setUp() {
        eventScreenViewModel = mock(EventScreenViewModel.class);
        addEventPresenter = new AddEventPresenter(eventScreenViewModel);
    }

    @Test
    public void testUpdateEventsView() {
        ArtistUser artist = new ArtistUser("testUser", "Test User", "testPass", "testMail");
        VenueUser venue = new VenueUser("testUser", "Test User", "testPass", "testMail");
        LocalDateTime testDate = LocalDateTime.now();
        ArrayList<String> testTags = new ArrayList<>();
        Event event1 = new Event("Test Event", artist, venue, testDate, "Test Description", testTags, testDate, "testMedia");
        Event event2 = new Event("Test Event 2", artist, venue, testDate, "Test Description", testTags, testDate, "testMedia");
        ArrayList<Event> testEvents = new ArrayList<>();
        testEvents.add(event1);
        testEvents.add(event2);
        AddEventOutputData outputData = new AddEventOutputData(testEvents);

        EventScreenState eventScreenState = new EventScreenState();
        when(eventScreenViewModel.getState()).thenReturn(eventScreenState);

        addEventPresenter.updateEventsView(outputData);

        ArgumentCaptor<EventScreenState> stateCaptor = ArgumentCaptor.forClass(EventScreenState.class);
        verify(eventScreenViewModel, times(1)).setState(stateCaptor.capture());
        verify(eventScreenViewModel, times(1)).firePropertyChanged();

        EventScreenState capturedState = stateCaptor.getValue();
        assertEquals(outputData.getEventsToDisplay(), capturedState.getEvents());
    }
}