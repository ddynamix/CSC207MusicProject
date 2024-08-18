package use_case.edit_profile;

import data_access.UserAlreadyExistsException;
import data_access.mongodb.UserDataAccessObject;

/**
 * interface for input data for edit profile use case
 */
public interface EditProfileInputBoundary {
    void editProfile(EditProfileInputData inputData);
    void updateProfile(EditProfileInputData inputData) throws UserDataAccessObject.UserNotFoundException, UserAlreadyExistsException;

    void deleteProfile(EditProfileInputData editProfileInputData) throws UserDataAccessObject.UserNotFoundException, UserAlreadyExistsException;
}
