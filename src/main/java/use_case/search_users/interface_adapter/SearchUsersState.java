package use_case.search_users.interface_adapter;

import entity.user.User;

import java.util.ArrayList;

public class SearchUsersState {
    private ArrayList<? extends User> usersToDisplay;

    public SearchUsersState() {
        this.usersToDisplay = null;
    }

    public void setUsersToDisplay(ArrayList<? extends User> usersToDisplay) {
        this.usersToDisplay = usersToDisplay;
    }

    public ArrayList<? extends User> getUsersToDisplay() {
        return usersToDisplay;
    }
}
