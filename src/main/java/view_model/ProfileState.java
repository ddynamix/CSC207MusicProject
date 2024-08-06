package view_model;

import entity.user.User;

public class ProfileState {
    private User viewing;
    private boolean isLoggedIn;

    public ProfileState() {
        this.viewing = null;
        this.isLoggedIn = false;
    }

    public User getViewing() {
        return viewing;
    }

    public void setViewing(User viewing) {
        this.viewing = viewing;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
}
