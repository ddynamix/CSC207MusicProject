package use_case.screen_switcher;

import entity.event.Event;
import entity.user.User;

import java.util.ArrayList;

public class ScreenSwitcherMyEventsData {
    private ArrayList<Event> myEvents;
    private User signedInUser;

    public ScreenSwitcherMyEventsData(ArrayList<Event> myEvents, User signedInUser) {
        this.myEvents = myEvents;
        this.signedInUser = signedInUser;
    }

    public ArrayList<Event> getMyEvents() {
        return myEvents;
    }

    public User getSignedInUser() {
        return signedInUser;
    }
}
