package use_case.edit_event.interface_adapter;

import app.interface_adapter_tools.ViewManagerModel;
import entity.event.Event;
import entity.user.ArtistUser;
import entity.user.VenueUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_case.edit_event.EditEventOutputData;
import use_case.edit_event.EditEventsSuccessOutputData;
import view_model.EventEditorState;
import view_model.EventEditorViewModel;
import view_model.EventScreenState;
import view_model.EventScreenViewModel;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EditEventPresenterTest {

    private EditEventPresenter editEventPresenter;
    private ViewManagerModel viewManagerModel;
    private EventEditorViewModel eventEditorViewModel;
    private EventScreenViewModel eventScreenViewModel;

    @BeforeEach
    public void setUp() {
        viewManagerModel = mock(ViewManagerModel.class);
        eventEditorViewModel = mock(EventEditorViewModel.class);
        eventScreenViewModel = mock(EventScreenViewModel.class);
        editEventPresenter = new EditEventPresenter(viewManagerModel, eventEditorViewModel, eventScreenViewModel);
    }

    @Test
    public void testGoToEventEditor() {
        ArtistUser artist = new ArtistUser("testUser", "Test User", "testPass", "testMail");
        VenueUser venue = new VenueUser("testUser", "Test User", "testPass", "testMail");
        LocalDateTime testDate = LocalDateTime.now();
        ArrayList<String> testTags = new ArrayList<>();
        Event event = new Event("Test Event", artist, venue, testDate, "Test Description", testTags, testDate, "testMedia");
        EditEventOutputData outputData = new EditEventOutputData(event);

        EventEditorState eventEditorState = new EventEditorState();
        when(eventEditorViewModel.getState()).thenReturn(eventEditorState);

        editEventPresenter.goToEventEditor(outputData);

        ArgumentCaptor<EventEditorState> stateCaptor = ArgumentCaptor.forClass(EventEditorState.class);
        verify(eventEditorViewModel, times(1)).setState(stateCaptor.capture());
        verify(eventEditorViewModel, times(1)).firePropertyChanged();
        verify(viewManagerModel, times(1)).setActiveView("event editor");
        verify(viewManagerModel, times(1)).firePropertyChanged();

        EventEditorState capturedState = stateCaptor.getValue();
        assertEquals(outputData.getGetEventToEdit(), capturedState.getEventToEdit());
    }

    @Test
    public void testPrepareSuccessView() {
        ArtistUser artist = new ArtistUser("testUser", "Test User", "testPass", "testMail");
        VenueUser venue = new VenueUser("testUser", "Test User", "testPass", "testMail");
        LocalDateTime testDate = LocalDateTime.now();
        ArrayList<String> testTags = new ArrayList<>();
        Event event = new Event("Test Event", artist, venue, testDate, "Test Description", testTags, testDate, "testMedia");
        ArtistUser artist2 = new ArtistUser("testUser", "Test User", "testPass", "testMail");
        VenueUser venue2 = new VenueUser("testUser", "Test User", "testPass", "testMail");
        LocalDateTime testDate2 = LocalDateTime.now();
        ArrayList<String> testTags2 = new ArrayList<>();
        Event event2 = new Event("Test Event", artist, venue, testDate, "Test Description", testTags, testDate, "testMedia");
        ArrayList<Event> events = new ArrayList<>();
        events.add(event);
        events.add(event2);
        EditEventsSuccessOutputData outputData = new EditEventsSuccessOutputData(events);

        EventScreenState eventScreenState = new EventScreenState();
        when(eventScreenViewModel.getState()).thenReturn(eventScreenState);

        editEventPresenter.prepareSuccessView(outputData);

        ArgumentCaptor<EventScreenState> stateCaptor = ArgumentCaptor.forClass(EventScreenState.class);
        verify(eventScreenViewModel, times(1)).setState(stateCaptor.capture());
        verify(eventScreenViewModel, times(1)).firePropertyChanged();
        verify(viewManagerModel, times(1)).setActiveView("event screen");
        verify(viewManagerModel, times(1)).firePropertyChanged();

        EventScreenState capturedState = stateCaptor.getValue();
        assertEquals(outputData.getEvents(), capturedState.getEvents());
    }
}