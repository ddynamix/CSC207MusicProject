package use_case.screen_switcher;

import entity.event.Event;

import java.util.ArrayList;

public class ScreenSwitcherSearchEventsData {
    private final ArrayList<Event> allEvents;

    public ScreenSwitcherSearchEventsData(ArrayList<Event> allEvents) {
        this.allEvents = allEvents;
    }

    public ArrayList<Event> getAllEvents() {
        return allEvents;
    }
}
