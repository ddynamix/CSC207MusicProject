package entity.user;

import entity.event.Event;
import entity.post.Post;

import java.util.ArrayList;

/**
 * Parent class User
 */
public class User {
    private String username;
    private String password;
    private String email;
    private String name;

    private String id;

    private ArrayList<User> followers;
    private ArrayList<User> following;
    private ArrayList<Event> pastEvents;

    //private Song featuredSong;

    /**
     * Create instance of the User class <br>
     * Child classes: AudienceUser, ArtistUser, VenueUser
     *
     * @param name  String  name of user
     * @param username  String  username for this user
     * @param password  String  password of this user
     * @param email     String  email of this user
     */
    public User(String name, String username, String password, String email) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.id = "null"; // this is a placeholder value to be reassigned by the database

        this.followers = new ArrayList<>();
        this.following = new ArrayList<>();
        this.pastEvents = new ArrayList<>();
    }

    /**
     * Return the username
     * @return  String  username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Return the name
     * @return  String  name
     */
    public String getName() {
        return name;
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


    /**
     * Return followers
     * @return  list    followers
     */
    public ArrayList<User> getFollowers() {
        return followers;
    }

    /**
     * Return following
     * @return  list    following
     */
    public ArrayList<User> getFollowing() {
        return following;
    }

    /**
     * Return ID in database
     * @return  String id
     */
    public String getId() {
        return this.id; // should be the id
    }

    /**
     * Set ID in database
     * @param id    String id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Return past events
     * @return  list    pastEvents
     */
    public ArrayList<Event> getPastEvents() {
        return pastEvents;
    }
}