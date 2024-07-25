package use_case.homescreen;

import entity.event.Event;
import entity.user.User;

import java.util.ArrayList;

public class HomescreenOutputData {
    private ArrayList<Event> myEvents;
    private User signedInAs;

    public HomescreenOutputData(ArrayList<Event> myEvents, User signedInAs) {
        this.myEvents = myEvents;
        this.signedInAs = signedInAs;
    }

    public ArrayList<Event> getMyEvents() {
        return myEvents;
    }

    public User getSignedInAs() {
        return signedInAs;
    }
}
