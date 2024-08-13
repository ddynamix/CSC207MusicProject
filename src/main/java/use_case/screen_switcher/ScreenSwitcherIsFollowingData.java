package use_case.screen_switcher;

import entity.user.User;

import java.util.ArrayList;

/**
 * following data for switcher
 */
public class ScreenSwitcherIsFollowingData {
    private ArrayList<? extends User> usersToDisplay;

    /**
     * create instance of following data for switcher
     * @param usersToDisplay user data
     */
    public ScreenSwitcherIsFollowingData(ArrayList<? extends User> usersToDisplay) {
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
