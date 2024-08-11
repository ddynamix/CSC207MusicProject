package use_case.screen_switcher;

import entity.user.User;

import java.util.ArrayList;

public class ScreenSwitcherIsFollowingData {
    private ArrayList<? extends User> usersToDisplay;

    public ScreenSwitcherIsFollowingData(ArrayList<? extends User> usersToDisplay) {
        this.usersToDisplay = usersToDisplay;
    }

    public ArrayList<? extends User> getUsersToDisplay() {
        return usersToDisplay;
    }
}
