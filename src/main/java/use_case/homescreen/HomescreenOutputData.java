package use_case.homescreen;

import entity.event.Event;

import java.util.ArrayList;

public class HomescreenOutputData {
    private ArrayList<Event> myEvents;

    public HomescreenOutputData(ArrayList<Event> myEvents) {
        this.myEvents = myEvents;
    }

    public ArrayList<Event> getMyEvents() {
        return myEvents;
    }
}
