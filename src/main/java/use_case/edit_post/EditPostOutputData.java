package use_case.edit_post;

import entity.post.Post;

/**
 * output data for edit post use case
 */
public class EditPostOutputData {
    private final Post getPostToEdit;

    /**
     * create instance in edit post use case
     * @param getPostToEdit current post
     */
    public EditPostOutputData(Post getPostToEdit) {
        this.getPostToEdit = getPostToEdit;
    }

    /**
     * access post to edit
     * @return post
     */
    public Post getGetPostToEdit() {
        return getPostToEdit;
    }
}
