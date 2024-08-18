package app.controller_factories;

import app.interface_adapter_tools.ViewManagerModel;
import data_access.UserDataAccessInterface;
import use_case.edit_profile.EditProfileInputBoundary;
import use_case.edit_profile.EditProfileInteractor;
import use_case.edit_profile.EditProfileOutputBoundary;
import use_case.edit_profile.interface_adapter.EditProfileController;
import use_case.edit_profile.interface_adapter.EditProfilePresenter;
import view_model.ProfileEditViewModel;

/**
 * Create edit profile controllers
 */
public class EditProfileControllerFactory {

    private EditProfileControllerFactory() {}

    /**
     * create edit profile controller
     * @param viewManagerModel model for switching view models
     * @param profileEditorViewModel model for post edit use case
     * @param userDataAccessObject DAO for accessing users
     * @return new instance of EditProfileController
     */
    public static EditProfileController createEditProfileController(ViewManagerModel viewManagerModel, ProfileEditViewModel profileEditorViewModel, UserDataAccessInterface userDataAccessObject) {
        EditProfileOutputBoundary editProfilePresenter = new EditProfilePresenter(viewManagerModel, profileEditorViewModel);
        EditProfileInputBoundary editProfileInteractor = new EditProfileInteractor(editProfilePresenter, userDataAccessObject);

        return new EditProfileController(editProfileInteractor);
    }
}
