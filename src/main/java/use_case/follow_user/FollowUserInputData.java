package use_case.follow_user;

import entity.user.User;

/**
 * input data for follow use case
 */
public class FollowUserInputData {
    private final User user;

    /**
     * create new input data for follow use case
     * @param user to be followed
     */
    public FollowUserInputData(User user) {
        this.user = user;
    }

    /**
     * access user
     * @return to be followed
     */
    public User getUser() {
        return user;
    }
}
