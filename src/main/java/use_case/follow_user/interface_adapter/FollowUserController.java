package use_case.follow_user.interface_adapter;

import entity.user.User;
import use_case.follow_user.FollowUserInputBoundary;
import use_case.follow_user.FollowUserInputData;

/**
 * controller for follow use case
 */
public class FollowUserController {
    private final FollowUserInputBoundary followUserInteractor;

    /**
     * create instance of controller for follow use case
     * @param followUserInteractor interactor for follow use case
     */
    public FollowUserController(FollowUserInputBoundary followUserInteractor) {
        this.followUserInteractor = followUserInteractor;
    }

    /**
     * add user
     * @param userToFollow to be added
     */
    public void followUser(User userToFollow) {
        followUserInteractor.followUser(new FollowUserInputData(userToFollow));
    }

    /**
     * remove user
     * @param userToUnfollow to be removed
     */
    public void unfollowUser(User userToUnfollow) {
        followUserInteractor.unfollowUser(new FollowUserInputData(userToUnfollow));
    }
}
