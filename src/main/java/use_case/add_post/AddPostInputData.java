package use_case.add_post;

import entity.post.Post;

public class AddPostInputData {
    private final Post postToAddOrRemove;

    public AddPostInputData(Post postToAddOrRemove) {
        this.postToAddOrRemove = postToAddOrRemove;
    }

    public Post getPostToAddOrRemove() {
        return postToAddOrRemove;
    }
}
