package use_case.follow_user;

import app.interface_adapter_tools.UserSession;
import data_access.FollowRelationalAccessInterface;
import entity.user.User;

/**
 * interactor for follow use case
 */
public class FollowUserInteractor implements FollowUserInputBoundary {
    private final FollowRelationalAccessInterface followRelationalAccess;

    /**
     * create instance of interactor for follow use case
     * @param followRelationalAccess DAO follow
     */
    public FollowUserInteractor(FollowRelationalAccessInterface followRelationalAccess) {
        this.followRelationalAccess = followRelationalAccess;
    }

    /**
     * add user
     * @param inputData new data
     */
    @Override
    public void followUser(FollowUserInputData inputData) {
        User follower = UserSession.getInstance().getLoggedInUser();
        User followee = inputData.getUser();
        followRelationalAccess.addFollower(follower, followee);
    }

    /**
     * remove user / unfollow
     * @param inputData new data
     */
    @Override
    public void unfollowUser(FollowUserInputData inputData) {
        User follower = UserSession.getInstance().getLoggedInUser();
        User followee = inputData.getUser();
        followRelationalAccess.removeFollower(follower, followee);
    }
}
