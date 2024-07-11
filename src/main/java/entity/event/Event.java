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

    public Event(String title, User artist, String venue, LocalDateTime dateAndTime, String description, ArrayList<String> tags, LocalDateTime postDate, String attachedMedia, int id) {
        this.title = title;
        this.artist = artist;
        this.venue = venue;
        this.dateAndTime = dateAndTime;
        this.description = description;
        this.tags = tags;
        this.postDate = postDate;
        this.attachedMedia = attachedMedia;
        this.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public User getArtist() {
        return artist;
    }

    @Override
    public String getVenue() {
        return venue;
    }

    @Override
    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ArrayList<String> getTags() {
        return tags;
    }

    @Override
    public LocalDateTime getPostDate() {
        return postDate;
    }

    @Override
    public String getAttachedMedia() {
        return attachedMedia;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setArtist(User artist) { this.artist = artist; }

    public void setVenue(String venue) { this.venue = venue; }

    public void setDateAndTime(LocalDateTime dateAndTime) { this.dateAndTime = dateAndTime; }

    public void setDescription(String description) { this.description = description; }

    public void setTags(ArrayList<String> tags) { this.tags = tags; }

    public void setPostDate(LocalDateTime postDate) { this.postDate = postDate; }

    public void setAttachedMedia(String attachedMedia) { this.attachedMedia = attachedMedia; }

}
