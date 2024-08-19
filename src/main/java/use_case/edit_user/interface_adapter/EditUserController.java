package use_case.edit_user.interface_adapter;

import entity.user.User;
import use_case.edit_user.EditUserInputBoundary;
import use_case.edit_user.EditUserInputData;

/**
 * controller for edit user use case
 */
public class EditUserController {
    private final EditUserInputBoundary editUserInteractor;

    /**
     * create instance of controller for edit user use case
     * @param editUserInteractor interactor to pass information to controller
     */
    public EditUserController(EditUserInputBoundary editUserInteractor) {
        this.editUserInteractor = editUserInteractor;
    }

    /**
     * update user
     * @param user new user
     */
    public void editUser(User user) {
        editUserInteractor.editUser(new EditUserInputData(user));
    }

    /**
     * remove user
     * @param user to be removed
     */
    public void deleteUser(User user) {
        editUserInteractor.deleteUser(new EditUserInputData(user));
    }

    /**
     * update user data
     * @param userToAlter current user
     * @param title of user
     * @param text of user
     * @param media of user
     */
    public void updateUser(User userToAlter, String title, String text, String media) {
        editUserInteractor.updateUser(new EditUserInputData(userToAlter, title, text, media));
    }
}
