package use_case.screen_switcher;

import entity.user.User;

import java.util.ArrayList;

public class ScreenSwitcherMyFollowersData {
    private ArrayList<? extends User> usersToDisplay;

    public ScreenSwitcherMyFollowersData(ArrayList<? extends User> usersToDisplay) {
        this.usersToDisplay = usersToDisplay;
    }

    public ArrayList<? extends User> getUsersToDisplay() {
        return usersToDisplay;
    }
}
