package use_case.edit_post;

public interface EditPostOutputBoundary {
    void goToPostEditor(EditPostOutputData outputData);
    void prepareSuccessView(EditPostsSuccessOutputData outputData);
}
