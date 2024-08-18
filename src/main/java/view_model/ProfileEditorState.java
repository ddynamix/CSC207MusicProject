package view_model;

import entity.user.User;

/**
 * create profile editor state
 */
public class ProfileEditorState {
    private User profileToEdit;

    /**
     * create state
     */
    public ProfileEditorState() {
        this.profileToEdit = null;
    }

    /**
     * access profile
     * @return profile
     */
    public User getProfileToEdit() {
        return profileToEdit;
    }

    /**
     * change profile
     * @param profileToEdit profile to be set
     */
    public void setProfileToEdit(User profileToEdit) {
        this.profileToEdit = profileToEdit;
    }
}
