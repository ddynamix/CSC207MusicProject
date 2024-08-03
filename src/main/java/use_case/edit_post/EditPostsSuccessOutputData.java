package use_case.edit_post;

import entity.post.Post;

import java.util.ArrayList;

public class EditPostsSuccessOutputData {
    private final ArrayList<Post> posts;

    public EditPostsSuccessOutputData(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }
}
