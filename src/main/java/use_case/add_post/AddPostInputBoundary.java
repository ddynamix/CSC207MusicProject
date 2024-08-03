package use_case.add_post;

public interface AddPostInputBoundary {
    void addPost(AddPostInputData inputData);
    void removePost(AddPostInputData inputData);
}
