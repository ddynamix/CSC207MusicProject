package entity.user;

import entity.event.Event;

/**
 * Uploadable interface for User types that can create events on the platform <br>
 * Implemented by VenueUser and AudienceUser
 */
public interface uploadable {
    void addEvent(Event event);
    void removeEvent(Event event);
}
