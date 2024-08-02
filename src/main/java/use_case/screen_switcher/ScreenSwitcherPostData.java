package use_case.screen_switcher;

import entity.event.Event;
import entity.post.Post;
import entity.user.User;

import java.util.ArrayList;

public class ScreenSwitcherPostData {
    private ArrayList<Post> myPosts;
    private User signedInUser;

    public ScreenSwitcherPostData(ArrayList<Post> myPosts, User signedInUser) {
        this.myPosts = myPosts;
        this.signedInUser = signedInUser;
    }

    public ArrayList<Post> getPosts() {
        return myPosts;
    }

    public User getSignedInAs() {
        return signedInUser;
    }
}
