package use_case.follow_user;

import entity.user.User;

public class FollowUserInputData {
    private final User user;

    public FollowUserInputData(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
