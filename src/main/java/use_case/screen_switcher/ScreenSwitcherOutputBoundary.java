package use_case.screen_switcher;

import entity.user.User;

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

    void switchToViewProfile(ScreenSwitcherProfileData profileData, User loggedIn);

    void switchToMyFollowers(ScreenSwitcherMyFollowersData myFollowersData);
    void switchToMyProfile(ScreenSwitcherProfileData profileData);
    void switchToPost(ScreenSwitcherPostData screenSwitcherPostData);
    void switchToProfileEditor(ScreenSwitcherProfileData screenSwitcherProfileData);

}
