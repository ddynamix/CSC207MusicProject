package entity.user;

import entity.event.Event;

import java.util.ArrayList;

public class UserFactory {
    public User createUser(String username, String password, String email, String firstName, String lastName) {
        int id = 0; // This is a placeholder value, the actual id will be assigned by the database
        ArrayList<User> followers = new ArrayList<>();
        ArrayList<User> following = new ArrayList<>();
        ArrayList<Event> pastEvents = new ArrayList<>();
        return new User(username, password, email, firstName, lastName, id, followers, following, pastEvents);
    }
}
