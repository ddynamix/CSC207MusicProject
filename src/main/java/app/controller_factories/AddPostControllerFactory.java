package app.controller_factories;


import data_access.UsersPostsRelationalAccessInterface;
import use_case.add_post.AddPostInputBoundary;
import use_case.add_post.AddPostInteractor;
import use_case.add_post.AddPostOutputBoundary;
import use_case.add_post.interface_adapter.AddPostController;
import use_case.add_post.interface_adapter.AddPostPresenter;
import view_model.HomescreenViewModel;

public class AddPostControllerFactory {

    private AddPostControllerFactory() {}

    public static AddPostController createAddPostsController(HomescreenViewModel homescreenViewModel,
                                                             UsersPostsRelationalAccessInterface userPostsRelationalAccessObject) {
        AddPostOutputBoundary addPostPresenter = new AddPostPresenter(homescreenViewModel);
        AddPostInputBoundary addPostInteractor = new AddPostInteractor(addPostPresenter, usersPostsRelationalAccessObject);

        return new AddPostController(addPostInteractor);
    }
}
