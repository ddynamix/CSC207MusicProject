package use_case.add_post;

import app.interface_adapter_tools.UserSession;
import data_access.UsersPostsRelationalAccessInterface;
import entity.user.User;

public class AddPostInteractor implements AddPostInputBoundary {
    private final AddPostOutputBoundary addPostPresenter;
    private final UsersPostsRelationalAccessInterface usersPostsRelationalAccessInterface;

    public AddPostInteractor(AddPostOutputBoundary addPostPresenter, UsersPostsRelationalAccessInterface usersPostsRelationalAccessInterface) {
        this.usersPostsRelationalAccessInterface = usersPostsRelationalAccessInterface;
        this.addPostPresenter = addPostPresenter;
    }

    @Override
    public void addPost(AddPostInputData inputData) {
        User loggedIn = UserSession.getInstance().getLoggedInUser();
        usersPostsRelationalAccessInterface.addPost(loggedIn, inputData.getPostToAddOrRemove());
    }

    @Override
    public void removePost(AddPostInputData inputData) {
        User loggedIn = UserSession.getInstance().getLoggedInUser();
        usersPostsRelationalAccessInterface.removePost(loggedIn, inputData.getPostToAddOrRemove());
        addPostPresenter.updatePostsView(new AddPostOutputData(UserSession.getInstance().getLoggedInUser().getMyPosts()));
    }
}
