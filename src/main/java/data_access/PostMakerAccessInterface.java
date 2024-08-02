package data_access;

import entity.post.Post;

import java.util.Map;

public interface PostMakerAccessInterface {
    void createPost(Post post);
    void deletePost(Post post);
    Post getPosts(String eventName);
    Map<String, Post> getPosts();
}
