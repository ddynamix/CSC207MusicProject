package use_case.screen_switcher;

import entity.user.User;

/**
 * profile data for switcher
 */
public class ScreenSwitcherProfileData {
    User signedInUser;

    /**
     * set profile data
     * @param signedInUser current login
     */
    public ScreenSwitcherProfileData(User signedInUser) {
        this.signedInUser = signedInUser;
    }

    /**
     * access current login
     * @return current login
     */
    public User getSignedInUser() {
        return signedInUser;
    }
}
