package use_case.eventcrafter;

import entity.user.ArtistUser;
import entity.user.User;
import entity.user.VenueUser;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class EventCrafterInputData {
    private final String title;
    private final String description;
    private final ArtistUser artist;
    private final VenueUser venue;
    private final LocalDateTime dateAndTime;
    private final ArrayList<String> tags;
    private final LocalDateTime postDate;
    private final String attachedMedia;

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

    public String getAttachedMedia() {
        return attachedMedia;
    }

    public LocalDateTime getPostDate() {
        return postDate;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public VenueUser getVenue() {
        return venue;
    }

    public ArtistUser getArtist() {
        return artist;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }
}
