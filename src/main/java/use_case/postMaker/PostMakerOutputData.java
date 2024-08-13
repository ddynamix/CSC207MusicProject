package use_case.postMaker;

import entity.post.Post;

import java.util.ArrayList;

/**
 * output data for post use case
 */
public class PostMakerOutputData {
    private final ArrayList<Post> posts;

    /**
     * create instance of output data for post use case
     * @param posts to be listed
     */
    public PostMakerOutputData(ArrayList<Post> posts) {
        this.posts = posts;
    }

    /**
     * access posts
     * @return posts
     */
    public ArrayList<Post> getPosts() {
        return posts;
    }
}
