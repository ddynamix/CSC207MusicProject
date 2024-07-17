package entity.user;

import entity.event.Event;
import entity.post.Post;

import java.util.ArrayList;

/**
 * Parent class User
 */
public class User implements IUser {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String id;
    private ArrayList<User> followers;
    private ArrayList<User> following;
    private ArrayList<Event> pastEvents;

    //private Song featuredSong;

    /**
     * Create instance of the User class <br>
     * Child classes: AudienceUser, ArtistUser, VenueUser
     *
     * @param username  String  username for this user
     * @param password  String  password of this user
     * @param email     String  email of this user
     * @param firstName String  first name of this user
     * @param lastName  String  last name of this user
     */
    public User(String username, String password, String email, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = "null"; // dummy value to be replaced by database
        this.followers = new ArrayList<>();
        this.following = new ArrayList<>();
        this.pastEvents = new ArrayList<>();
    }


    /**
     * Return the username
     * @return  String  username
     */
    @Override
    public String getUsername() {
        return username;
    }

    // Encrypt somehow??

    /**
     * Return the password
     * @return  String  password
     *
     */
    @Override
    public String getPassword() { //private?
        return password;
    }

    /**
     * Return the email
     * @return  String  email
     */
    @Override
    public String getEmail() {
        return email;
    }

    /**
     * Return the first name
     * @return  String  firstName
     */
    @Override
    public String getFirstName() {
        return firstName;
    }

    /**
     * Return the last name
     * @return  String  lastName
     */
    @Override
    public String getLastName() {
        return lastName;
    }

    /**
     * Return followers
     * @return  list    followers
     */
    @Override
    public ArrayList<User> getFollowers() {
        return followers;
    }

    /**
     * Return following
     * @return  list    following
     */
    @Override
    public ArrayList<User> getFollowing() {
        return following;
    }

    /**
     * Return ID in database
     * @return  int id
     */
    @Override
    public String getId() {
        return this.id; // should be the id
    }

    /**
     * Set ID in database
     * @param id    int id
     */
    @Override
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Return past events
     * @return  list    pastEvents
     */
    @Override
    public ArrayList<Event> getPastEvents() {
        return pastEvents;
    }
}