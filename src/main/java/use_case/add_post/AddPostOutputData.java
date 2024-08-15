package use_case.add_post;

import entity.post.Post;

import java.util.ArrayList;

/**
 * ouput data for add post use case
 */
public class AddPostOutputData {
    private final ArrayList<Post> postsToDisplay;

    /**
     * create instance of outputdata
     * @param postsToDisplay ArrayList of posts to add to data
     */
    public AddPostOutputData(ArrayList<Post> postsToDisplay) {
        this.postsToDisplay = postsToDisplay;
    }

    /**
     * access posts
     * @return list posts
     */
    public ArrayList<Post> getPostsToDisplay() {
        return postsToDisplay;
    }
}
