package use_case.eventcrafter.interface_adapter;

import app.interface_adapter_tools.ViewManagerModel;
import view_model.EventScreenState;
import view_model.EventScreenViewModel;
import use_case.eventcrafter.EventCrafterOutputBoundary;
import use_case.eventcrafter.EventCrafterOutputData;

import javax.swing.*;

public class EventCrafterPresenter implements EventCrafterOutputBoundary {
    private final EventScreenViewModel eventScreenViewModel;
    private final ViewManagerModel viewManagerModel;

    public EventCrafterPresenter(EventScreenViewModel eventScreenViewModel, ViewManagerModel viewManagerModel) {
        this.eventScreenViewModel = eventScreenViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(EventCrafterOutputData eventCrafterOutputData) {
        EventScreenState eventScreenState = eventScreenViewModel.getState();
        eventScreenState.setEvents(eventCrafterOutputData.getEvents());

        eventScreenViewModel.setState(eventScreenState);
        eventScreenViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(eventScreenViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        viewManagerModel.firePropertyChanged();
    }
}
