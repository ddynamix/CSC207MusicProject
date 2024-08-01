package entity.event;

import entity.user.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Event Class
 */
public class Event implements IEvent {
    private String title;
    private User artist;
    private String venue;
    private LocalDateTime dateAndTime;
    private String description;
    private ArrayList<String> tags;
    private LocalDateTime postDate;
    private String attachedMedia;
    private String id;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    /**
     * @param title         String              title
     * @param artist        User                artist
     * @param venue         String              venue
     * @param dateAndTime   LocalDateTime       event time
     * @param description   String              description of event
     * @param tags          ArrayList<String>   tags
     * @param postDate      LocalDateTime       date event posted
     * @param attachedMedia String              media of event
     */
    public Event(String title, User artist, String venue, LocalDateTime dateAndTime, String description, ArrayList<String> tags, LocalDateTime postDate, String attachedMedia) {
        this.title = title;
        this.artist = artist;
        this.venue = venue;
        this.dateAndTime = dateAndTime;
        this.description = description;
        this.tags = tags;
        this.postDate = postDate;
        this.attachedMedia = attachedMedia;
        this.id = 0; // should be generated
    }

    /**
     * Return title
     * @return String title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Return Artist
     * @return User artist
     */
    public User getArtist() {
        return artist;
    }

    /**
     * Return venue
     * @return String venue
     */
    public String getVenue() {
        return venue;
    }

    /**
     * Return date of event
     * @return LocalDateTime dateAndTime
     */
    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    /**
     * Return description
     * @return String description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Return tags
     * @return ArrayList<String> tags
     */
    public ArrayList<String> getTags() {
        return tags;
    }

    /**
     * Return post date
     * @return LocalDateTime postDate
     */
    public LocalDateTime getPostDate() {
        return postDate;
    }

    /**
     * Return media
     * @return String attachedMedia
     */
    public String getAttachedMedia() {
        return attachedMedia;
    }

    /**
     * ID in database
     * @return int id
     */
    public String getId() {
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    @Override
    public String getDateAndTimeString() {
        return dateAndTime.format(formatter);
    }

    @Override
    public String getPostDateString() {
        return postDate.format(formatter);
    }

    @Override
    public String getTagsString() {
        return String.join("; ", tags);
    }

    /**
     * Update date
     * @param dateAndTime LocalDateTime
     */
    public void setDateAndTime(LocalDateTime dateAndTime) { this.dateAndTime = dateAndTime; }

    /**
     * Update description
     * @param description String
     */
    public void setDescription(String description) { this.description = description; }

    /**
     * Update tags
     * @param tags Arraylist<String>
     */
    public void setTags(ArrayList<String> tags) { this.tags = tags; }

    /**
     * Update media
     * @param attachedMedia String
     */
    public void setAttachedMedia(String attachedMedia) { this.attachedMedia = attachedMedia; }

    /**
     * Update title
     * @param title String
     */
    public void setTitle(String title) { this.title = title; }

    /**
     * Update venue
     * @param venue String
     */
    public void setVenue(String venue) { this.venue = venue; }

}
