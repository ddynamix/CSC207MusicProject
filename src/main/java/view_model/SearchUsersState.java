package view_model;

import entity.user.User;

import java.util.ArrayList;

/**
 * create state for search users use case
 */
public class SearchUsersState {
    private ArrayList<? extends User> usersToDisplay;

    /**
     * create instance of state
     */
    public SearchUsersState() {
        this.usersToDisplay = null;
    }

    /**
     * change users
     * @param usersToDisplay to be set
     */
    public void setUsersToDisplay(ArrayList<? extends User> usersToDisplay) {
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
