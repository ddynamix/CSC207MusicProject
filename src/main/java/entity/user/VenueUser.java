package entity.user;
import entity.post.Post;
import entity.user.User;
import entity.event.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * VenueUser class
 */
public class VenueUser extends User implements uploadable{

    /**
     * Create new instance of ArtistUser
     * @param name      String              name
     * @param username  String              username
     * @param password  String              password
     * @param email     String              email
     */
    public VenueUser(String name, String username, String password, String email) {
        super(name, username, password, email);
    }

    /**
     * Return past events
     * @return  ArrayList<Event>    pastEvents</Event>
     */
    @Override
    public ArrayList<Event> getPastEvents() {
        return super.getPastEvents();
    }

    /**
     * Create new instance of the event class
     * @param title         String  event.title
     * @param artist        User    event.artist
     * @param venue         String  event.venue
     * @param dateAndTime   LocalDateTime   event.dateAndTime
     * @param description   String  event.description
     * @param tags          ArrayList<String>   event.tags</String>
     * @param postDate      LocalDateTime   event.postDate
     * @param attachedMedia String  event.attachedMedia
     * @param id            int     event.id
     * @return event created
     */
    @Override
    public Event createNewEvent(String title, User artist, String venue, LocalDateTime dateAndTime, String description, ArrayList<String> tags, LocalDateTime postDate, String attachedMedia, int id) {
        return new Event(title, artist, this.getName(), dateAndTime, description, tags, postDate, attachedMedia);
    }

    /**
     * Contact the followers of this user
     * @param postToShare   Post    content being sent
     */
    @Override
    public void contactFollowers(Post postToShare){
        for (User user: this.getFollowers()){
            postToShare.share(user);
        }
    }

    /**
     * Update event.title
     * @param event     Event
     * @param newTitle  String
     */
    @Override
    public void updateEventTitle(Event event, String newTitle) {
        event.setTitle(newTitle);
    }

    /**
     * Update event.venue
     * @param event     Event
     * @param newVenue  String
     */
    @Override
    public void updateEventVenue(Event event, String newVenue) {
        event.setVenue(newVenue);
    }

    /**
     * Update event.dataAndTime
     * @param event     Event
     * @param newTime   LocalDateTime
     */
    @Override
    public void updateEventTime(Event event, LocalDateTime newTime) {
        event.setDateAndTime(newTime);
    }

    /**
     * Update event.description
     * @param event             Event
     * @param newDescription    String
     */
    @Override
    public void updateEventDescription(Event event, String newDescription) {
        event.setDescription(newDescription);
    }

    /**
     * Update event.tags
     * @param event     Event
     * @param newTags   ArrayList<String>
     */
    @Override
    public void updateEventTags(Event event, ArrayList<String> newTags) {
        event.setTags(newTags);
    }

    /**
     * Update event.attachedMedia
     * @param event     Event
     * @param newMedia  String
     */
    @Override
    public void updateEventMedia(Event event, String newMedia) {
        event.setAttachedMedia(newMedia);
    }

    /**
     * Remove event from database
     * @param event     Event   to be removed
     */
    @Override
    public void removeEvent(Event event){
        //if (event.venue = this.name){
            //event.id // ?
        //}
    };
}
