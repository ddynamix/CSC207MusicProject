package view_model;

import entity.user.User;

import java.util.ArrayList;

public class ProfileState {
    private User signedInAs;
    private User viewing;

    public ProfileState() {
        this.signedInAs = null;
        this.viewing = null;
    }

    public void setSignedInAs(User signedInAs) {
        this.signedInAs = signedInAs;
    }

    public User getSignedInAs() {
        return signedInAs;
    }

    public void setViewing(User viewing) {
        this.viewing = viewing;
    }

    public User getViewing() {
        return viewing;
    }

}
