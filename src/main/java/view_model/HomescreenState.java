package view_model;

import entity.event.Event;
import entity.post.Post;
import entity.user.User;

import java.util.ArrayList;

/**
 * State of homescreen
 */
public class HomescreenState {
    private ArrayList<Event> events;
    private ArrayList<Post> posts;
    private User signedInAs;

    /**
     * create instance of state
     */
    public HomescreenState() {
        this.events = new ArrayList<>();
        this.posts = new ArrayList<>();
        this.signedInAs = null;
    }

    /**
     * access events
     * @return list of events
     */
    public ArrayList<Event> getEvents() {
        return events;
    }

    /**
     * update posts
     * @param posts to be set
     */
    public void setPosts(ArrayList<Post> posts) {this.posts = posts;}

    /**
     * update events
     * @param events to be set
     */
    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    /**
     * access current logged in user
     * @return signinas
     */
    public User getSignedInAs() {
        return signedInAs;
    }

    /**
     * set current signed in user
     * @param signedInAs new login
     */
    public void setSignedInAs(User signedInAs) {
        this.signedInAs = signedInAs;
    }
}
