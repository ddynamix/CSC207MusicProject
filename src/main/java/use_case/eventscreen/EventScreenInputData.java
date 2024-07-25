package use_case.eventscreen;

import entity.user.User;

public class EventScreenInputData {
    private final User signedInAs;

    public EventScreenInputData(User signedInAs) {
        this.signedInAs = signedInAs;
    }

    public User getSignedInAs() {
        return signedInAs;
    }
}
