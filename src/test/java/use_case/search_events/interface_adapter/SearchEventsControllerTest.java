package use_case.search_events.interface_adapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.search_events.SearchEventsInputBoundary;

import static org.mockito.Mockito.*;

public class SearchEventsControllerTest {

    private SearchEventsController searchEventsController;
    private SearchEventsInputBoundary searchEventsInteractor;

    @BeforeEach
    public void setUp() {
        searchEventsInteractor = mock(SearchEventsInputBoundary.class);
        searchEventsController = new SearchEventsController(searchEventsInteractor);
    }

    @Test
    public void testSearchEvents() {
        searchEventsController.searchEvents();
        verify(searchEventsInteractor, times(1)).searchForEvents();
    }
}