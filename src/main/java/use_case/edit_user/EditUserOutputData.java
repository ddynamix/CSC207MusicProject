package use_case.edit_user;

import entity.user.User;

/**
 * output data for edit user use case
 */
public class EditUserOutputData {
    private final User getUserToEdit;

    /**
     * create instance in edit user use case
     * @param getUserToEdit current user
     */
    public EditUserOutputData(User getUserToEdit) {
        this.getUserToEdit = getUserToEdit;
    }

    /**
     * access user to edit
     * @return user
     */
    public User getGetUserToEdit() {
        return getUserToEdit;
    }
}
