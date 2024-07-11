package entity.user;
import entity.user.User;
import entity.event.Event;
import entity.EventFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class VenueUser extends User implements uploadable{
    private String location;
    public VenueUser(){
        super();
        this.location = null;
    }

    public Event createNewEvent(){

        Event e = new Event();
        // how do I represent user data as variables or inputs
        return e;
    };

    public void contactFollowers(){
        this.getFollowers();

        // contact somehow

    };

    public Event updateEvent(Event event, String title, User artist, LocalDateTime time, String description, ArrayList<String> tags, String attachedMedia){
        if (event.venue = this.name){
            event.setTitle(title);
            event.setArtist(artist);
            event.setDateAndTime(time);
            event.setDescription(description);
            event.setTags(tags);
            event.setPostDate(LocalDateTime.now());
            event.setAttachedMedia(attachedMedia);
        }
        return event;
    };

    public void removeEvent(Event event){
        if (event.venue = this.name){
            // remove from database
            event.id // ?
        }
    };

    public void updateLocation(String location){ this.location = location; }
}
