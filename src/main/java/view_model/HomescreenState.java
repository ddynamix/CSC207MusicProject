package view_model;

import entity.post.Post;
import entity.user.User;

import java.util.ArrayList;

/**
 * State of homescreen
 */
public class HomescreenState {
    private ArrayList<Post> posts;
    private User signedInAs;

    /**
     * create instance of state
     */
    public HomescreenState() {
        this.posts = new ArrayList<>();
        this.signedInAs = null;
    }

    /**
     * update posts
     * @param posts to be set
     */
    public void setPosts(ArrayList<Post> posts) {this.posts = posts;}

    /**
     * return posts
     * @return arraylist of posts
     */
    public ArrayList<Post> getPosts() {
        return posts;
    }

    /**
     * access current logged-in user
     * @return signinas
     */
    public User getSignedInAs() {
        return signedInAs;
    }

    /**
     * set current logged-in user
     * @param signedInAs new login
     */
    public void setSignedInAs(User signedInAs) {
        this.signedInAs = signedInAs;
    }
}
