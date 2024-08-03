package use_case.screen_switcher;

import entity.user.User;

public class ScreenSwitcherProfileData {
    User signedInUser;

    public ScreenSwitcherProfileData(User signedInUser) {
        this.signedInUser = signedInUser;
    }

    public User getSignedInUser() {
        return signedInUser;
    }
}
