package entity.user;

import entity.event.Event;

import java.util.ArrayList;

public interface IUser {
    public String getUsername();
    public String getPassword();
    public String getEmail();
    public String getFirstName();
    public String getLastName();
    public int getId();
    ArrayList<Event> getPastEvents();
    public ArrayList<User> getFollowers();
    public ArrayList<User> getFollowing();

}