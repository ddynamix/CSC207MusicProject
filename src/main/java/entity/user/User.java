package entity.user;

import entity.event.Event;
import entity.post.Post;
import entity.song.Song;

import java.util.ArrayList;

/**
 * Parent class User
 */
public abstract class User {
    private String username;
    private String password;
    private String email;
    private String name;

    private String id;

    private ArrayList<User> followers;
    private ArrayList<User> following;
    private ArrayList<Event> myEvents;
    private ArrayList<Post> myPosts;

    private Song featuredSong;

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
        this.myEvents = new ArrayList<>();
        this.myPosts = new ArrayList<>();
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

    public void addFollower(User newFollow){
        this.followers.add(newFollow);
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
     * Set email in database
     * @param email    String id
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Set name in database
     * @param name    String id
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set username in database
     * @param username    String id
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Return  events
     * @return  list    pastEvents
     */
    public ArrayList<Event> getMyEvents() {
        return myEvents;
    }

    /**
     * Return posts
     * @return  list    myPosts
     */
    public ArrayList<Post> getMyPosts() {
        return myPosts;
    }

    public void addEvent(Event event) {
        this.myEvents.add(event);
        System.out.println("User has " + this.getMyEvents().size() + " events");
        System.out.println("Event " + event.getTitle() + " added to user");
    }

    public void removeEvent(Event event) {
        this.myEvents.remove(event);
    }

    public void removeFollower(User follower) {
        this.followers.remove(follower);
    }

    public void addFollowing(User following) {
        this.following.add(following);
    }

    public void removeFollowing(User following) {
        this.following.remove(following);
    }

    @Override
    public String toString() {
        return this.username;
    }

    public void addPost(Post post) {this.myPosts.add(post);
        System.out.println("User has " + this.getMyPosts().size() + " posts");
        System.out.println("Post " + post.getTitle() + " added to user");
    }

    public void removePost(Post post) {this.myPosts.remove((post));}

    public int getNumFollowers() {
        return this.followers.size();
    }

    public int getNumFollowing() {
        return this.following.size();
    }

    public Song getFeaturedSong() {
        return featuredSong;
    }

    public void setFeaturedSong(Song featuredSong) {
        this.featuredSong = featuredSong;
    }
}