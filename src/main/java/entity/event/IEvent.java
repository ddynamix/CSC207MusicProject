package entity.event;

import entity.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface IEvent {
    public String getTitle();
    public User getArtist();
    public String getVenue();
    public LocalDateTime getDateAndTime();
    public String getDescription();
    public ArrayList<String> getTags(); // genres, maybe make genre object
    public LocalDateTime getPostDate();
    public String getAttachedMedia(); // change to media object
    public int getId();

    public String getDateAndTimeString();
    public String getPostDateString();
    public String getTagsString();
}
