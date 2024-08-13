package use_case.screen_switcher.interface_adapter;

import app.interface_adapter_tools.ViewManagerModel;
import use_case.screen_switcher.*;
import view_model.*;

/**
 * presenter for switcher use case
 */
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
    private final ProfileViewModel profileViewModel;

    /**
     * create instance of presenter for switcher use case
     *
     * @param viewManagerModel      manager for changing models
     * @param loginViewModel        model for login use case
     * @param splashViewModel       model for splash view
     * @param signupViewModel       model for signup use case
     * @param homeViewModel         model for homescreen
     * @param myEventsViewModel     model for event screen
     * @param searchEventsViewModel model for event search use case
     * @param eventCrafterViewModel model for event use case
     * @param searchUsersViewModel  model for user search use case
     * @param myFollowersViewModel  model for follower use case
     * @param isFollowingViewModel  model for following use case
     * @param postMakerViewModel    model for post use case
     */
    public ScreenSwitcherPresenter(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel,
                                   SplashViewModel splashViewModel, UserSignupViewModel signupViewModel,
                                   HomescreenViewModel homeViewModel, EventScreenViewModel myEventsViewModel,
                                   SearchEventsViewModel searchEventsViewModel, EventCrafterViewModel eventCrafterViewModel,
                                   SearchUsersViewModel searchUsersViewModel, MyFollowersViewModel myFollowersViewModel,
                                   IsFollowingViewModel isFollowingViewModel, PostMakerViewModel postMakerViewModel,
                                   ProfileViewModel profileViewModel) {
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
        this.profileViewModel = profileViewModel;
    }

    /**
     * change view to login
     */
    @Override
    public void switchToLogin() {
        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * change view to splash
     */
    @Override
    public void switchToSplash() {
        viewManagerModel.setActiveView(splashViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * change view to signup
     */
    @Override
    public void switchToSignup() {
        viewManagerModel.setActiveView(signupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * change view to homescreen
     *
     * @param signedInData current login
     */
    @Override
    public void switchToHome(ScreenSwitcherLoggedInData signedInData) {
        HomescreenState homescreenState = homescreenViewModel.getState();
        if (homescreenState == null) {
            homescreenState = new HomescreenState();
        }
        homescreenState.setSignedInAs(signedInData.getSignedInUser());
        homescreenViewModel.setState(homescreenState);
        homescreenViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(homescreenViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * change view to event screen
     *
     * @param myEventsData event data
     */
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

    /**
     * change view to event search
     *
     * @param searchEventsData event data
     */
    @Override
    public void switchToSearchEvents(ScreenSwitcherSearchEventsData searchEventsData) {
        SearchEventsState searchEventsState = new SearchEventsState();
        searchEventsState.setEventsToDisplay(searchEventsData.getAllEvents());

        searchEventsViewModel.setState(searchEventsState);
        searchEventsViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(searchEventsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * change view to user search
     */
    @Override
    public void switchToSearchUsers() {
        viewManagerModel.setActiveView(searchUsersViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * change view to event crafter
     *
     * @param eventCrafterData events data
     */
    @Override
    public void switchToEventCrafter(ScreenSwitcherEventCrafterData eventCrafterData) {
        EventCrafterState eventCrafterState = eventCrafterViewModel.getState();
        if (eventCrafterState == null) {
            eventCrafterState = new EventCrafterState();
        }
        eventCrafterState.setSignedInAs(eventCrafterData.getSignedInAs());
        eventCrafterViewModel.setState(eventCrafterState);
        eventCrafterViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(eventCrafterViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * change view to make post
     *
     * @param postData posts data
     */
    @Override
    public void switchToPost(ScreenSwitcherPostData postData) {
        PostMakerState postMakerState = postMakerViewModel.getState();
        if (postMakerState == null) {
            postMakerState = new PostMakerState();
        }
        postMakerState.setSignedInAs(postData.getSignedInAs());
        postMakerViewModel.setState(postMakerState);
        postMakerViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(postMakerViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * change view to follower
     *
     * @param myFollowersData follower data
     */
    @Override
    public void switchToMyFollowers(ScreenSwitcherMyFollowersData myFollowersData) {
        MyFollowersState myFollowersState = myFollowersViewModel.getState();
        if (myFollowersState == null) {
            myFollowersState = new MyFollowersState();
        }
        myFollowersState.setUsersToDisplay(myFollowersData.getUsersToDisplay());
        myFollowersViewModel.setState(myFollowersState);
        myFollowersViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(myFollowersViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * change view to following
     *
     * @param isFollowingData following data
     */
    @Override
    public void switchToIsFollowing(ScreenSwitcherIsFollowingData isFollowingData) {
        IsFollowingState isFollowingState = isFollowingViewModel.getState();
        if (isFollowingState == null) {
            isFollowingState = new IsFollowingState();
        }
        isFollowingState.setUsersToDisplay(isFollowingData.getUsersToDisplay());
        isFollowingViewModel.setState(isFollowingState);
        isFollowingViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(isFollowingViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * change view to profile
     *
     * @param profileData profile data
     */
    @Override
    public void switchToMyProfile(ScreenSwitcherProfileData profileData) {
        ProfileState profileState = profileViewModel.getState();
        if (profileState == null) {
            profileState = new ProfileState();
            profileViewModel.setState(profileState);
        }
        profileState.setViewing(profileData.getSignedInUser());
        profileViewModel.setState(profileState);
        profileViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(profileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
