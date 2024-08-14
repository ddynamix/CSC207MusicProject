package use_case.eventcrafter;

import entity.user.ArtistUser;
import entity.user.User;
import entity.user.VenueUser;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * input data for event use case
 */
public class EventCrafterInputData {
    private final String title;
    private final String description;
    private final ArtistUser artist;
    private final VenueUser venue;
    private final LocalDateTime dateAndTime;
    private final ArrayList<String> tags;
    private final LocalDateTime postDate;
    private final String attachedMedia;

    /**
     * create instance of input data for event use case
     * @param title of event
     * @param description of event
     * @param artist of event
     * @param venue of event
     * @param dateAndTime of event
     * @param tags applied to event
     * @param postDate of event
     * @param attachedMedia of event
     */
    public EventCrafterInputData(String title, String description, ArtistUser artist, VenueUser venue, LocalDateTime dateAndTime, ArrayList<String> tags, LocalDateTime postDate, String attachedMedia) {
        this.title = title;
        this.description = description;
        this.artist = artist;
        this.venue = venue;
        this.dateAndTime = dateAndTime;
        this.tags = tags;
        this.postDate = postDate;
        this.attachedMedia = attachedMedia;
    }

    /**
     * access media
     * @return media
     */
    public String getAttachedMedia() {
        return attachedMedia;
    }

    /**
     * access post date
     * @return post date
     */
    public LocalDateTime getPostDate() {
        return postDate;
    }

    /**
     * access tags
     * @return tags
     */
    public ArrayList<String> getTags() {
        return tags;
    }

    /**
     * access date
     * @return date
     */
    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    /**
     * access venue
     * @return venue
     */
    public VenueUser getVenue() {
        return venue;
    }

    /**
     * access artist
     * @return artist
     */
    public ArtistUser getArtist() {
        return artist;
    }

    /**
     * access description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * access title
     * @return title
     */
    public String getTitle() {
        return title;
    }
}
