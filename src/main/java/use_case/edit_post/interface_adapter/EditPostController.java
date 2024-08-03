package use_case.edit_post.interface_adapter;

import entity.post.Post;
import use_case.edit_post.EditPostInputBoundary;
import use_case.edit_post.EditPostInputData;

public class EditPostController {
    private final EditPostInputBoundary editPostInteractor;

    public EditPostController(EditPostInputBoundary editPostInteractor) {
        this.editPostInteractor = editPostInteractor;
    }

    public void editPost(Post post) {
        editPostInteractor.editPost(new EditPostInputData(post));
    }

    public void deletePost(Post post) {
        editPostInteractor.deletePost(new EditPostInputData(post));
    }

    public void updatePost(Post postToAlter, String title, String text, String postDate, String media) {
        editPostInteractor.updatePost(new EditPostInputData(postToAlter, title, text, postDate, media));
    }
}
