package use_case.search_users;

import entity.user.User;

import java.util.ArrayList;

public class SearchUsersOutputData {
    private final ArrayList<? extends User> returnUsers;

    public SearchUsersOutputData(ArrayList<? extends User> returnUsers) {
        this.returnUsers = returnUsers;
    }

    public ArrayList<? extends User> getReturnUsers() {
        return returnUsers;
    }
}
