package data_access;

import entity.event.Event;
import entity.user.User;

/**
 * interface for user -> events DAO
 */
public interface UsersEventsRelationalAccessInterface {
    void addEvent(User user, Event event);
    void removeEvent(User user, Event event);
}
