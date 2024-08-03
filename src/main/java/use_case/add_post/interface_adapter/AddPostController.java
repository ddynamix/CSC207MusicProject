package use_case.add_post.interface_adapter;

import entity.post.Post;
import use_case.add_post.AddPostInputBoundary;
import use_case.add_post.AddPostInputData;

public class AddPostController {
    private final AddPostInputBoundary addPostInteractor;

    public AddPostController(AddPostInputBoundary addPostInteractor) {
        this.addPostInteractor = addPostInteractor;
    }

    public void addPost(Post post) {
        AddPostInputData inputData = new AddPostInputData(post);
        addPostInteractor.addPost(inputData);
    }

    public void removePost(Post post) {
        AddPostInputData inputData = new AddPostInputData(post);
        addPostInteractor.removePost(inputData);
    }
}
