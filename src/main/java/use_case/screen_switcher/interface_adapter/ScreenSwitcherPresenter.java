package use_case.screen_switcher.interface_adapter;

import app.interface_adapter_tools.ViewManagerModel;
import view_model.EventCrafterState;
import view_model.EventScreenState;
import view_model.HomescreenState;
import use_case.screen_switcher.ScreenSwitcherEventCrafterData;
import use_case.screen_switcher.ScreenSwitcherLoggedInData;
import use_case.screen_switcher.ScreenSwitcherMyEventsData;
import use_case.screen_switcher.ScreenSwitcherOutputBoundary;

import view_model.LoginViewModel;
import view_model.SearchUsersViewModel;
import view_model.SplashViewModel;
import view_model.UserSignupViewModel;
import view_model.HomescreenViewModel;
import view_model.EventScreenViewModel;
import view_model.EventCrafterViewModel;

public class ScreenSwitcherPresenter implements ScreenSwitcherOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;
    private final SplashViewModel splashViewModel;
    private final UserSignupViewModel signupViewModel;
    private final HomescreenViewModel homescreenViewModel;
    private final EventScreenViewModel eventScreenViewModel;
    private final EventCrafterViewModel eventCrafterViewModel;
    private final SearchUsersViewModel searchUsersViewModel;

    public ScreenSwitcherPresenter(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SplashViewModel splashViewModel, UserSignupViewModel signupViewModel, HomescreenViewModel homeViewModel, EventScreenViewModel myEventsViewModel, EventCrafterViewModel eventCrafterViewModel, SearchUsersViewModel searchUsersViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.splashViewModel = splashViewModel;
        this.signupViewModel = signupViewModel;
        this.homescreenViewModel = homeViewModel;
        this.eventScreenViewModel = myEventsViewModel;
        this.eventCrafterViewModel = eventCrafterViewModel;
        this.searchUsersViewModel = searchUsersViewModel;
    }

    @Override
    public void switchToLogin() {
        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToSplash() {
        viewManagerModel.setActiveView(splashViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToSignup() {
        viewManagerModel.setActiveView(signupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToHome(ScreenSwitcherLoggedInData signedInData) {
        HomescreenState homescreenState = homescreenViewModel.getState();
        homescreenState.setSignedInAs(signedInData.getSignedInUser());
        homescreenViewModel.setState(homescreenState);
        homescreenViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(homescreenViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToMyEvents(ScreenSwitcherMyEventsData myEventsData) {
        EventScreenState eventScreenState = new EventScreenState();
        eventScreenState.setEvents(myEventsData.getMyEvents());
        eventScreenState.setSignedInAs(myEventsData.getSignedInUser());

        eventScreenViewModel.setState(eventScreenState);
        eventScreenViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(eventScreenViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToSearchUsers() {
        viewManagerModel.setActiveView(searchUsersViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToEventCrafter(ScreenSwitcherEventCrafterData eventScreenData) {
        EventCrafterState eventCrafterState = eventCrafterViewModel.getState();
        eventCrafterState.setSignedInAs(eventScreenData.getSignedInAs());
        eventCrafterState.setArtistUsers(eventScreenData.getArtistUsers());
        eventCrafterState.setVenueUsers(eventScreenData.getVenueUsers());

        eventCrafterViewModel.setState(eventCrafterState);
        eventCrafterViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(eventCrafterViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
