package view_model;

import entity.user.User;

/**
 * create user editor state
 */
public class UserEditorState {
    private User userToEdit;

    /**
     * create state
     */
    public UserEditorState() {
        this.userToEdit = null;
    }

    /**
     * access user
     * @return user
     */
    public User getUserToEdit() {
        return userToEdit;
    }

    /**
     * change user
     * @param userToEdit user to be set
     */
    public void setUserToEdit(User userToEdit) {
        this.userToEdit = userToEdit;
    }
}
