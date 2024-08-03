package use_case.edit_post;

public interface EditPostInputBoundary {
    void editPost(EditPostInputData inputData);
    void deletePost(EditPostInputData inputData);
    void updatePost(EditPostInputData inputData);
}
