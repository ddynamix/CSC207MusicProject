package view_model;

import entity.post.Post;

/**
 * create post editor state
 */
public class PostEditorState {
    private Post postToEdit;

    /**
     * create state
     */
    public PostEditorState() {
        this.postToEdit = null;
    }

    /**
     * access post
     * @return post
     */
    public Post getPostToEdit() {
        return postToEdit;
    }

    /**
     * change post
     * @param postToEdit post to be set
     */
    public void setPostToEdit(Post postToEdit) {
        this.postToEdit = postToEdit;
    }
}
