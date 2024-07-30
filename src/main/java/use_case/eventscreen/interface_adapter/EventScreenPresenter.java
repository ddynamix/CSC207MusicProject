package use_case.eventscreen.interface_adapter;

import app.interface_adapter_tools.ViewManagerModel;
import view_model.*;
import use_case.eventscreen.EventScreenGetEventOutputData;
import use_case.eventscreen.EventScreenOutputBoundary;
import use_case.eventscreen.EventScreenOutputData;

public class EventScreenPresenter implements EventScreenOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final EventCrafterViewModel eventCrafterViewModel;
    private final HomescreenViewModel homescreenViewModel;
    private final EventScreenViewModel eventScreenViewModel;

    public EventScreenPresenter(ViewManagerModel viewManagerModel, EventCrafterViewModel eventCrafterViewModel, HomescreenViewModel homescreenViewModel, EventScreenViewModel eventScreenViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.eventCrafterViewModel = eventCrafterViewModel;
        this.homescreenViewModel = homescreenViewModel;
        this.eventScreenViewModel = eventScreenViewModel;
    }

    @Override
    public void prepareCreateEventView(EventScreenOutputData eventScreenOutputData) {
        EventCrafterState eventCrafterState = eventCrafterViewModel.getState();
        eventCrafterState.setSignedInAs(eventScreenOutputData.getSignedInAs());
        eventCrafterState.setArtistUsers(eventScreenOutputData.getArtistUsers());
        eventCrafterState.setVenueUsers(eventScreenOutputData.getVenueUsers());

        eventCrafterViewModel.setState(eventCrafterState);
        eventCrafterViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(eventCrafterViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void updateEvents(EventScreenGetEventOutputData eventScreenOutputData) {
        EventScreenState eventScreenState = eventScreenViewModel.getState();
        eventScreenState.setEvents(eventScreenOutputData.getEvents());

        eventScreenViewModel.setState(eventScreenState);
        eventScreenViewModel.firePropertyChanged();
    }
}
