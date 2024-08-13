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
    void addEvent(Event event);
    void removeEvent(Event event);
}
