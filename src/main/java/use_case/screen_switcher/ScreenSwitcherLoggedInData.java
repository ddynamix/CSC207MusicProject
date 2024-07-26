package use_case.screen_switcher;

import entity.user.User;

public class ScreenSwitcherLoggedInData {
    User signedInUser;

    public ScreenSwitcherLoggedInData(User signedInUser) {
        this.signedInUser = signedInUser;
    }

    public User getSignedInUser() {
        return signedInUser;
    }
}
