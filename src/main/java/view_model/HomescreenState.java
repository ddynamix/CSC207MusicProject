package view_model;

import entity.event.Event;
import entity.post.Post;
import entity.user.User;

import java.util.ArrayList;

public class HomescreenState {
    private ArrayList<Post> posts;
    private User signedInAs;

    public HomescreenState() {
        this.posts = new ArrayList<>();
        this.signedInAs = null;
    }

    public void setPosts(ArrayList<Post> posts) {this.posts = posts;}

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public User getSignedInAs() {
        return signedInAs;
    }

    public void setSignedInAs(User signedInAs) {
        this.signedInAs = signedInAs;
    }
}
