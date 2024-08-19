package use_case.edit_user.interface_adapter;

import app.interface_adapter_tools.ViewManagerModel;
import use_case.edit_user.EditUserOutputBoundary;
import use_case.edit_user.EditUserOutputData;
import use_case.edit_user.EditUserSuccessOutputData;
import view_model.UserEditorState;
import view_model.UserEditorViewModel;

/**
 * presenter for edit user use case
 */
public class EditUserPresenter implements EditUserOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final UserEditorViewModel userEditorViewModel;

    /**
     * create presenter for edit user use case
     * @param viewManagerModel model for changing models
     * @param userEditorViewModel model for edit user use case
     */
    public EditUserPresenter(ViewManagerModel viewManagerModel, UserEditorViewModel userEditorViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.userEditorViewModel = userEditorViewModel;
    }

    /**
     * access current data
     * @param outputData curret data
     */
    @Override
    public void goToUserEditor(EditUserOutputData outputData) {
        UserEditorState userEditorState = userEditorViewModel.getState();
        userEditorState.setUserToEdit(outputData.getGetUserToEdit());
        userEditorViewModel.setState(userEditorState);
        userEditorViewModel.firePropertyChanged();

        viewManagerModel.setActiveView("user editor");
        viewManagerModel.firePropertyChanged();
    }

    /**
     * create event screen view
     * @param outputData screen data
     */
    @Override
    public void prepareSuccessView(EditUserSuccessOutputData outputData) {
        viewManagerModel.firePropertyChanged();
    }
}