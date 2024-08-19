package entity.event;

import entity.user.ArtistUser;
import entity.user.VenueUser;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * create events
 */
public class EventFactory {
    /**
     * create instance of Event entity
     * @param title of event
     * @param artist username of artist of event
     * @param venue of event
     * @param dateAndTime of event
     * @param description of event
     * @param tags applied to event
     * @param attachedMedia of event
     * @return Event created
     */
    public Event createEvent(String title, ArtistUser artist, VenueUser venue, LocalDateTime dateAndTime, String description, ArrayList<String> tags, String attachedMedia) {
        LocalDateTime postDate = LocalDateTime.now();
        int id = 0; // This is a placeholder value, the actual id will be assigned by the database
        return new Event(title, artist, venue, dateAndTime, description, tags, postDate, attachedMedia);
    }
}
