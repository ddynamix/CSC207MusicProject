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
    private final User author;
    private final LocalDateTime timePosted;

    private int id = 0;
    private String attachedMedia = null;

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


    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setAttachedMedia(String media) {
        this.attachedMedia = media;
    }
}