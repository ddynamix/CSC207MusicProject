package use_case.follow_user.interface_adapter;

import entity.user.User;
import use_case.follow_user.FollowUserInputBoundary;
import use_case.follow_user.FollowUserInputData;

public class FollowUserController {
    private final FollowUserInputBoundary followUserInteractor;

    public FollowUserController(FollowUserInputBoundary followUserInteractor) {
        this.followUserInteractor = followUserInteractor;
    }

    public void followUser(User userToFollow) {
        followUserInteractor.followUser(new FollowUserInputData(userToFollow));
    }

    public void unfollowUser(User userToUnfollow) {
        followUserInteractor.unfollowUser(new FollowUserInputData(userToUnfollow));
    }
}
