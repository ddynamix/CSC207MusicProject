package use_case.edit_post;

import app.interface_adapter_tools.UserSession;
import data_access.PostDataAccessInterface;
import data_access.PostDoesntExistException;
import data_access.UsersPostsRelationalAccessInterface;
import entity.post.Post;

import java.util.ArrayList;

/**
 * interactor for edit post use case
 */
public class EditPostInteractor implements EditPostInputBoundary {
    private final EditPostOutputBoundary editPostPresenter;
    private final PostDataAccessInterface postDataAccessInterface;
    private final UsersPostsRelationalAccessInterface usersPostsRelationalAccessInterface;

    /**
     * create instance of interactor for edit post use case
     * @param editPostPresenter
     * @param postDataAccessInterface
     * @param usersPostsRelationalAccessInterface
     */
    public EditPostInteractor(EditPostOutputBoundary editPostPresenter, PostDataAccessInterface postDataAccessInterface, UsersPostsRelationalAccessInterface usersPostsRelationalAccessInterface) {
        this.editPostPresenter = editPostPresenter;
        this.postDataAccessInterface = postDataAccessInterface;
        this.usersPostsRelationalAccessInterface = usersPostsRelationalAccessInterface;
    }

    /**
     * update post
     * @param inputData new data
     */
    @Override
    public void editPost(EditPostInputData inputData) {
        EditPostOutputData outputData = new EditPostOutputData(inputData.getPostToAlter());
        editPostPresenter.goToPostEditor(outputData);
    }

    /**
     * remove data
     * @param inputData new data
     */
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

    /**
     * update post
     * @param inputData new data
     */
    @Override
    public void updatePost(EditPostInputData inputData) {
        try {
            postDataAccessInterface.updatePost(inputData.getPostToAlter(), inputData.getTitle(), inputData.getText(),
                    inputData.getMedia());
            ArrayList<Post> updatedPosts = UserSession.getInstance().getLoggedInUser().getMyPosts();
            editPostPresenter.prepareSuccessView(new EditPostsSuccessOutputData(updatedPosts));
        } catch (PostDoesntExistException e) {
            e.printStackTrace();
        }
    }
}
