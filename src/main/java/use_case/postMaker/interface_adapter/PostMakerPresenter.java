package use_case.postMaker.interface_adapter;

import app.interface_adapter_tools.ViewManagerModel;
import use_case.postMaker.PostMakerOutputBoundary;
import use_case.postMaker.PostMakerOutputData;
import view_model.HomescreenState;
import view_model.HomescreenViewModel;
import view_model.PostMakerState;
import view_model.PostMakerViewModel;


import javax.swing.*;

/**
 * presenter for post use case
 */
public class PostMakerPresenter implements PostMakerOutputBoundary {
    private final PostMakerViewModel postMakerViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * create instance of presenter for post use case
     * @param postMakerViewModel model for post use case
     * @param viewManagerModel manager for changing models
     */
    public PostMakerPresenter(PostMakerViewModel postMakerViewModel, ViewManagerModel viewManagerModel) {
        this.postMakerViewModel = postMakerViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * build homescreen view and following actions
     * @param postMakerOutputData output data for post use case
     */
    @Override
    public void prepareSuccessView(PostMakerOutputData postMakerOutputData) {
        PostMakerState postMakerState = postMakerViewModel.getState();
        postMakerState.setPosts(postMakerOutputData.getPosts());

        postMakerViewModel.setState(postMakerState);
        postMakerViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(postMakerViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * create dialogue for error
     * @param error custom message
     */
    @Override
    public void prepareFailView(String error) {
        viewManagerModel.firePropertyChanged();
    }
}
