package entity.event;

import entity.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface IEvent {
    public String getTitle();
    public User getArtist(); // change to ArtistUser
    public String getVenue(); // change to VenueUser
    public LocalDateTime getDateAndTime();
    public String getDescription();
    public ArrayList<String> getTags(); // genres, maybe make genre object
    public LocalDateTime getPostDate();
    public String getAttachedMedia(); // change to media object
    public int getId();
}
