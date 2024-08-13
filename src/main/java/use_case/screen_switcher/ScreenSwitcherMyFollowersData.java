package use_case.screen_switcher;

import entity.user.User;

import java.util.ArrayList;

/**
 * follower data for switcher
 */
public class ScreenSwitcherMyFollowersData {
    private ArrayList<? extends User> usersToDisplay;

    /**
     * create instance of follower data
     * @param usersToDisplay user data
     */
    public ScreenSwitcherMyFollowersData(ArrayList<? extends User> usersToDisplay) {
        this.usersToDisplay = usersToDisplay;
    }

    /**
     * access users
     * @return users
     */
    public ArrayList<? extends User> getUsersToDisplay() {
        return usersToDisplay;
    }
}
