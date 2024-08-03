package use_case.edit_post;

import app.interface_adapter_tools.UserSession;
import data_access.PostDataAccessInterface;
import data_access.PostDoesntExistException;
import data_access.UsersPostsRelationalAccessInterface;
import entity.post.Post;

import java.util.ArrayList;

public class EditPostInteractor implements EditPostInputBoundary {
    private final EditPostOutputBoundary editPostPresenter;
    private final PostDataAccessInterface postDataAccessInterface;
    private final UsersPostsRelationalAccessInterface usersPostsRelationalAccessInterface;

    public EditPostInteractor(EditPostOutputBoundary editPostPresenter, PostDataAccessInterface postDataAccessInterface, UsersPostsRelationalAccessInterface usersPostsRelationalAccessInterface) {
        this.editPostPresenter = editPostPresenter;
        this.postDataAccessInterface = postDataAccessInterface;
        this.usersPostsRelationalAccessInterface = usersPostsRelationalAccessInterface;
    }

    @Override
    public void editPost(EditPostInputData inputData) {
        EditPostOutputData outputData = new EditPostOutputData(inputData.getPostToAlter());
        editPostPresenter.goToPostEditor(outputData);
    }

    @Override
    public void deletePost(EditPostInputData inputData) {
        try {
            postDataAccessInterface.deletePost(inputData.getPostToAlter());
            usersPostsRelationalAccessInterface.removePost(UserSession.getInstance().getLoggedInUser(), inputData.getPostToAlter());
            ArrayList<Post> updatedPosts = UserSession.getInstance().getLoggedInUser().getMyPosts();
            editPostPresenter.prepareSuccessView(new EditPostsSuccessOutputData(updatedPosts));
        } catch (PostDoesntExistException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePost(EditPostInputData inputData) {
        try {
            postDataAccessInterface.updatePost(inputData.getPostToAlter(), inputData.getTitle(), inputData.getText(), inputData.getPostDate(), inputData.getMedia());
            ArrayList<Post> updatedPosts = UserSession.getInstance().getLoggedInUser().getMyPosts();
            editPostPresenter.prepareSuccessView(new EditPostsSuccessOutputData(updatedPosts));
        } catch (PostDoesntExistException e) {
            e.printStackTrace();
        }
    }
}
