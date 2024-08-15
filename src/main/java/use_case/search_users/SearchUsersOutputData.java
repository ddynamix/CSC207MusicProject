package use_case.search_users;

import entity.user.User;

import java.util.ArrayList;

/**
 * output data for user search use case
 */
public class SearchUsersOutputData {
    private final ArrayList<? extends User> returnUsers;

    /**
     * create instance of output data for user search use case
     * @param returnUsers users to be saved in output
     */
    public SearchUsersOutputData(ArrayList<? extends User> returnUsers) {
        this.returnUsers = returnUsers;
    }

    /**
     * access searched users
     * @return searched users
     */
    public ArrayList<? extends User> getReturnUsers() {
        return returnUsers;
    }
}
