package use_case.edit_post.interface_adapter;

import app.interface_adapter_tools.ViewManagerModel;
import use_case.edit_post.EditPostOutputBoundary;
import use_case.edit_post.EditPostOutputData;
import use_case.edit_post.EditPostsSuccessOutputData;
import view_model.PostEditorState;
import view_model.PostEditorViewModel;

/**
 * presenter for edit post use case
 */
public class EditPostPresenter implements EditPostOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final PostEditorViewModel postEditorViewModel;

    /**
     * create presenter for edit post use case
     * @param viewManagerModel model for changing models
     * @param postEditorViewModel model for edit post use case
     */
    public EditPostPresenter(ViewManagerModel viewManagerModel, PostEditorViewModel postEditorViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.postEditorViewModel = postEditorViewModel;
    }

    /**
     * access current data
     * @param outputData curret data
     */
    @Override
    public void goToPostEditor(EditPostOutputData outputData) {
        PostEditorState postEditorState = postEditorViewModel.getState();
        postEditorState.setPostToEdit(outputData.getGetPostToEdit());
        postEditorViewModel.setState(postEditorState);
        postEditorViewModel.firePropertyChanged();

        viewManagerModel.setActiveView("post editor");
        viewManagerModel.firePropertyChanged();
    }

    /**
     * create event screen view
     * @param outputData screen data
     */
    @Override
    public void prepareSuccessView(EditPostsSuccessOutputData outputData) {
        viewManagerModel.firePropertyChanged();
    }
}
