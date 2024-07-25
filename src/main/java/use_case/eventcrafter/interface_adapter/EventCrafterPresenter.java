package use_case.eventcrafter.interface_adapter;

import app.interface_adapter_tools.ViewManagerModel;
import use_case.eventscreen.interface_adapter.EventScreenState;
import use_case.eventscreen.interface_adapter.EventScreenViewModel;
import use_case.homescreen.interface_adapter.HomescreenState;
import use_case.homescreen.interface_adapter.HomescreenViewModel;
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
        JOptionPane.showMessageDialog(null, error);
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToHomescreen() {
        viewManagerModel.setActiveView(eventScreenViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
