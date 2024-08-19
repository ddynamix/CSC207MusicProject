package use_case.eventcrafter.interface_adapter;

import app.interface_adapter_tools.ViewManagerModel;
import view_model.EventScreenState;
import view_model.EventScreenViewModel;
import use_case.eventcrafter.EventCrafterOutputBoundary;
import use_case.eventcrafter.EventCrafterOutputData;

/**
 * present for event use case
 */
public class EventCrafterPresenter implements EventCrafterOutputBoundary {
    private final EventScreenViewModel eventScreenViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * create instance of present for event use case
     * @param eventScreenViewModel model for event screen
     * @param viewManagerModel manager for changing models
     */
    public EventCrafterPresenter(EventScreenViewModel eventScreenViewModel, ViewManagerModel viewManagerModel) {
        this.eventScreenViewModel = eventScreenViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * create view of event screen
     * @param eventCrafterOutputData data for screen
     */
    @Override
    public void prepareSuccessView(EventCrafterOutputData eventCrafterOutputData) {
        EventScreenState eventScreenState = eventScreenViewModel.getState();
        eventScreenState.setEvents(eventCrafterOutputData.getEvents());

        eventScreenViewModel.setState(eventScreenState);
        eventScreenViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(eventScreenViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * create dialogue for error
     * @param error custom message
     */
    @Override
    public void prepareFailView(String error) {
        viewManagerModel.firePropertyChanged();
    }
}
