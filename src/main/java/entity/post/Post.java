package entity.post;

import entity.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Post class
 */
public class Post implements IPost{
    private String title;
    private String text;
    private User author;
    private LocalDateTime timePosted;

    private int rating = 0;
    private int id = 0;
    private String attachedMedia = null;

    //private Media content;
    //private Event taggedEvent;


    /**
     * Create instance of the Post class
     * @param title             String  title of post
     * @param text              String  message of post
     * @param author            User    author/creator of post
     * @param attachedMedia     String  media
     */
    public Post(String title, String text, User author, String attachedMedia) {
        this.title = title;
        this.text = text;
        this.author = author;
        this.timePosted = LocalDateTime.now();
        this.id = 0; // should be generated
        this.attachedMedia = attachedMedia;
        this.rating = 0; //default value
    }

    /**
     * Return title
     * @return  String  title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Return message
     * @return  String  text
     */
    public String getText() {
        return text;
    }

    /**
     * Return author/creator of post
     * @return  User    author
     */
    public User getAuthor() {
        return author;
    }

    /**
     * Return rating of post
     * @return  int rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * Return ID of post in database
     * @return  int     id
     */
    public int getId() {
        return id;
    }

    /**
     * Return post send time
     * @return  LocalDateTime   timePosted
     */
    public LocalDateTime getTimePosted() {
        return timePosted;
    }

    /**
     * Return media
     * @return  String  attachedMedia
     */
    public String getAttachedMedia() {
        return attachedMedia;
    }

    /**
     * Share the post with a designated user
     * @param destination   User    who the post is being sent to
     */
    public void share(User destination){
        destination.getEmail(); // send somehow
    }

    /**
     * Share the post with a multiple users
     * @param people   ArrayList<User>    who the post is being sent to
     */
    public void share(ArrayList<User> people){
        for (User destination : people){
            destination.getEmail(); // send somehow
        }

    }
}