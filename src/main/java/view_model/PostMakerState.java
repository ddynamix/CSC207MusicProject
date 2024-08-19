package view_model;

import entity.post.Post;
import entity.user.User;

import java.util.ArrayList;

/**
 * state for post maker
 */
public class PostMakerState {
    private User signedInAs;
    private ArrayList<Post> posts;

    /**
     * create instance of state
     */
    public PostMakerState() {
        this.signedInAs = null;
        this.posts = new ArrayList<>();
    }

    /**
     * change current login
     * @param signedInAs to be set
     */
    public void setSignedInAs(User signedInAs) {
        this.signedInAs = signedInAs;
    }

    /**
     * access current login
     * @return current login
     */
    public User getSignedInAs() {
        return signedInAs;
    }

    /**
     * change posts
     * @param postsToDisplay to be set
     */
    public void setPosts(ArrayList<Post> postsToDisplay) {
        posts = postsToDisplay;
    }

    /**
     * access posts
     * @return posts
     */
    public ArrayList<Post> getPosts() {
        return posts;
    }
}
