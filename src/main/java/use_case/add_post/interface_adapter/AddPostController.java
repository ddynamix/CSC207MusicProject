package use_case.add_post.interface_adapter;

import entity.post.Post;
import use_case.add_post.AddPostInputBoundary;
import use_case.add_post.AddPostInputData;

/**
 * controller for add post use case
 */
public class AddPostController {
    private final AddPostInputBoundary addPostInteractor;

    /**
     * create controller for add post controller
     * @param addPostInteractor interactor information
     */
    public AddPostController(AddPostInputBoundary addPostInteractor) {
        this.addPostInteractor = addPostInteractor;
    }

    /**
     * add new post
     * @param post new data
     */
    public void addPost(Post post) {
        AddPostInputData inputData = new AddPostInputData(post);
        addPostInteractor.addPost(inputData);
    }

    /**
     * remove post
     * @param post to be removed
     */
    public void removePost(Post post) {
        AddPostInputData inputData = new AddPostInputData(post);
        addPostInteractor.removePost(inputData);
    }
}
