package entity.user;

import entity.user.User;
import entity.event.Event;
import entity.event.EventFactory;
import entity.post.Post;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * AudienceUser Class
 */
public class AudienceUser extends User{
    private ArrayList<Event> upcoming;

    /**
     * Create new AudienceUser instance
     * @param username      String          username of user
     * @param password      String          password of user
     * @param email         String          email of user
     * @param firstName     String          first name of user
     * @param lastName      String          last name of user
     * @param id            int             ID of user in database
     * @param followers     ArrayList<User> list of Users following this user
     * @param following     ArrayList<User> list of Users this user follows
     * @param pastEvents    ArrayList<Event>list of Events this user attended
     */
    public AudienceUser(String username, String password, String email, String firstName, String lastName, int id, ArrayList<User> followers, ArrayList<User> following, ArrayList<Event> pastEvents) {
        super(username, password, email, firstName, lastName, id, followers, following, pastEvents);
        this.upcoming = new ArrayList<>();

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

    /**
     * Return the upcoming events for this user
     * @return  ArrayList<Event>    this.upcoming
     */
    public ArrayList<Event> getUpcoming() { return upcoming; }

}

