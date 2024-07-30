package use_case.screen_switcher;

public interface ScreenSwitcherOutputBoundary {
    void switchToLogin();
    void switchToSplash();
    void switchToSignup();
    void switchToHome(ScreenSwitcherLoggedInData signedInData);
    void switchToMyEvents(ScreenSwitcherMyEventsData myEventsData);
    void switchToSearchUsers();
    void switchToEventCrafter(ScreenSwitcherEventCrafterData eventCrafterData);
    void switchToMyFollowers(ScreenSwitcherMyFollowersData myFollowersData);
}
