package view_model;

import entity.event.Event;
import entity.user.User;

import java.util.ArrayList;

public class HomescreenState {
    private ArrayList<Event> events;
    private User signedInAs;

    public HomescreenState() {
        this.events = new ArrayList<>();
        this.signedInAs = null;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public User getSignedInAs() {
        return signedInAs;
    }

    public void setSignedInAs(User signedInAs) {
        this.signedInAs = signedInAs;
    }
}
