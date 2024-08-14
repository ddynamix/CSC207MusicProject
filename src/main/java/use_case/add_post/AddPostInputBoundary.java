package use_case.add_post;

/**
 * interface for input data
 */
public interface AddPostInputBoundary {
    void addPost(AddPostInputData inputData);
    void removePost(AddPostInputData inputData);
}
