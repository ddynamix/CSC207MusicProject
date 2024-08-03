package use_case.add_post.interface_adapter;

import app.interface_adapter_tools.ViewManagerModel;
import use_case.add_post.AddPostOutputBoundary;
import use_case.add_post.AddPostOutputData;
import view_model.HomescreenState;
import view_model.HomescreenViewModel;

public class AddPostPresenter implements AddPostOutputBoundary {
    private final HomescreenViewModel homescreenViewModel;

    public AddPostPresenter(HomescreenViewModel homescreenViewModel) {
        this.homescreenViewModel = homescreenViewModel;
    }

    @Override
    public void updatePostsView(AddPostOutputData outputData) {
        HomescreenState homescreenState = homescreenViewModel.getState();
        homescreenState.setPosts(outputData.getPostsToDisplay());
        homescreenViewModel.setState(homescreenState);
        homescreenViewModel.firePropertyChanged();
    }
}
