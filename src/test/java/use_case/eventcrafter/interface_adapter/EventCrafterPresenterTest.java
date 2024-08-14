package use_case.eventcrafter.interface_adapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_case.eventcrafter.EventCrafterOutputData;
import view_model.EventScreenState;
import view_model.EventScreenViewModel;
import app.interface_adapter_tools.ViewManagerModel;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EventCrafterPresenterTest {

    private EventCrafterPresenter eventCrafterPresenter;
    private EventScreenViewModel eventScreenViewModel;
    private ViewManagerModel viewManagerModel;

    @BeforeEach
    public void setUp() {
        eventScreenViewModel = mock(EventScreenViewModel.class);
        viewManagerModel = mock(ViewManagerModel.class);
        eventCrafterPresenter = new EventCrafterPresenter(eventScreenViewModel, viewManagerModel);
    }

    @Test
    public void testPrepareSuccessView() {
        System.out.println("Starting testPrepareSuccessView");
        EventCrafterOutputData outputData = new EventCrafterOutputData(new ArrayList<>());

        EventScreenState eventScreenState = new EventScreenState();
        when(eventScreenViewModel.getState()).thenReturn(eventScreenState);

        eventCrafterPresenter.prepareSuccessView(outputData);

        ArgumentCaptor<EventScreenState> stateCaptor = ArgumentCaptor.forClass(EventScreenState.class);
        verify(eventScreenViewModel, times(1)).setState(stateCaptor.capture());
        verify(eventScreenViewModel, times(1)).firePropertyChanged();
        verify(viewManagerModel, times(1)).setActiveView(eventScreenViewModel.getViewName());
        verify(viewManagerModel, times(1)).firePropertyChanged();

        EventScreenState capturedState = stateCaptor.getValue();
        assertEquals(outputData.getEvents(), capturedState.getEvents());
        System.out.println("Finished testPrepareSuccessView");
    }

    @Test
    public void testPrepareFailView() {
        System.out.println("Starting testPrepareFailView");
        String error = "Test Error";

        eventCrafterPresenter.prepareFailView(error);

        verify(viewManagerModel, times(1)).firePropertyChanged();
        System.out.println("Finished testPrepareFailView");
    }
}
