package use_case.edit_post;

/**
 * interface for output data for edit post use case
 */
public interface EditPostOutputBoundary {
    void goToPostEditor(EditPostOutputData outputData);
    void prepareSuccessView(EditPostsSuccessOutputData outputData);
}
