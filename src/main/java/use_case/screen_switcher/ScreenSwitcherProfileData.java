package use_case.screen_switcher;

import entity.user.User;

public class ScreenSwitcherProfileData {
    User userToDisplay;

    public ScreenSwitcherProfileData(User userToDisplay) {
        this.userToDisplay = userToDisplay;
    }

    public User getUser() {
        return userToDisplay;
    }
}
