package use_case.follow_user;

public interface FollowUserInputBoundary {
    void followUser(FollowUserInputData inputData);
    void unfollowUser(FollowUserInputData inputData);
}
