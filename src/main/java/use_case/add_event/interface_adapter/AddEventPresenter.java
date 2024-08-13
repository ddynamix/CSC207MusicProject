package use_case.add_event.interface_adapter;

import app.interface_adapter_tools.ViewManagerModel;
import use_case.add_event.AddEventOutputBoundary;
import use_case.add_event.AddEventOutputData;
import view_model.EventScreenState;
import view_model.EventScreenViewModel;

/**
 * Presenter for add event use case
 */
public class AddEventPresenter implements AddEventOutputBoundary {
    private final EventScreenViewModel eventScreenViewModel;

    /**
     * create instance of presenter
     * @param eventScreenViewModel model for event screen
     */
    public AddEventPresenter(EventScreenViewModel eventScreenViewModel) {
        this.eventScreenViewModel = eventScreenViewModel;
    }

    /**
     * update view
     * @param outputData new values
     */
    @Override
    public void updateEventsView(AddEventOutputData outputData) {
        EventScreenState eventScreenState = eventScreenViewModel.getState();
        eventScreenState.setEvents(outputData.getEventsToDisplay());
        eventScreenViewModel.setState(eventScreenState);
        eventScreenViewModel.firePropertyChanged();
    }
}
