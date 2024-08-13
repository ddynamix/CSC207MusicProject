package use_case.screen_switcher;

import entity.event.Event;
import entity.user.User;

import java.util.ArrayList;

/**
 * event data for switcher
 */
public class ScreenSwitcherMyEventsData {
    private ArrayList<Event> myEvents;
    private User signedInUser;

    /**
     * create instance of event data for switcher
     * @param myEvents event data
     * @param signedInUser current login
     */
    public ScreenSwitcherMyEventsData(ArrayList<Event> myEvents, User signedInUser) {
        this.myEvents = myEvents;
        this.signedInUser = signedInUser;
    }

    /**
     * access events
     * @return events
     */
    public ArrayList<Event> getMyEvents() {
        return myEvents;
    }

    /**
     * access current login
     * @return curren login
     */
    public User getSignedInUser() {
        return signedInUser;
    }
}
