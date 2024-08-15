package data_access;

import entity.user.User;

/**
 * data access for user : followers
 */
public interface FollowRelationalAccessInterface {
    void addFollower(User follower, User followee);
    void removeFollower(User follower, User followee);
}
