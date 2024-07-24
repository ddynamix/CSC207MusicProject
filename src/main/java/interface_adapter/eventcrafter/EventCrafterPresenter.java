package interface_adapter.eventcrafter;

import interface_adapter.ViewManagerModel;
import interface_adapter.homescreen.HomescreenState;
import interface_adapter.homescreen.HomescreenViewModel;
import usecase.eventcrafter.EventCrafterOutputBoundary;
import usecase.eventcrafter.EventCrafterOutputData;

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
}
