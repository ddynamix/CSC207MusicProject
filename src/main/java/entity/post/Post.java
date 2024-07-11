package entity.post;

import entity.user.User;

import java.time.LocalDateTime;

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


    public Post(String title, String text, User author, String attachedMedia) {
        this.title = title;
        this.text = text;
        this.author = author;
        this.timePosted = LocalDateTime.now();
        this.id = 0; // should be generated
        this.attachedMedia = attachedMedia;
        this.rating = 0; //default value
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public User getAuthor() {
        return author;
    }

    public int getRating() {
        return rating;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getTimePosted() {
        return timePosted;
    }

    public String getAttachedMedia() {
        return attachedMedia;
    }

    public void share(User destination){
        destination.getEmail(); // send somehow
    }
}