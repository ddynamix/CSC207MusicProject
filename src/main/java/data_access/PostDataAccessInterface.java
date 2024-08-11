package data_access;

import entity.post.Post;

import java.util.ArrayList;

public interface PostDataAccessInterface {
    void createPost(Post post);

    boolean postExists(String postTitle);

    void deletePost(Post post) throws PostDoesntExistException ;

    Post getPostFromTitle(String PostName);

    void updatePost(Post Post, String title, String text, String media) throws PostDoesntExistException ;

    ArrayList<Post> getPosts();
}
