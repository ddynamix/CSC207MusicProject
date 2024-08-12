package use_case.search_events.interface_adapter;

import entity.event.Event;
import entity.user.ArtistUser;
import entity.user.VenueUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.search_events.SearchEventsOutputData;
import view_model.SearchEventsState;
import view_model.SearchEventsViewModel;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SearchEventsPresenterTest {

    private SearchEventsPresenter searchEventsPresenter;
    private SearchEventsViewModel searchEventsViewModel;

    @BeforeEach
    public void setUp() {
        searchEventsViewModel = mock(SearchEventsViewModel.class);
        searchEventsPresenter = new SearchEventsPresenter(searchEventsViewModel);
    }

    @Test
    public void testUpdateDisplayedEvents() {
        ArtistUser artist = new ArtistUser("testUser", "Test User", "testPass", "testMail");
        VenueUser venue = new VenueUser("testUser", "Test User", "testPass", "testMail");
        LocalDateTime testDate = LocalDateTime.now();
        ArrayList<String> testTags = new ArrayList<>();
        Event event1 = new Event("Test Event", artist, venue, testDate, "Test Description", testTags, testDate, "testMedia");
        Event event2 = new Event("Test Event 2", artist, venue, testDate, "Test Description 2", testTags, testDate, "testMedia 2");
        ArrayList<Event> allEvents = new ArrayList<>();
        allEvents.add(event1);
        allEvents.add(event2);

        SearchEventsOutputData outputData = new SearchEventsOutputData(allEvents);
        SearchEventsState state = new SearchEventsState();
        when(searchEventsViewModel.getState()).thenReturn(state);

        searchEventsPresenter.updateDisplayedEvents(outputData);

        verify(searchEventsViewModel, times(1)).setState(state);
        verify(searchEventsViewModel, times(1)).firePropertyChanged();
        assertEquals(outputData.getAllEvents(), state.getEventsToDisplay());
    }
}