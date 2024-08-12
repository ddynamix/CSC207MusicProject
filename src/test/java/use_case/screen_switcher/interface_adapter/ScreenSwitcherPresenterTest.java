package use_case.screen_switcher.interface_adapter;

import app.interface_adapter_tools.ViewManagerModel;
import entity.event.Event;
import entity.user.ArtistUser;
import entity.user.AudienceUser;
import entity.user.User;
import entity.user.VenueUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.screen_switcher.*;
import view_model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class ScreenSwitcherPresenterTest {

    private ScreenSwitcherPresenter screenSwitcherPresenter;
    private ViewManagerModel viewManagerModel;
    private LoginViewModel loginViewModel;
    private SplashViewModel splashViewModel;
    private UserSignupViewModel signupViewModel;
    private HomescreenViewModel homescreenViewModel;
    private EventScreenViewModel eventScreenViewModel;
    private SearchEventsViewModel searchEventsViewModel;
    private EventCrafterViewModel eventCrafterViewModel;
    private SearchUsersViewModel searchUsersViewModel;
    private MyFollowersViewModel myFollowersViewModel;
    private IsFollowingViewModel isFollowingViewModel;
    private PostMakerViewModel postMakerViewModel;
    private ProfileViewModel profileViewModel;

    @BeforeEach
    public void setUp() {
        viewManagerModel = mock(ViewManagerModel.class);
        loginViewModel = mock(LoginViewModel.class);
        splashViewModel = mock(SplashViewModel.class);
        signupViewModel = mock(UserSignupViewModel.class);
        homescreenViewModel = mock(HomescreenViewModel.class);
        eventScreenViewModel = mock(EventScreenViewModel.class);
        isFollowingViewModel = mock(IsFollowingViewModel.class);
        postMakerViewModel = mock(PostMakerViewModel.class);
        profileViewModel = mock(ProfileViewModel.class);

        searchEventsViewModel = mock(SearchEventsViewModel.class);
        eventCrafterViewModel = mock(EventCrafterViewModel.class);
        searchUsersViewModel = mock(SearchUsersViewModel.class);
        myFollowersViewModel = mock(MyFollowersViewModel.class);
        screenSwitcherPresenter = new ScreenSwitcherPresenter(viewManagerModel, loginViewModel, splashViewModel, signupViewModel,
                homescreenViewModel, eventScreenViewModel, searchEventsViewModel, eventCrafterViewModel,
                searchUsersViewModel, myFollowersViewModel, isFollowingViewModel, postMakerViewModel, profileViewModel);
    }

    @Test
    public void testSwitchToLogin() {
        screenSwitcherPresenter.switchToLogin();
        verify(viewManagerModel, times(1)).setActiveView(loginViewModel.getViewName());
        verify(viewManagerModel, times(1)).firePropertyChanged();
    }

    @Test
    public void testSwitchToSplash() {
        screenSwitcherPresenter.switchToSplash();
        verify(viewManagerModel, times(1)).setActiveView(splashViewModel.getViewName());
        verify(viewManagerModel, times(1)).firePropertyChanged();
    }

    @Test
    public void testSwitchToSignup() {
        screenSwitcherPresenter.switchToSignup();
        verify(viewManagerModel, times(1)).setActiveView(signupViewModel.getViewName());
        verify(viewManagerModel, times(1)).firePropertyChanged();
    }

    @Test
    public void testSwitchToHome() {
        AudienceUser signedInUser = new AudienceUser("testUser", "Test User", "testPass", "testMail");
        ScreenSwitcherLoggedInData signedInData = new ScreenSwitcherLoggedInData(signedInUser);
        screenSwitcherPresenter.switchToHome(signedInData);
        verify(viewManagerModel, times(1)).setActiveView(homescreenViewModel.getViewName());
        verify(viewManagerModel, times(1)).firePropertyChanged();
    }

    @Test
    public void testSwitchToMyEvents() {
        AudienceUser signedInUser = new AudienceUser("testUser", "Test User", "testPass", "testMail");
        ArtistUser artist = new ArtistUser("testUser", "Test User", "testPass", "testMail");
        VenueUser venue = new VenueUser("testUser", "Test User", "testPass", "testMail");
        LocalDateTime testDate = LocalDateTime.now();
        ArrayList<String> testTags = new ArrayList<>();
        Event event1 = new Event("Test Event", artist, venue, testDate, "Test Description", testTags, testDate, "testMedia");
        Event event2 = new Event("Test Event", artist, venue, testDate, "Test Description", testTags, testDate, "testMedia");
        ArrayList<Event> events = new ArrayList<>();
        events.add(event1);
        events.add(event2);

        ScreenSwitcherMyEventsData myEventsData = new ScreenSwitcherMyEventsData(events, signedInUser);
        screenSwitcherPresenter.switchToMyEvents(myEventsData);
        verify(viewManagerModel, times(1)).setActiveView(eventScreenViewModel.getViewName());
        verify(viewManagerModel, times(1)).firePropertyChanged();
    }

    @Test
    public void testSwitchToSearchEvents() {
        ArtistUser artist = new ArtistUser("testUser", "Test User", "testPass", "testMail");
        VenueUser venue = new VenueUser("testUser", "Test User", "testPass", "testMail");
        LocalDateTime testDate = LocalDateTime.now();
        ArrayList<String> testTags = new ArrayList<>();
        Event event1 = new Event("Test Event", artist, venue, testDate, "Test Description", testTags, testDate, "testMedia");
        Event event2 = new Event("Test Event", artist, venue, testDate, "Test Description", testTags, testDate, "testMedia");
        ArrayList<Event> events = new ArrayList<>();
        events.add(event1);
        events.add(event2);

        ScreenSwitcherSearchEventsData searchEventsData = new ScreenSwitcherSearchEventsData(events);
        screenSwitcherPresenter.switchToSearchEvents(searchEventsData);
        verify(viewManagerModel, times(1)).setActiveView(searchEventsViewModel.getViewName());
        verify(viewManagerModel, times(1)).firePropertyChanged();
    }

    @Test
    public void testSwitchToSearchUsers() {
        screenSwitcherPresenter.switchToSearchUsers();
        verify(viewManagerModel, times(1)).setActiveView(searchUsersViewModel.getViewName());
        verify(viewManagerModel, times(1)).firePropertyChanged();
    }

    @Test
    public void testSwitchToEventCrafter() {
        AudienceUser signedInUser = new AudienceUser("testUser", "Test User", "testPass", "testMail");
        ScreenSwitcherEventCrafterData eventCrafterData = new ScreenSwitcherEventCrafterData(signedInUser, null, null);
        screenSwitcherPresenter.switchToEventCrafter(eventCrafterData);
        verify(viewManagerModel, times(1)).setActiveView(eventCrafterViewModel.getViewName());
        verify(viewManagerModel, times(1)).firePropertyChanged();
    }

    @Test
    public void testSwitchToPost() {
        AudienceUser signedInUser = new AudienceUser("testUser", "Test User", "testPass", "testMail");
        ScreenSwitcherPostData postData = new ScreenSwitcherPostData(null, signedInUser);
        screenSwitcherPresenter.switchToPost(postData);
        verify(viewManagerModel, times(1)).setActiveView(postMakerViewModel.getViewName());
        verify(viewManagerModel, times(1)).firePropertyChanged();
    }

    @Test
    public void testSwitchToMyFollowers() {
        AudienceUser user1 = new AudienceUser("User1", "User 1", "testPass", "testMail");
        AudienceUser user2 = new AudienceUser("User2", "User 2", "testPass", "testMail");
        ArrayList<User> followers = new ArrayList<>();
        followers.add(user1);
        followers.add(user2);

        ScreenSwitcherMyFollowersData myFollowersData = new ScreenSwitcherMyFollowersData(followers);
        screenSwitcherPresenter.switchToMyFollowers(myFollowersData);
        verify(viewManagerModel, times(1)).setActiveView(myFollowersViewModel.getViewName());
        verify(viewManagerModel, times(1)).firePropertyChanged();
    }

    @Test
    public void testSwitchToIsFollowing() {
        AudienceUser user1 = new AudienceUser("User1", "User 1", "testPass", "testMail");
        AudienceUser user2 = new AudienceUser("User2", "User 2", "testPass", "testMail");
        ArrayList<User> followers = new ArrayList<>();
        followers.add(user1);
        followers.add(user2);

        ScreenSwitcherIsFollowingData isFollowingData = new ScreenSwitcherIsFollowingData(followers);
        screenSwitcherPresenter.switchToIsFollowing(isFollowingData);
        verify(viewManagerModel, times(1)).setActiveView(isFollowingViewModel.getViewName());
        verify(viewManagerModel, times(1)).firePropertyChanged();
    }

    @Test
    public void testSwitchToMyProfile() {
        AudienceUser user = new AudienceUser("User1", "User 1", "testPass", "testMail");
        ScreenSwitcherProfileData profileData = new ScreenSwitcherProfileData(user);
        screenSwitcherPresenter.switchToMyProfile(profileData);
        verify(viewManagerModel, times(1)).setActiveView(profileViewModel.getViewName());
        verify(viewManagerModel, times(1)).firePropertyChanged();
    }
}