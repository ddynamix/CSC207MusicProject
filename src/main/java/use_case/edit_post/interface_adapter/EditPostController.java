package use_case.edit_post.interface_adapter;

import entity.post.Post;
import use_case.edit_post.EditPostInputBoundary;
import use_case.edit_post.EditPostInputData;

/**
 * controller for edit post use case
 */
public class EditPostController {
    private final EditPostInputBoundary editPostInteractor;

    /**
     * create instance of controller for edit post use case
     * @param editPostInteractor
     */
    public EditPostController(EditPostInputBoundary editPostInteractor) {
        this.editPostInteractor = editPostInteractor;
    }

    /**
     * update post
     * @param post new post
     */
    public void editPost(Post post) {
        editPostInteractor.editPost(new EditPostInputData(post));
    }

    /**
     * remove post
     * @param post to be removed
     */
    public void deletePost(Post post) {
        editPostInteractor.deletePost(new EditPostInputData(post));
    }

    /**
     * update post data
     * @param postToAlter current post
     * @param title of post
     * @param text of post
     * @param media of post
     */
    public void updatePost(Post postToAlter, String title, String text, String media) {
        editPostInteractor.updatePost(new EditPostInputData(postToAlter, title, text, media));
    }
}
