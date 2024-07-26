package use_case.eventscreen.interface_adapter;

import entity.event.Event;
import entity.user.User;

import java.util.ArrayList;

public class EventScreenState {
    private User signedInAs;
    private ArrayList<Event> events;

    public EventScreenState() {
        this.events = new ArrayList<>();
        this.signedInAs = null;
    }

    public User getSignedInAs() {
        return signedInAs;
    }

    public void setSignedInAs(User signedInAs) {
        this.signedInAs = signedInAs;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }
}
