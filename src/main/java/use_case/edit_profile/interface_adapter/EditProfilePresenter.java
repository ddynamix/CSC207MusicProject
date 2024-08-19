package use_case.edit_profile.interface_adapter;

import app.interface_adapter_tools.ViewManagerModel;
import use_case.edit_profile.EditProfileOutputBoundary;
import use_case.edit_profile.EditProfileOutputData;
import use_case.edit_profile.EditProfileSuccessOutputData;
import view_model.ProfileEditorState;
import view_model.ProfileEditViewModel;

/**
 * presenter for edit profile use case
 */
public class EditProfilePresenter implements EditProfileOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final ProfileEditViewModel profileEditorViewModel;

    /**
     * create presenter for edit profile use case
     * @param viewManagerModel model for changing models
     * @param profileEditorViewModel model for edit profile use case
     */
    public EditProfilePresenter(ViewManagerModel viewManagerModel, ProfileEditViewModel profileEditorViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.profileEditorViewModel = profileEditorViewModel;
    }

    /**
     * access current data
     * @param outputData curret data
     */
    @Override
    public void goToUserEditor(EditProfileOutputData outputData) {
        ProfileEditorState profileEditorState = profileEditorViewModel.getState();
        profileEditorState.setProfileToEdit(outputData.getGetProfileToEdit());
        profileEditorViewModel.setState(profileEditorState);
        profileEditorViewModel.firePropertyChanged();

        viewManagerModel.setActiveView("profile editor");
        viewManagerModel.firePropertyChanged();
    }

    /**
     * create event screen view
     * @param outputData screen data
     */
    @Override
    public void prepareSuccessView(EditProfileSuccessOutputData outputData) {
        viewManagerModel.firePropertyChanged();
    }
}
