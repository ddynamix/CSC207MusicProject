package view_model;

import entity.user.User;

import java.util.ArrayList;

/**
 * state for followers view
 */
public class MyFollowersState {
    private ArrayList<? extends User> usersToDisplay;

    /**
     * create state
     */
    public MyFollowersState() {
        this.usersToDisplay = null;
    }

    /**
     * change users in followers
     * @param usersToDisplay to be set
     */
    public void setUsersToDisplay(ArrayList<? extends User> usersToDisplay) {
        this.usersToDisplay = usersToDisplay;
    }

    /**
     * return followers
     * @return array list of followers
     */
    public ArrayList<? extends User> getUsersToDisplay() {
        return usersToDisplay;
    }
}
