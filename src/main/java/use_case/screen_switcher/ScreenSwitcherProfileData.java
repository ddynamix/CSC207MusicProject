package use_case.screen_switcher;

import entity.user.User;

/**
 * profile data for switcher
 */
public class ScreenSwitcherProfileData {
    User profileUser;
    User signedInAs;

    /**
     * set profile data for viewing a user
     * @param profileUser profile to be viewed
     * @param signedInAs current login
     */
    public ScreenSwitcherProfileData(User profileUser, User signedInAs) {
        this.profileUser = profileUser;
        this.signedInAs = signedInAs;
    }

    /**
     * set profile data
     * @param profileUser current login and profile owner
     */
    public ScreenSwitcherProfileData(User profileUser) {
        this.profileUser = profileUser;
        this.signedInAs = profileUser;
    }

    /**
     * access current login
     * @return current login
     */
    public User getSignedInUser() {
        return signedInAs;
    }

    /**
     * access profile owner
     * @return profile owner
     */
    public User getProfileUser() {
        return profileUser;
    }
}
