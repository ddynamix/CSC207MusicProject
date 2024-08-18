package view_model;

import entity.user.User;

/**
 * create states for profile view
 */
public class ProfileState {
    public User signedInAs;
    private User viewing;
    private boolean isLoggedIn;

    /**
     * create instance of state
     */
    public ProfileState() {
        this.viewing = null;
        this.isLoggedIn = false;
        this.signedInAs = null;
    }

    /**
     * access current login
     * @return viewing
     */
    public User getViewing() {
        return viewing;
    }

    /**
     * change login
     * @param viewing to be set
     */
    public void setViewing(User viewing) {
        this.viewing = viewing;
    }

    /**
     * access current logged in user
     * @return signinas
     */
    public User getSignedInAs() {
        return signedInAs;
    }
}
