package use_case.follow_user;

/**
 * interface for input data for follow use case
 */
public interface FollowUserInputBoundary {
    void followUser(FollowUserInputData inputData);
    void unfollowUser(FollowUserInputData inputData);
}
