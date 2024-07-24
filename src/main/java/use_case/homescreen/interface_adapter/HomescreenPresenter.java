package use_case.homescreen.interface_adapter;

import app.interface_adapter_tools.ViewManagerModel;
import use_case.eventcrafter.interface_adapter.EventCrafterState;
import use_case.eventcrafter.interface_adapter.EventCrafterViewModel;
import use_case.homescreen.HomescreenGetEventOutputData;
import use_case.homescreen.HomescreenOutputBoundary;
import use_case.homescreen.HomescreenOutputData;
import use_case.splash.interface_adapter.SplashViewModel;

public class HomescreenPresenter implements HomescreenOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final EventCrafterViewModel eventCrafterViewModel;
    private final HomescreenViewModel homescreenViewModel;
    private final SplashViewModel splashViewModel;

    public HomescreenPresenter(ViewManagerModel viewManagerModel, EventCrafterViewModel eventCrafterViewModel, HomescreenViewModel homescreenViewModel, SplashViewModel splashViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.eventCrafterViewModel = eventCrafterViewModel;
        this.homescreenViewModel = homescreenViewModel;
        this.splashViewModel = splashViewModel;
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

    @Override
    public void prepareSplashView() {
        HomescreenState homescreenState = homescreenViewModel.getState();
        homescreenState.setSignedInAs(null);

        homescreenViewModel.setState(homescreenState);
        homescreenViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(splashViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
