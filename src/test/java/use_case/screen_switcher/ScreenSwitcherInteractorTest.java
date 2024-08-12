package use_case.screen_switcher;

import app.interface_adapter_tools.UserSession;
import data_access.EventDataAccessInterface;
import data_access.PostDataAccessInterface;
import data_access.UserDataAccessInterface;
import entity.event.Event;
import entity.post.Post;
import entity.user.ArtistUser;
import entity.user.AudienceUser;
import entity.user.User;
import entity.user.VenueUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ScreenSwitcherInteractorTest {

    private ScreenSwitcherInteractor screenSwitcherInteractor;
    private ScreenSwitcherOutputBoundary screenSwitchPresenter;
    private UserDataAccessInterface userDataAccess;
    private EventDataAccessInterface eventDataAccess;
    private PostDataAccessInterface postDataAccess;

    @BeforeEach
    public void setUp() {
        screenSwitchPresenter = mock(ScreenSwitcherOutputBoundary.class);
        userDataAccess = mock(UserDataAccessInterface.class);
        eventDataAccess = mock(EventDataAccessInterface.class);
        postDataAccess = mock(PostDataAccessInterface.class);
        screenSwitcherInteractor = new ScreenSwitcherInteractor(screenSwitchPresenter, userDataAccess, eventDataAccess, postDataAccess);
    }

    @Test
    public void testSwitchToLogin() {
        screenSwitcherInteractor.switchToLogin();
        verify(screenSwitchPresenter, times(1)).switchToLogin();
    }

    @Test
    public void testSwitchToSplash() {
        screenSwitcherInteractor.switchToSplash();
        verify(screenSwitchPresenter, times(1)).switchToSplash();
    }

    @Test
    public void testSwitchToSignup() {
        screenSwitcherInteractor.switchToSignup();
        verify(screenSwitchPresenter, times(1)).switchToSignup();
    }

    @Test
    public void testSwitchToHome() {
        User loggedInUser = new AudienceUser("testUser", "Test User", "testPass", "testMail");
        UserSession.getInstance().setLoggedInUser(loggedInUser);

        screenSwitcherInteractor.switchToHome();

        ArgumentCaptor<ScreenSwitcherLoggedInData> captor = ArgumentCaptor.forClass(ScreenSwitcherLoggedInData.class);
        verify(screenSwitchPresenter, times(1)).switchToHome(captor.capture());
        assertEquals(loggedInUser, captor.getValue().getSignedInUser());
    }

    @Test
    public void testSwitchToMyEvents() {
        User loggedInUser = new AudienceUser("testUser", "Test User", "testPass", "testMail");
        ArrayList<Event> myEvents = new ArrayList<>();
        ArtistUser artist = new ArtistUser("testUser", "Test User", "testPass", "testMail");
        VenueUser venue = new VenueUser("testUser", "Test User", "testPass", "testMail");
        LocalDateTime testDate = LocalDateTime.now();
        ArrayList<String> testTags = new ArrayList<>();
        Event event1 = new Event("Test Event", artist, venue, testDate, "Test Description", testTags, testDate, "testMedia");
        myEvents.add(event1);

        loggedInUser.addEvent(event1);
        UserSession.getInstance().setLoggedInUser(loggedInUser);

        screenSwitcherInteractor.switchToMyEvents();

        ArgumentCaptor<ScreenSwitcherMyEventsData> captor = ArgumentCaptor.forClass(ScreenSwitcherMyEventsData.class);
        verify(screenSwitchPresenter, times(1)).switchToMyEvents(captor.capture());
        assertEquals(myEvents, captor.getValue().getMyEvents());
        assertEquals(loggedInUser, captor.getValue().getSignedInUser());
    }

    @Test
    public void testSwitchToSearchEvents() {
        ArrayList<Event> allEvents = new ArrayList<>();

        ArtistUser artist = new ArtistUser("testUser", "Test User", "testPass", "testMail");
        VenueUser venue = new VenueUser("testUser", "Test User", "testPass", "testMail");
        LocalDateTime testDate = LocalDateTime.now();
        ArrayList<String> testTags = new ArrayList<>();
        Event event1 = new Event("Test Event", artist, venue, testDate, "Test Description", testTags, testDate, "testMedia");

        allEvents.add(event1);
        when(eventDataAccess.getEvents()).thenReturn(allEvents);

        screenSwitcherInteractor.switchToSearchEvents();

        ArgumentCaptor<ScreenSwitcherSearchEventsData> captor = ArgumentCaptor.forClass(ScreenSwitcherSearchEventsData.class);
        verify(screenSwitchPresenter, times(1)).switchToSearchEvents(captor.capture());
        assertEquals(allEvents, captor.getValue().getAllEvents());
    }

    @Test
    public void testSwitchToSearchUsers() {
        screenSwitcherInteractor.switchToSearchUsers();
        verify(screenSwitchPresenter, times(1)).switchToSearchUsers();
    }

    @Test
    public void testSwitchToEventCrafter() {
        User loggedInUser = new AudienceUser("testUser", "Test User", "testPass", "testMail");
        ArrayList<ArtistUser> artists = new ArrayList<>();
        ArrayList<VenueUser> venues = new ArrayList<>();
        when(userDataAccess.getArtistUsers()).thenReturn(artists);
        when(userDataAccess.getVenueUsers()).thenReturn(venues);
        UserSession.getInstance().setLoggedInUser(loggedInUser);

        screenSwitcherInteractor.switchToEventCrafter();

        ArgumentCaptor<ScreenSwitcherEventCrafterData> captor = ArgumentCaptor.forClass(ScreenSwitcherEventCrafterData.class);
        verify(screenSwitchPresenter, times(1)).switchToEventCrafter(captor.capture());
        assertEquals(loggedInUser, captor.getValue().getSignedInAs());
        assertEquals(artists, captor.getValue().getArtistUsers());
        assertEquals(venues, captor.getValue().getVenueUsers());
    }

    @Test
    public void testSwitchToPost() {
        User loggedInUser = new AudienceUser("testUser", "Test User", "testPass", "testMail");
        ArrayList<Post> posts = new ArrayList<>();
        when(postDataAccess.getPosts()).thenReturn(posts);
        UserSession.getInstance().setLoggedInUser(loggedInUser);

        screenSwitcherInteractor.switchToPost();

        ArgumentCaptor<ScreenSwitcherPostData> captor = ArgumentCaptor.forClass(ScreenSwitcherPostData.class);
        verify(screenSwitchPresenter, times(1)).switchToPost(captor.capture());
        assertEquals(posts, captor.getValue().getPosts());
        assertEquals(loggedInUser, captor.getValue().getSignedInAs());
    }

    @Test
    public void testSwitchToIsFollowing() {
        User loggedInUser = new AudienceUser("testUser", "Test User", "testPass", "testMail");
        AudienceUser following1 = new AudienceUser("testUser2", "Test User 2", "testPass2", "testMail2");
        loggedInUser.addFollowing(following1);

        ArrayList<? extends User> following = loggedInUser.getFollowing();

        UserSession.getInstance().setLoggedInUser(loggedInUser);

        screenSwitcherInteractor.switchToIsFollowing();

        ArgumentCaptor<ScreenSwitcherIsFollowingData> captor = ArgumentCaptor.forClass(ScreenSwitcherIsFollowingData.class);
        verify(screenSwitchPresenter, times(1)).switchToIsFollowing(captor.capture());
        assertEquals(following, captor.getValue().getUsersToDisplay());
    }

    @Test
    public void testSwitchToMyFollowers() {
        User loggedInUser = new AudienceUser("testUser", "Test User", "testPass", "testMail");
        AudienceUser follower1 = new AudienceUser("testUser2", "Test User 2", "testPass2", "testMail2");
        loggedInUser.addFollower(follower1);
        UserSession.getInstance().setLoggedInUser(loggedInUser);

        ArrayList<User> followers = loggedInUser.getFollowers();

        screenSwitcherInteractor.switchToMyFollowers();

        ArgumentCaptor<ScreenSwitcherMyFollowersData> captor = ArgumentCaptor.forClass(ScreenSwitcherMyFollowersData.class);
        verify(screenSwitchPresenter, times(1)).switchToMyFollowers(captor.capture());
        assertEquals(followers, captor.getValue().getUsersToDisplay());
    }

    @Test
    public void testSwitchToMyProfile() {
        User loggedInUser = new AudienceUser("testUser", "Test User", "testPass", "testMail");
        UserSession.getInstance().setLoggedInUser(loggedInUser);

        screenSwitcherInteractor.switchToMyProfile();

        ArgumentCaptor<ScreenSwitcherProfileData> captor = ArgumentCaptor.forClass(ScreenSwitcherProfileData.class);
        verify(screenSwitchPresenter, times(1)).switchToMyProfile(captor.capture());
        assertEquals(loggedInUser, captor.getValue().getUser());
    }
}