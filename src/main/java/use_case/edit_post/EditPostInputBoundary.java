package use_case.edit_post;

/**
 * interface for input data for edit post use case
 */
public interface EditPostInputBoundary {
    void editPost(EditPostInputData inputData);
    void deletePost(EditPostInputData inputData);
    void updatePost(EditPostInputData inputData);
}
