package entity.post;

import entity.user.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Post class
 */
public class Post implements IPost{
    private String title;
    private String text;
    private final User author;
    private LocalDateTime timePosted;

    private int id;
    private String attachedMedia;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

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


    /**
     * change title
     * @param title to be set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * change text
     * @param text to be set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * change media
     * @param media to be set
     */
    public void setAttachedMedia(String media) {this.attachedMedia = media;}

    /**
     * access date and time
     * @return data
     */
    public String getDateAndTimeString() {
        return timePosted.format(formatter);
    }

}