package data_access;

import entity.user.User;
import entity.post.Post;

/**
 * interface for user -> post DAO
 */
public interface UsersPostsRelationalAccessInterface {
    void addPost(User user, Post post);
    void removePost(User user, Post post);
}
