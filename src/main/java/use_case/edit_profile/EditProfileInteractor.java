package use_case.edit_profile;

import app.interface_adapter_tools.UserSession;
import data_access.UserDataAccessInterface;
import data_access.mongodb.UserDataAccessObject;
import entity.user.User;

/**
 * interactor for edit profile use case
 */
public class EditProfileInteractor implements EditProfileInputBoundary {
    private final EditProfileOutputBoundary editProfilePresenter;
    private final UserDataAccessInterface userDataAccessInterface;

    /**
     * create instance of interactor for edit profile use case
     * @param editProfilePresenter presenter to pass information to view
     * @param userDataAccessInterface data access for posts
     */
    public EditProfileInteractor(EditProfileOutputBoundary editProfilePresenter, UserDataAccessInterface userDataAccessInterface) {
        this.editProfilePresenter = editProfilePresenter;
        this.userDataAccessInterface = userDataAccessInterface;
    }

    /**
     * update profile
     * @param inputData new data
     */
    @Override
    public void editProfile(EditProfileInputData inputData) {
        EditProfileOutputData outputData = new EditProfileOutputData(inputData.getUserToAlter());
        editProfilePresenter.goToUserEditor(outputData);
    }

    /**
     * update profile of user
     * @param inputData new data
     */
    @Override
    public void updateProfile(EditProfileInputData inputData) throws UserDataAccessObject.UserNotFoundException {
        userDataAccessInterface.updateUser(inputData.getUserToAlter(), inputData.getEmail(), inputData.getUsername(),
                inputData.getName());
        User update = UserSession.getInstance().getLoggedInUser();
        editProfilePresenter.prepareSuccessView(new EditProfileSuccessOutputData(update));
    }

    @Override
    public void deleteProfile(EditProfileInputData editProfileInputData) throws UserDataAccessObject.UserNotFoundException {
        User original = editProfileInputData.getUserToAlter();
        userDataAccessInterface.delete(original);
    }
}
