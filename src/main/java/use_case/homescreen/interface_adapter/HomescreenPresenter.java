package use_case.homescreen.interface_adapter;

import app.interface_adapter_tools.ViewManagerModel;
import use_case.eventscreen.interface_adapter.EventScreenState;
import use_case.eventscreen.interface_adapter.EventScreenViewModel;
import use_case.homescreen.HomescreenOutputBoundary;
import use_case.homescreen.HomescreenOutputData;
import use_case.splash.interface_adapter.SplashViewModel;

public class HomescreenPresenter implements HomescreenOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final EventScreenViewModel eventScreenViewModel;
    private final SplashViewModel splashViewModel;

    public HomescreenPresenter(ViewManagerModel viewManagerModel, EventScreenViewModel eventScreenViewModel, SplashViewModel splashViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.eventScreenViewModel = eventScreenViewModel;
        this.splashViewModel = splashViewModel;
    }

    @Override
    public void prepareEventPageView(HomescreenOutputData homescreenOutputData) {
        EventScreenState eventScreenState = new EventScreenState();
        eventScreenState.setEvents(homescreenOutputData.getMyEvents());

        eventScreenViewModel.setState(eventScreenState);
        eventScreenViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(eventScreenViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareSplashView() {
        viewManagerModel.setActiveView(splashViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
