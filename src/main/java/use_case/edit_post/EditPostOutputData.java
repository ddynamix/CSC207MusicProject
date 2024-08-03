package use_case.edit_post;

import entity.post.Post;

public class EditPostOutputData {
    private final Post getPostToEdit;

    public EditPostOutputData(Post getPostToEdit) {
        this.getPostToEdit = getPostToEdit;
    }

    public Post getGetPostToEdit() {
        return getPostToEdit;
    }
}
