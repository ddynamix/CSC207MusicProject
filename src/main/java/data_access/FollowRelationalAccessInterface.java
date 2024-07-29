package data_access;

import entity.user.User;

public interface FollowRelationalAccessInterface {
    void addFollower(User follower, User followee);
    void removeFollower(User follower, User followee);
}
