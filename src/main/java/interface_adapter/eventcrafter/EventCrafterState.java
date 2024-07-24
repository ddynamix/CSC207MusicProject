package interface_adapter.eventcrafter;

import entity.user.User;

public class EventCrafterState {
    private User signedInAs;

    public EventCrafterState(User signedInAs) {
        this.signedInAs = signedInAs;
    }

    public void setSignedInAs(User signedInAs) {
        this.signedInAs = signedInAs;
    }

    public User getSignedInAs() {
        return signedInAs;
    }
}
