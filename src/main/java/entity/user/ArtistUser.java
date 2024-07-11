package entity.user;

import entity.user.User;
import entity.event.Event;
import entity.event.EventFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ArtistUser extends User implements uploadable{
    public ArtistUser(){
        super();
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

    public Event updateEvent(Event event, String title, String venue, LocalDateTime time, String description, ArrayList<String> tags, String attachedMedia){
        if (event.getArtist() = this) {
            event.setTitle(title);
            event.setVenue(venue);
            event.setDateAndTime(time);
            event.setDescription(description);
            event.setTags(tags);
            event.setPostDate(LocalDateTime.now());
            event.setAttachedMedia(attachedMedia);
        }
        return event;
    };

    public void removeEvent(Event event){
        // remove id from database
    };
}
