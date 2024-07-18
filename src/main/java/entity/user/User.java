package entity.user;

import entity.event.Event;
import entity.post.Post;

import java.util.ArrayList;

/**
 * Parent class User
 */
public abstract class User {
    private String username;
    private String password;
    private String email;

    private ArrayList<User> followers;
    private ArrayList<User> following;
    private ArrayList<Event> upcomingEvents;
    private ArrayList<Event> pastEvents;
    private int id; //figure out how to implement ids

    /**
     * Create instance of the User class <br>
     * Child classes: AudienceUser, ArtistUser, VenueUser
     *
     * @param username  String  username for this user
     * @param password  String  password of this user
     * @param email     String  email of this user
     */
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;

        this.followers = new ArrayList<>();
        this.following = new ArrayList<>();
        this.upcomingEvents = new ArrayList<>();
        this.pastEvents = new ArrayList<>();
        this.id = 0;
    }

    /**
     * Return the username
     * @return  String  username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Return the password
     * @return  String  password
     *
     */
    public String getPassword() {
        // Encrypt somehow??
        return password;
    }

    /**
     * Return the email
     * @return  String  email
     */

    public String getEmail() {
        return email;
    }

    public ArrayList<User> getFollowers() {
        return followers;
    }

    public ArrayList<User> getFollowing() {
        return following;
    }

    public ArrayList<Event> getUpcomingEvents() {
        return upcomingEvents;
    }

    public ArrayList<Event> getPastEvents() {
        return pastEvents;
    }

    public int getId() {
        return id;
    }
}