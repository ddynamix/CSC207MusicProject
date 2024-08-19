package app.controller_factories;


import data_access.UserDataAccessInterface;
import use_case.edit_user.EditUserInputBoundary;
import use_case.edit_user.EditUserInteractor;
import use_case.edit_user.EditUserOutputBoundary;
import use_case.edit_user.interface_adapter.EditUserController;
import use_case.edit_user.interface_adapter.EditUserPresenter;
import view_model.ProfileViewModel;
import view_model.UserEditorViewModel;

/**
 * Create controllers for add post use case
 */
public class EditUserControllerFactory {

    private EditUserControllerFactory() {}

    /**
     * create an instance of controller for add post use case
     * @param profileViewModel     view model for profile view
     * @param userEditorViewModel  view model for user edit use case
     * @param userDataAccessObject  DAO for users
     * @return instance of AddPostController
     */
    public static EditUserController createEditUserController(ProfileViewModel profileViewModel,
                                                              UserEditorViewModel userEditorViewModel,
                                                             UserDataAccessInterface userDataAccessObject) {
        EditUserOutputBoundary editUserPresenter = new EditUserPresenter(profileViewModel, userEditorViewModel);
        EditUserInputBoundary editUserInteractor = new EditUserInteractor(editUserPresenter, userDataAccessObject);

        return new EditUserController(editUserInteractor);
    }
}
