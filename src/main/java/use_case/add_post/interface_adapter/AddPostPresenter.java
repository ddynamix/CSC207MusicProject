package use_case.add_post.interface_adapter;

import app.interface_adapter_tools.ViewManagerModel;
import use_case.add_post.AddPostOutputBoundary;
import use_case.add_post.AddPostOutputData;
import view_model.HomescreenState;
import view_model.HomescreenViewModel;

/**
 * presenter for add post use case
 */
public class AddPostPresenter implements AddPostOutputBoundary {
    private final HomescreenViewModel homescreenViewModel;

    /**
     * set view model
     * @param homescreenViewModel new view model
     */
    public AddPostPresenter(HomescreenViewModel homescreenViewModel) {
        this.homescreenViewModel = homescreenViewModel;
    }

    /**
     * update view
     * @param outputData new data
     */
    @Override
    public void updatePostsView(AddPostOutputData outputData) {
        HomescreenState homescreenState = homescreenViewModel.getState();
        homescreenState.setPosts(outputData.getPostsToDisplay());
        homescreenViewModel.setState(homescreenState);
        homescreenViewModel.firePropertyChanged();
    }
}