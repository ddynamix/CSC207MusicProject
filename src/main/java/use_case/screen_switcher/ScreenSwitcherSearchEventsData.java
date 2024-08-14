package use_case.screen_switcher;

import entity.event.Event;

import java.util.ArrayList;

/**
 * search event data for switcher
 */
public class ScreenSwitcherSearchEventsData {
    private final ArrayList<Event> allEvents;

    /**
     * set events
     * @param allEvents data
     */
    public ScreenSwitcherSearchEventsData(ArrayList<Event> allEvents) {
        this.allEvents = allEvents;
    }

    /**
     * access events
     * @return events
     */
    public ArrayList<Event> getAllEvents() {
        return allEvents;
    }
}
