package use_case.edit_post.interface_adapter;

import app.interface_adapter_tools.ViewManagerModel;
import use_case.edit_post.EditPostOutputBoundary;
import use_case.edit_post.EditPostOutputData;
import use_case.edit_post.EditPostsSuccessOutputData;
import view_model.PostEditorState;
import view_model.PostEditorViewModel;

public class EditPostPresenter implements EditPostOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final PostEditorViewModel postEditorViewModel;

    public EditPostPresenter(ViewManagerModel viewManagerModel, PostEditorViewModel postEditorViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.postEditorViewModel = postEditorViewModel;
    }

    @Override
    public void goToPostEditor(EditPostOutputData outputData) {
        PostEditorState postEditorState = postEditorViewModel.getState();
        postEditorState.setPostToEdit(outputData.getGetPostToEdit());
        postEditorViewModel.setState(postEditorState);
        postEditorViewModel.firePropertyChanged();

        viewManagerModel.setActiveView("post editor");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareSuccessView(EditPostsSuccessOutputData outputData) {
        viewManagerModel.firePropertyChanged();
    }
}
