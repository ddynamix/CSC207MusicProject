package view_model;

import entity.post.Post;
import entity.user.ArtistUser;
import entity.user.User;
import entity.user.VenueUser;

import java.util.ArrayList;

public class PostMakerState {
    private User signedInAs;
    private ArrayList<Post> posts;

    public PostMakerState() {
        this.signedInAs = null;
        this.posts = new ArrayList<>();
    }

    public void setSignedInAs(User signedInAs) {
        this.signedInAs = signedInAs;
    }

    public User getSignedInAs() {
        return signedInAs;
    }

    public void setPosts(ArrayList<Post> postsToDisplay) {

    }
}
