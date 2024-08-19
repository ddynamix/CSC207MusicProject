package use_case.edit_user;

import app.interface_adapter_tools.UserSession;
import data_access.UserDataAccessInterface;
import data_access.mongodb.UserDataAccessObject;
import entity.user.User;

/**
 * interactor for edit user use case
 */
public class EditUserInteractor implements EditUserInputBoundary {
    private final EditUserOutputBoundary editUserPresenter;
    private final UserDataAccessInterface userDataAccessInterface;

    /**
     * create instance of interactor for edit user use case
     * @param editUserPresenter presenter to pass information to view
     * @param userDataAccessInterface data access for users
     */
    public EditUserInteractor(EditUserOutputBoundary editUserPresenter, UserDataAccessInterface userDataAccessInterface) {
        this.editUserPresenter = editUserPresenter;
        this.userDataAccessInterface = userDataAccessInterface;
    }

    /**
     * update user
     * @param inputData new data
     */
    @Override
    public void editUser(EditUserInputData inputData) {
        EditUserOutputData outputData = new EditUserOutputData(inputData.getUserToAlter());
        editUserPresenter.goToUserEditor(outputData);
    }

    /**
     * remove data
     * @param inputData new data
     */
    @Override
    public void deleteUser(EditUserInputData inputData) {
        try {
            userDataAccessInterface.delete(inputData.getUserToAlter());
            User updatedUser = UserSession.getInstance().getLoggedInUser();
            editUserPresenter.prepareSuccessView(new EditUserSuccessOutputData(updatedUser));
        } catch (UserDataAccessObject.UserNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * update user
     * @param inputData new data
     */
    @Override
    public void updateUser(EditUserInputData inputData) {
        try {
            userDataAccessInterface.updateUser(inputData.getUserToAlter(), inputData.getTitle(), inputData.getText(),
                    inputData.getMedia());
            User updatedUser = UserSession.getInstance().getLoggedInUser();
            editUserPresenter.prepareSuccessView(new EditUserSuccessOutputData(updatedUser));
        }
        catch (UserDataAccessObject.UserNotFoundException e) {
            e.printStackTrace();
        }
    }
}
