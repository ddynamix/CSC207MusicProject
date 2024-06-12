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


    public Post(String title, String text, User author, LocalDateTime timePosted, int id, String attachedMedia, int rating) {
        this.title = title;
        this.text = text;
        this.author = author;
        this.timePosted = timePosted;
        this.id = id;
        this.attachedMedia = attachedMedia;
        this.rating = rating; //default value
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public User getAuthor() {
        return author;
    }

    @Override
    public int getRating() {
        return rating;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public LocalDateTime getTimePosted() {
        return timePosted;
    }

    @Override
    public String getAttachedMedia() {
        return attachedMedia;
    }
}