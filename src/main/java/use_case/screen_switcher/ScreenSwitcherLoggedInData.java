package use_case.screen_switcher;

import entity.user.User;

/**
 * login data for switcher
 */
public class ScreenSwitcherLoggedInData {
    User signedInUser;

    /**
     * create instance of login data for switcher
     * @param signedInUser current login
     */
    public ScreenSwitcherLoggedInData(User signedInUser) {
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
