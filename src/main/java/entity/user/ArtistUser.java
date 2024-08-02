package entity.user;

import entity.event.Event;
import entity.post.Post;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * ArtistUser class
 */
public class ArtistUser extends User implements uploadable {

    /**
     * Create new instance of ArtistUser
     * @param name  String                  name
     * @param username  String              username
     * @param password  String              password
     * @param email     String              email
     */
    public ArtistUser(String name, String username, String password, String email){
        super(name, username, password, email);
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
    public void updateEventVenue(Event event, VenueUser newVenue) {
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
}
