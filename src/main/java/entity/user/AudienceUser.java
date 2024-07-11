package entity.user;

import entity.user.User;
import entity.event.Event;
import entity.EventFactory;
import entity.post.Post;

import java.util.ArrayList;

public class AudienceUser extends User{
    private ArrayList<Event> upcoming;

    public AudienceUser() {
        super();
        this.upcoming = [];

    }

    public Post post(){
        Post p = new Post();
        return p;
    }

    public Post share(<T> data){
        // GENERIC TYPE so it could share post or event
        p = new Post(title, text, this, date.now(), data, rating);
        this.getFollowing(); // contact somehow
        return p;
    }

}

