package usecase.homescreen;

import entity.event.Event;

import java.util.ArrayList;

public class HomescreenGetEventOutputData {
    private ArrayList<Event> events;

    public HomescreenGetEventOutputData(ArrayList<Event> events) {
        this.events = events;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }
}
