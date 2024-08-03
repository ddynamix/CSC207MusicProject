package app.controller_factories;

import app.interface_adapter_tools.ViewManagerModel;
import data_access.PostDataAccessInterface;
import data_access.UsersPostsRelationalAccessInterface;
import use_case.edit_post.EditPostInputBoundary;
import use_case.edit_post.EditPostInteractor;
import use_case.edit_post.EditPostOutputBoundary;
import use_case.edit_post.interface_adapter.EditPostController;
import use_case.edit_post.interface_adapter.EditPostPresenter;
import view_model.PostEditorViewModel;

public class EditPostControllerFactory {

    private EditPostControllerFactory() {}

    public static EditPostController createEditPostController(ViewManagerModel viewManagerModel, PostEditorViewModel postEditorViewModel, PostDataAccessInterface postDataAccessObject, UsersPostsRelationalAccessInterface usersPostsRelationalAccessObject) {
        EditPostOutputBoundary editPostPresenter = new EditPostPresenter(viewManagerModel, postEditorViewModel);
        EditPostInputBoundary editPostInteractor = new EditPostInteractor(editPostPresenter, postDataAccessObject, usersPostsRelationalAccessObject);

        return new EditPostController(editPostInteractor);
    }
}
