package view_model;

import entity.post.Post;

public class PostEditorState {
    private Post postToEdit;

    public PostEditorState() {
        this.postToEdit = null;
    }

    public Post getPostToEdit() {
        return postToEdit;
    }

    public void setPostToEdit(Post postToEdit) {
        this.postToEdit = postToEdit;
    }
}
