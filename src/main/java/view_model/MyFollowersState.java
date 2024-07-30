package view_model;

import entity.user.User;

import java.util.ArrayList;

public class MyFollowersState {
    private ArrayList<? extends User> usersToDisplay;

    public MyFollowersState() {
        this.usersToDisplay = null;
    }

    public void setUsersToDisplay(ArrayList<? extends User> usersToDisplay) {
        this.usersToDisplay = usersToDisplay;
    }

    public ArrayList<? extends User> getUsersToDisplay() {
        return usersToDisplay;
    }
}
