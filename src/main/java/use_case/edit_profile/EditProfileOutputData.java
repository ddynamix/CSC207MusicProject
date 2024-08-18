package use_case.edit_profile;

import entity.user.User;

/**
 * output data for edit post use case
 */
public class EditProfileOutputData {
    private User getUserToEdit;

    /**
     * create instance in edit profile use case
     * @param userToAlter current post
     */
    public EditProfileOutputData(User userToAlter) {
        this.getUserToEdit = userToAlter;
    }


    /**
     * access user to edit
     * @return user
     */
    public User getGetProfileToEdit() {
        return getUserToEdit;
    }
}
