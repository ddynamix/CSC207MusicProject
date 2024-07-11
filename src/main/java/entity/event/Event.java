package entity.event;

import entity.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Event implements IEvent {
    private String title;
    private User artist;
    private String venue;
    private LocalDateTime dateAndTime;
    private String description;
    private ArrayList<String> tags;
    private LocalDateTime postDate;
    private String attachedMedia;
    private int id;

    public Event(String title, User artist, String venue, LocalDateTime dateAndTime, String description, ArrayList<String> tags, LocalDateTime postDate, String attachedMedia) {
        this.title = title;
        this.artist = artist;
        this.venue = venue;
        this.dateAndTime = dateAndTime;
        this.description = description;
        this.tags = tags;
        this.postDate = postDate;
        this.attachedMedia = attachedMedia;
        //this.id = 0; // should be generated
    }

    public String getTitle() {
        return title;
    }

    public User getArtist() {
        return artist;
    }

    public String getVenue() {
        return venue;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public LocalDateTime getPostDate() {
        return postDate;
    }

    public String getAttachedMedia() {
        return attachedMedia;
    }

    public int getId() {
        return id;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) { this.dateAndTime = dateAndTime; }

    public void setDescription(String description) { this.description = description; }

    public void setTags(ArrayList<String> tags) { this.tags = tags; }

    public void setAttachedMedia(String attachedMedia) { this.attachedMedia = attachedMedia; }

    public void setTitle(String title) { this.title = title; }

    public void setVenue(String venue) { this.venue = venue; }
}
