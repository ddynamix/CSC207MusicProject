package use_case.screen_switcher;

import entity.user.User;

/**
 * profile data for switcher
 */
public class ScreenSwitcherProfileData {
    User profileUser;

    /**
     * set profile data
     * @param profileUser current login
     */
    public ScreenSwitcherProfileData(User profileUser) {
        this.profileUser = profileUser;
    }

    /**
     * access current login
     * @return current login
     */
    public User getSignedInUser() {
        return profileUser;
    }
}
