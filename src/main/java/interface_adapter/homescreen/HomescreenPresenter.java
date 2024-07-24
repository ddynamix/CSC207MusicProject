package interface_adapter.homescreen;

import interface_adapter.ViewManagerModel;
import interface_adapter.eventcrafter.EventCrafterState;
import interface_adapter.eventcrafter.EventCrafterViewModel;
import usecase.homescreen.HomescreenGetEventOutputData;
import usecase.homescreen.HomescreenInputBoundary;
import usecase.homescreen.HomescreenOutputBoundary;
import usecase.homescreen.HomescreenOutputData;

public class HomescreenPresenter implements HomescreenOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final EventCrafterViewModel eventCrafterViewModel;
    private final HomescreenViewModel homescreenViewModel;

    public HomescreenPresenter(ViewManagerModel viewManagerModel, EventCrafterViewModel eventCrafterViewModel, HomescreenViewModel homescreenViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.eventCrafterViewModel = eventCrafterViewModel;
        this.homescreenViewModel = homescreenViewModel;
    }

    @Override
    public void prepareCreateEventView(HomescreenOutputData homescreenOutputData) {
        EventCrafterState eventCrafterState = eventCrafterViewModel.getState();
        eventCrafterState.setSignedInAs(homescreenOutputData.getSignedInAs());
        eventCrafterState.setArtistUsers(homescreenOutputData.getArtistUsers());
        eventCrafterState.setVenueUsers(homescreenOutputData.getVenueUsers());

        eventCrafterViewModel.setState(eventCrafterState);
        eventCrafterViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(eventCrafterViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void updateEvents(HomescreenGetEventOutputData homescreenOutputData) {
        HomescreenState homescreenState = homescreenViewModel.getState();
        homescreenState.setEvents(homescreenOutputData.getEvents());

        homescreenViewModel.setState(homescreenState);
        homescreenViewModel.firePropertyChanged();
    }
}
