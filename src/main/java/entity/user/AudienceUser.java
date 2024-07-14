package entity.user;

import entity.user.User;
import entity.event.Event;
import entity.event.EventFactory;
import entity.post.Post;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class AudienceUser extends User{
    private ArrayList<Event> upcoming;

    public AudienceUser(String username, String password, String email, String firstName, String lastName, int id, ArrayList<User> followers, ArrayList<User> following, ArrayList<Event> pastEvents) {
        super(username, password, email, firstName, lastName, id, followers, following, pastEvents);
        this.upcoming = new ArrayList<>();

    }

    public Post post(String title, String text, String attachedMedia){
        return new Post(title, text, this, attachedMedia);
    }

    public Post share(String title, String message, String media){
        var p = new Post(title, message, this, media);
        for (User user: this.getFollowing()){
            p.share(user);
        }
        return p;
    }

}

