package entity.user;
import entity.post.Post;
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
     * add event to user
     * @param event to be added
     */
    @Override
    public void addEvent(Event event) {
        this.getMyEvents().add(event);
    }

    /**
     * remove event from user
     * @param event to be removed
     */
    @Override
    public void removeEvent(Event event) {this.getMyEvents().remove(event);}

}
