package view_model;

import entity.user.User;

import java.util.ArrayList;

/**
 * create state for following
 */
public class IsFollowingState {
    private ArrayList<? extends User> usersToDisplay;

    /**
     * create instance of state
     */
    public IsFollowingState() {
        this.usersToDisplay = null;
    }

    /**
     * set users
     * @param usersToDisplay given users
     */
    public void setUsersToDisplay(ArrayList<? extends User> usersToDisplay) {
        this.usersToDisplay = usersToDisplay;
    }

    /**
     * access users
     * @return list of users
     */
    public ArrayList<? extends User> getUsersToDisplay() {
        return usersToDisplay;
    }
}
