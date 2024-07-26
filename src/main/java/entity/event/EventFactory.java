package entity.event;

import entity.user.ArtistUser;
import entity.user.VenueUser;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class EventFactory {
    public Event createEvent(String title, ArtistUser artist, VenueUser venue, LocalDateTime dateAndTime, String description, ArrayList<String> tags, String attachedMedia) {
        LocalDateTime postDate = LocalDateTime.now();
        int id = 0; // This is a placeholder value, the actual id will be assigned by the database
        return new Event(title, artist, venue, dateAndTime, description, tags, postDate, attachedMedia);
    }
}
