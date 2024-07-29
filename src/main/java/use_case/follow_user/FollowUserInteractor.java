package use_case.follow_user;

import app.interface_adapter_tools.UserSession;
import data_access.FollowRelationalAccessInterface;
import entity.user.User;

public class FollowUserInteractor implements FollowUserInputBoundary {
    private final FollowRelationalAccessInterface followRelationalAccess;

    public FollowUserInteractor(FollowRelationalAccessInterface followRelationalAccess) {
        this.followRelationalAccess = followRelationalAccess;
    }

    @Override
    public void followUser(FollowUserInputData inputData) {
        User follower = UserSession.getInstance().getLoggedInUser();
        User followee = inputData.getUser();
        followRelationalAccess.addFollower(follower, followee);
    }

    @Override
    public void unfollowUser(FollowUserInputData inputData) {
        User follower = UserSession.getInstance().getLoggedInUser();
        User followee = inputData.getUser();
        followRelationalAccess.removeFollower(follower, followee);
    }
}
