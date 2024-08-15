package use_case.add_post;

import app.interface_adapter_tools.UserSession;
import data_access.UsersPostsRelationalAccessInterface;
import entity.user.User;

/**
 * interactor for add post use case
 */
public class AddPostInteractor implements AddPostInputBoundary {
    private final AddPostOutputBoundary addPostPresenter;
    private final UsersPostsRelationalAccessInterface usersPostsRelationalAccessInterface;

    /**
     * create instance of interactor for add post use case
     * @param addPostPresenter presenter to update view
     * @param usersPostsRelationalAccessInterface data access interface
     */
    public AddPostInteractor(AddPostOutputBoundary addPostPresenter, UsersPostsRelationalAccessInterface usersPostsRelationalAccessInterface) {
        this.usersPostsRelationalAccessInterface = usersPostsRelationalAccessInterface;
        this.addPostPresenter = addPostPresenter;
    }

    /**
     * add post to data
     * @param inputData new data
     */
    @Override
    public void addPost(AddPostInputData inputData) {
        User loggedIn = UserSession.getInstance().getLoggedInUser();
        usersPostsRelationalAccessInterface.addPost(loggedIn, inputData.getPostToAddOrRemove());
    }

    /**
     * remove post from data
     * @param inputData new data
     */
    @Override
    public void removePost(AddPostInputData inputData) {
        User loggedIn = UserSession.getInstance().getLoggedInUser();
        usersPostsRelationalAccessInterface.removePost(loggedIn, inputData.getPostToAddOrRemove());
        addPostPresenter.updatePostsView(new AddPostOutputData(UserSession.getInstance().getLoggedInUser().getMyPosts()));
    }
}
