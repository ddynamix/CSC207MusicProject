package use_case.eventcrafter.interface_adapter;

import interface_adapter.ViewManagerModel;
import use_case.homescreen.interface_adapter.HomescreenState;
import use_case.homescreen.interface_adapter.HomescreenViewModel;
import use_case.eventcrafter.EventCrafterOutputBoundary;
import use_case.eventcrafter.EventCrafterOutputData;

import javax.swing.*;

public class EventCrafterPresenter implements EventCrafterOutputBoundary {
    private final HomescreenViewModel homescreenViewModel;
    private final ViewManagerModel viewManagerModel;

    public EventCrafterPresenter(HomescreenViewModel homescreenViewModel, ViewManagerModel viewManagerModel) {
        this.homescreenViewModel = homescreenViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(EventCrafterOutputData eventCrafterOutputData) {
        HomescreenState homescreenState = homescreenViewModel.getState();
        homescreenState.setEvents(eventCrafterOutputData.getEvents());
        homescreenViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(homescreenViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        JOptionPane.showMessageDialog(null, error);
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToHomescreen() {
        viewManagerModel.setActiveView(homescreenViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
