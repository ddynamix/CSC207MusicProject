package entity;

import java.util.ArrayList;

public interface IUser {
    public String getUsername();
    public String getPassword();
    public String getEmail();
    public String getFirstName();
    public String getLastName();
    //public ArrayList<Events> getPastEvents();
    public ArrayList<User> getFollowers();
    public ArrayList<User> getFollowing();

}