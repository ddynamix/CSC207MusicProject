package usecase.homescreen;

import entity.user.User;

public class HomescreenInputData {
    private final User signedInAs;

    public HomescreenInputData(User signedInAs) {
        this.signedInAs = signedInAs;
    }

    public User getSignedInAs() {
        return signedInAs;
    }
}
