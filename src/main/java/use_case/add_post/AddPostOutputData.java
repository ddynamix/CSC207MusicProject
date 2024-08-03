package use_case.add_post;

import entity.post.Post;

import java.util.ArrayList;

public class AddPostOutputData {
    private final ArrayList<Post> postsToDisplay;

    public AddPostOutputData(ArrayList<Post> postsToDisplay) {
        this.postsToDisplay = postsToDisplay;
    }

    public ArrayList<Post> getPostsToDisplay() {
        return postsToDisplay;
    }
}
