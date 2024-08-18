package use_case.screen_switcher;

import entity.user.User;

/**
 * interface for switcher input
 */
public interface ScreenSwitcherInputBoundary {
    void switchToLogin();
    void switchToSplash();
    void switchToSignup();
    void switchToHome();
    void switchToMyEvents();
    void switchToSearchEvents();
    void switchToSearchUsers();
    void switchToEventCrafter();
    void switchToIsFollowing();
    void switchToMyFollowers();
    void switchToPost();
    void switchToMyProfile();

    void switchToViewProfile(User user);

    void switchToProfileEdit();
}
