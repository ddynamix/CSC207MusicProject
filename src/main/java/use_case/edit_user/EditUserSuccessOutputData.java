package use_case.edit_user;

import entity.user.User;

/**
 * correct output data for edit user use case
 */
public class EditUserSuccessOutputData {
    private final User user;

    /**
     * create instance of correct output data for edit post use case
     * @param user to be saved
     */
    public EditUserSuccessOutputData(User user) {
        this.user = user;
    }

    /**
     * access user
     * @return user
     */
    public User getUser() {
        return user;
    }
}
