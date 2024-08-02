package use_case.screen_switcher.interface_adapter;

import app.interface_adapter_tools.ViewManagerModel;
import use_case.screen_switcher.*;
import view_model.*;

public class ScreenSwitcherPresenter implements ScreenSwitcherOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;
    private final SplashViewModel splashViewModel;
    private final UserSignupViewModel signupViewModel;
    private final HomescreenViewModel homescreenViewModel;
    private final EventScreenViewModel eventScreenViewModel;
    private final SearchEventsViewModel searchEventsViewModel;
    private final EventCrafterViewModel eventCrafterViewModel;
    private final SearchUsersViewModel searchUsersViewModel;
    private final MyFollowersViewModel myFollowersViewModel;
    private final IsFollowingViewModel isFollowingViewModel;
    private final PostMakerViewModel postMakerViewModel;

    public ScreenSwitcherPresenter(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel,
                                   SplashViewModel splashViewModel, UserSignupViewModel signupViewModel,
                                   HomescreenViewModel homeViewModel, EventScreenViewModel myEventsViewModel,
                                   SearchEventsViewModel searchEventsViewModel, EventCrafterViewModel eventCrafterViewModel,
                                   SearchUsersViewModel searchUsersViewModel, MyFollowersViewModel myFollowersViewModel,
                                   IsFollowingViewModel isFollowingViewModel, PostMakerViewModel postMakerViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.splashViewModel = splashViewModel;
        this.signupViewModel = signupViewModel;
        this.homescreenViewModel = homeViewModel;
        this.eventScreenViewModel = myEventsViewModel;
        this.searchEventsViewModel = searchEventsViewModel;
        this.eventCrafterViewModel = eventCrafterViewModel;
        this.searchUsersViewModel = searchUsersViewModel;
        this.myFollowersViewModel = myFollowersViewModel;
        this.isFollowingViewModel = isFollowingViewModel;
        this.postMakerViewModel = postMakerViewModel;
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
    public void switchToSearchEvents(ScreenSwitcherSearchEventsData searchEventsData) {
        SearchEventsState searchEventsState = new SearchEventsState();
        searchEventsState.setEventsToDisplay(searchEventsData.getAllEvents());

        searchEventsViewModel.setState(searchEventsState);
        searchEventsViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(searchEventsViewModel.getViewName());
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

    @Override
    public void switchToPost(ScreenSwitcherPostData postData) {
        PostMakerState postMakerState = postMakerViewModel.getState();
        postMakerState.setSignedInAs(postData.getSignedInAs());
        postMakerState.setPosts(postData.getPosts());

        postMakerViewModel.setState(postMakerState);
        postMakerViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(postMakerViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToMyFollowers(ScreenSwitcherMyFollowersData myFollowersData) {
        MyFollowersState myFollowersState = myFollowersViewModel.getState();
        myFollowersState.setUsersToDisplay(myFollowersData.getUsersToDisplay());

        myFollowersViewModel.setState(myFollowersState);
        myFollowersViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(myFollowersViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToIsFollowing(ScreenSwitcherIsFollowingData isFollowingData) {
        IsFollowingState isFollowingState = isFollowingViewModel.getState();
        isFollowingState.setUsersToDisplay(isFollowingData.getUsersToDisplay());

        isFollowingViewModel.setState(isFollowingState);
        isFollowingViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(isFollowingViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
