package entity.user;
import entity.post.Post;
import entity.user.User;
import entity.event.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class VenueUser extends User implements uploadable{
    private String location;
    public VenueUser(String username, String password, String email, String firstName, String lastName, int id, ArrayList<User> followers, ArrayList<User> following, ArrayList<Event> pastEvents){
        super(username, password, email, firstName, lastName, id, followers, following, pastEvents);
        this.location = null;
    }

    @Override
    public ArrayList<Event> getPastEvents() {
        return super.getPastEvents();
    }

    @Override
    public Event createNewEvent(String title, User artist, String venue, LocalDateTime dateAndTime, String description, ArrayList<String> tags, LocalDateTime postDate, String attachedMedia, int id) {
        return new Event(title, artist, this.getFirstName(), dateAndTime, description, tags, postDate, attachedMedia);
    }

    @Override
    public void contactFollowers(Post postToShare){
        for (User user: this.getFollowers()){
            postToShare.share(user);
        }
    }

    @Override
    public void updateEventTitle(Event event, String newTitle) {
        event.setTitle(newTitle);
    }

    @Override
    public void updateEventVenue(Event event, String newVenue) {
        event.setVenue(newVenue);
    }

    @Override
    public void updateEventTime(Event event, LocalDateTime newTime) {
        event.setDateAndTime(newTime);
    }

    @Override
    public void updateEventDescription(Event event, String newDescription) {
        event.setDescription(newDescription);
    }

    @Override
    public void updateEventTags(Event event, ArrayList<String> newTags) {
        event.setTags(newTags);
    }

    @Override
    public void updateEventMedia(Event event, String newMedia) {
        event.setAttachedMedia(newMedia);
    }

    @Override
    public void removeEvent(Event event){
        //if (event.venue = this.name){
            //event.id // ?
        //}
    };

    public void updateLocation(String location){ this.location = location; }
}
