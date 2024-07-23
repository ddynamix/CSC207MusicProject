package interface_adapter.homescreen;

import entity.event.Event;
import entity.user.User;

import java.util.ArrayList;

public class HomescreenState {
    private User signedInAs;
    private ArrayList<Event> events;

    public HomescreenState() {
        this.signedInAs = null;
        this.events = new ArrayList<>();
    }

    public User getSignedInAs() {
        return signedInAs;
    }

    public void setSignedInAs(User user) {
        this.signedInAs = user;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }
}
