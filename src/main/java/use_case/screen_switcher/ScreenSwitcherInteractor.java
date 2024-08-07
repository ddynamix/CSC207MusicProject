package use_case.screen_switcher;

import app.interface_adapter_tools.UserSession;
import data_access.UserDataAccessInterface;
import entity.user.ArtistUser;
import entity.user.User;
import entity.event.Event;
import entity.user.VenueUser;

import java.util.ArrayList;

public class ScreenSwitcherInteractor implements ScreenSwitcherInputBoundary {
    private final ScreenSwitcherOutputBoundary screenSwitchPresenter;
    private final UserDataAccessInterface userDataAccess;

    public ScreenSwitcherInteractor(ScreenSwitcherOutputBoundary screenSwitchPresenter, UserDataAccessInterface userDataAccessObject) {
        this.screenSwitchPresenter = screenSwitchPresenter;
        this.userDataAccess = userDataAccessObject;
    }

    @Override
    public void switchToLogin() {
        screenSwitchPresenter.switchToLogin();
    }

    @Override
    public void switchToSplash() {
        screenSwitchPresenter.switchToSplash();
    }

    @Override
    public void switchToSignup() {
        screenSwitchPresenter.switchToSignup();
    }

    @Override
    public void switchToHome() {
        User loggedIn = UserSession.getInstance().getLoggedInUser();
        screenSwitchPresenter.switchToHome(new ScreenSwitcherLoggedInData(loggedIn));
    }

    @Override
    public void switchToMyEvents() {
        User loggedIn = UserSession.getInstance().getLoggedInUser();
        ArrayList<Event> myEvents = loggedIn.getMyEvents();
        screenSwitchPresenter.switchToMyEvents(new ScreenSwitcherMyEventsData(myEvents, loggedIn));
    }

    @Override
    public void switchToSearchUsers() {
        screenSwitchPresenter.switchToSearchUsers();
    }

    @Override
    public void switchToEventCrafter() {
        User loggedIn = UserSession.getInstance().getLoggedInUser();
        ArrayList<ArtistUser> artists = userDataAccess.getArtistUsers();
        ArrayList<VenueUser> venues = userDataAccess.getVenueUsers();
        screenSwitchPresenter.switchToEventCrafter(new ScreenSwitcherEventCrafterData(loggedIn, artists, venues));
    }

    @Override
    public void switchToIsFollowing() {
        User loggedIn = UserSession.getInstance().getLoggedInUser();
        ArrayList<User> following = loggedIn.getFollowing();
        screenSwitchPresenter.switchToIsFollowing(new ScreenSwitcherIsFollowingData(following));
    }

    @Override
    public void switchToMyFollowers() {
        User loggedIn = UserSession.getInstance().getLoggedInUser();
        ArrayList<User> followers = loggedIn.getFollowers();
        screenSwitchPresenter.switchToMyFollowers(new ScreenSwitcherMyFollowersData(followers));
    }
}
