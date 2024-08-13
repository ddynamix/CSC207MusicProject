package view_model;

import entity.event.Event;
import entity.user.User;

import java.util.ArrayList;

/**
 * State of Event Screen
 */
public class EventScreenState {
    private User signedInAs;
    private ArrayList<Event> events;

    /**
     * create instance of state
     */
    public EventScreenState() {
        this.events = new ArrayList<>();
        this.signedInAs = null;
    }

    /**
     * Access currently logged in
     * @return user logged in
     */
    public User getSignedInAs() {
        return signedInAs;
    }

    /**
     * set current sign in
     * @param signedInAs new login
     */
    public void setSignedInAs(User signedInAs) {
        this.signedInAs = signedInAs;
    }

    /**
     * access events
     * @return list of events
     */
    public ArrayList<Event> getEvents() {
        return events;
    }

    /**
     * update events
     * @param events to be set
     */
    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }
}
