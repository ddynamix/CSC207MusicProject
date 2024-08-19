package use_case.screen_switcher;

import entity.post.Post;
import entity.user.User;

import java.util.ArrayList;

/**
 * post data for switcher
 */
public class ScreenSwitcherPostData {
    private ArrayList<Post> myPosts;
    private User signedInUser;

    /**
     * create instance of post data for switcher
     * @param myPosts post data
     * @param signedInUser current login
     */
    public ScreenSwitcherPostData(ArrayList<Post> myPosts, User signedInUser) {
        this.myPosts = myPosts;
        this.signedInUser = signedInUser;
    }

    /**
     * access posts
     * @return posts
     */
    public ArrayList<Post> getPosts() {
        return myPosts;
    }

    /**
     * access current login
     * @return current login
     */
    public User getSignedInAs() {
        return signedInUser;
    }
}
