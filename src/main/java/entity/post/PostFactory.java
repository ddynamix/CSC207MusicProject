package entity.post;

import entity.user.User;

import java.time.LocalDateTime;

/**
 * create posts
 */
public class PostFactory {
    /**
     * create a post
     * @param title of post
     * @param text of post
     * @param author of post
     * @param attachedMedia of post
     * @return new instance of post
     */
    public Post createPost(String title, String text, User author, String attachedMedia) {
        int id = 0; //Placeholder value
        int rating = 0; //Placeholder value
        return new Post(title, text, author, attachedMedia);
    }
}