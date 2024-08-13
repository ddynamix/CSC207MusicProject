package use_case.edit_post;

import entity.post.Post;

import java.util.ArrayList;

/**
 * correct output data for edit post use case
 */
public class EditPostsSuccessOutputData {
    private final ArrayList<Post> posts;

    /**
     * create instance of correct output data for edit post use case
     * @param posts
     */
    public EditPostsSuccessOutputData(ArrayList<Post> posts) {
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
