package use_case.search_events;

import data_access.EventDataAccessInterface;
import entity.event.Event;
import entity.user.ArtistUser;
import entity.user.VenueUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class SearchEventsInteractorTest {

    private SearchEventsInteractor searchEventsInteractor;
    private SearchEventsOutputBoundary searchEventsPresenter;
    private EventDataAccessInterface eventDataAccess;

    @BeforeEach
    public void setUp() {
        searchEventsPresenter = mock(SearchEventsOutputBoundary.class);
        eventDataAccess = mock(EventDataAccessInterface.class);
        searchEventsInteractor = new SearchEventsInteractor(searchEventsPresenter, eventDataAccess);
    }

    @Test
    public void testSearchForEvents() {
        ArtistUser artist = new ArtistUser("testUser", "Test User", "testPass", "testMail");
        VenueUser venue = new VenueUser("testUser", "Test User", "testPass", "testMail");
        LocalDateTime testDate = LocalDateTime.now();
        ArrayList<String> testTags = new ArrayList<>();
        Event event1 = new Event("Test Event", artist, venue, testDate, "Test Description", testTags, testDate, "testMedia");
        Event event2 = new Event("Test Event 2", artist, venue, testDate, "Test Description 2", testTags, testDate, "testMedia 2");
        ArrayList<Event> allEvents = new ArrayList<>();
        allEvents.add(event1);
        allEvents.add(event2);

        when(eventDataAccess.getEvents()).thenReturn(allEvents);

        searchEventsInteractor.searchForEvents();

        ArgumentCaptor<SearchEventsOutputData> argumentCaptor = ArgumentCaptor.forClass(SearchEventsOutputData.class);
        verify(searchEventsPresenter, times(1)).updateDisplayedEvents(argumentCaptor.capture());

        SearchEventsOutputData actualOutputData = argumentCaptor.getValue();
        SearchEventsOutputData expectedOutputData = new SearchEventsOutputData(allEvents);

        assertTrue(fuzzyMatch(expectedOutputData, actualOutputData));
    }

    private boolean fuzzyMatch(SearchEventsOutputData expected, SearchEventsOutputData actual) {
        if (expected.getAllEvents().size() != actual.getAllEvents().size()) {
            return false;
        } else if (!expected.getAllEvents().get(0).getTitle().equals(actual.getAllEvents().get(0).getTitle())) {
            return false;
        }

        return true;
    }
}
