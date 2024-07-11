package entity.user;

import entity.event.Event;

public interface uploadable {
    public Event createNewEvent();
    public void contactFollowers();
    public Event updateEvent(Event event);
    public void removeEvent(Event event);
}
