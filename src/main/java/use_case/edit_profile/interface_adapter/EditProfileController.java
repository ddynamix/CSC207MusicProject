package use_case.edit_profile.interface_adapter;

import data_access.UserAlreadyExistsException;
import data_access.mongodb.UserDataAccessObject;
import entity.user.User;
import use_case.edit_profile.EditProfileInputBoundary;
import use_case.edit_profile.EditProfileInputData;

/**
 * controller for edit profile use case
 */
public class EditProfileController {
    private final EditProfileInputBoundary editProfileInteractor;

    /**
     * create instance of controller for edit profile use case
     * @param editProfileInteractor interactor to pass information to controller
     */
    public EditProfileController(EditProfileInputBoundary editProfileInteractor) {
        this.editProfileInteractor = editProfileInteractor;
    }

    /**
     * update profile
     * @param user new user to set
     */
    public void editProfile(User user) {
        editProfileInteractor.editProfile(new EditProfileInputData(user));
    }

    /**
     * remove profile
     * @param user to be removed
     */

    public void deleteProfile(User user) throws UserDataAccessObject.UserNotFoundException, UserAlreadyExistsException {
        editProfileInteractor.deleteProfile(new EditProfileInputData(user));
    }

    /**
     * update profile data
     * @param profileToAlter current profile
     * @param username of profile
     * @param email of profile
     * @param name of profile
     */
    public void updateProfile(User profileToAlter, String username, String email, String name) throws UserDataAccessObject.UserNotFoundException, UserAlreadyExistsException {
        editProfileInteractor.updateProfile(new EditProfileInputData(profileToAlter, username, email, name));
    }
}
