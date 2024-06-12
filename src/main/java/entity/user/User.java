package entity.user;

import entity.event.Event;

import java.util.ArrayList;

public class User implements IUser {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private int id = 0; //figure out how to implement ids probably in factory
    private ArrayList<User> followers;
    private ArrayList<User> following;
    private ArrayList<Event> pastEvents;

    //private Song featuredSong;
    //private ArrayList<Event> pastEvents;


    public User(String username, String password, String email, String firstName, String lastName, int id, ArrayList<User> followers, ArrayList<User> following, ArrayList<Event> pastEvents) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.followers = followers;
        this.following = following;
        this.pastEvents = pastEvents;
    }

    @Override
    public String getUsername() {
        return username;
    }

    // Encrypt somehow??
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public ArrayList<User> getFollowers() {
        return followers;
    }

    @Override
    public ArrayList<User> getFollowing() {
        return following;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public ArrayList<Event> getPastEvents() {
        return pastEvents;
    }

    //    public ArrayList<Event> getPastEvents() {
//        return pastEvents;
//    }

//    public void setPastEvents(ArrayList<Event> pastEvents) {
//        this.pastEvents = pastEvents;
//    }
}