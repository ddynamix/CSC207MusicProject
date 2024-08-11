package entity.user;

import entity.event.Event;
import entity.post.Post;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Uploadable interface for User types that can create events on the platform <br>
 * Implemented by VenueUser and AudienceUser
 */
public interface uploadable {
    public void contactFollowers(Post post);
    public void updateEventTitle(Event event, String newTitle);
    public void updateEventVenue(Event event, VenueUser newVenue);
    public void updateEventTime(Event event, LocalDateTime newTime);
    public void updateEventDescription(Event event, String newDescription);
    public void updateEventTags(Event event, ArrayList<String> newTags);
    public void updateEventMedia(Event event, String newMedia);
    public void removeEvent(Event event);
}
