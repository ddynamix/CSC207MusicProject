package entity.event;

import entity.user.ArtistUser;
import entity.user.VenueUser;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * interface for event objects
 */
public interface IEvent {
    String getTitle();
    ArtistUser getArtist();
    VenueUser getVenue();
    LocalDateTime getDateAndTime();
    String getDescription();
    ArrayList<String> getTags(); // genres, maybe make genre object
    LocalDateTime getPostDate();
    String getAttachedMedia(); // change to media object
    int getId();

    String getDateAndTimeString();
    String getPostDateString();
    String getTagsString();
}
