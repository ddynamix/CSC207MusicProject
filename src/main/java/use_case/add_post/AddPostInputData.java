package use_case.add_post;

import entity.post.Post;

/**
 * input data for add post use case
 */
public class AddPostInputData {
    private final Post postToAddOrRemove;

    /**
     * create instance of input data
     * @param postToAddOrRemove data to be changed
     */
    public AddPostInputData(Post postToAddOrRemove) {
        this.postToAddOrRemove = postToAddOrRemove;
    }

    /**
     * access post
     * @return post
     */
    public Post getPostToAddOrRemove() {
        return postToAddOrRemove;
    }
}
