package entity.user;

import entity.event.Event;
import entity.post.Post;

import java.util.ArrayList;

/**
 * AudienceUser Class
 */
public class AudienceUser extends User{

    /**
     * Create new AudienceUser instance
     * @param username      String          username of user
     * @param password      String          password of user
     * @param email         String          email of user
     * @param firstName     String          first name of user
     * @param lastName      String          last name of user
     */
    public AudienceUser(String name, String username, String password, String email) {
        super(name, username, password, email);
    }

    /**
     * Create a Post object created by and specified by this user
     * @param title         post.title
     * @param text          post.text
     * @param attachedMedia post.attachedMedia
     * @return  Post        the post created
     */
    public Post post(String title, String text, String attachedMedia){
        return new Post(title, text, this, attachedMedia);
    }

    /**
     * Share a post with this user's followers
     * @param post  Post    the post to share
     */
    public void share(Post post){
        for (User user: this.getFollowing()){
            post.share(user);
        }
    }
}

