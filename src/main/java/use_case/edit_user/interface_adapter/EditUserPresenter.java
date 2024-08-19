package use_case.edit_user.interface_adapter;

import app.interface_adapter_tools.ViewManagerModel;
import use_case.edit_user.EditUserOutputBoundary;
import use_case.edit_user.EditUserOutputData;
import use_case.edit_user.EditUserSuccessOutputData;
import view_model.ProfileState;
import view_model.ProfileViewModel;
import view_model.UserEditorState;
import view_model.UserEditorViewModel;

/**
 * presenter for edit user use case
 */
public class EditUserPresenter implements EditUserOutputBoundary {
    private final ProfileViewModel profileViewModel;
    private final UserEditorViewModel userEditorViewModel;

    /**
     * create presenter for edit user use case
     * @param profileViewModel model for profile view
     * @param userEditorViewModel model for edit user use case
     */
    public EditUserPresenter(ProfileViewModel profileViewModel, UserEditorViewModel userEditorViewModel) {
        this.profileViewModel = profileViewModel;
        this.userEditorViewModel = userEditorViewModel;
    }

    /**
     * access editor view
     * @param outputData current data
     */
    @Override
    public void goToUserEditor(EditUserOutputData outputData) {
        UserEditorState userEditorState = userEditorViewModel.getState();
        userEditorState.setUserToEdit(outputData.getGetUserToEdit());
        userEditorViewModel.setState(userEditorState);
        userEditorViewModel.firePropertyChanged();
    }

    /**
     * create event screen view
     * @param outputData screen data
     */
    @Override
    public void prepareSuccessView(EditUserSuccessOutputData outputData) {
        profileViewModel.firePropertyChanged();
    }
}