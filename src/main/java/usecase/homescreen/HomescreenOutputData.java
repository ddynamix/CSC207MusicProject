package usecase.homescreen;

import entity.user.User;

public class HomescreenOutputData {
    private final User signedInAs;

    public HomescreenOutputData(User signedInAs) {
        this.signedInAs = signedInAs;
    }

    public User getSignedInAs() {
        return signedInAs;
    }
}
