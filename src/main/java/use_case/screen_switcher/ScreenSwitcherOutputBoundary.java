package use_case.screen_switcher;

/**
 * interface for output data for switcher
 */
public interface ScreenSwitcherOutputBoundary {
    void switchToLogin();
    void switchToSplash();
    void switchToSignup();
    void switchToHome(ScreenSwitcherLoggedInData signedInData);
    void switchToMyEvents(ScreenSwitcherMyEventsData myEventsData);
    void switchToSearchEvents(ScreenSwitcherSearchEventsData searchEventsData);
    void switchToSearchUsers();
    void switchToEventCrafter(ScreenSwitcherEventCrafterData eventCrafterData);
    void switchToIsFollowing(ScreenSwitcherIsFollowingData isFollowingData);
    void switchToMyFollowers(ScreenSwitcherMyFollowersData myFollowersData);

    void switchToPost(ScreenSwitcherPostData screenSwitcherPostData);
}
