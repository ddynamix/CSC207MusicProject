package use_case.postMaker.interface_adapter;

import app.interface_adapter_tools.ViewManagerModel;
import use_case.postMaker.PostMakerOutputBoundary;
import use_case.postMaker.PostMakerOutputData;
import view_model.PostMakerState;
import view_model.PostMakerViewModel;


import javax.swing.*;

public class PostMakerPresenter implements PostMakerOutputBoundary {
    private final PostMakerViewModel postMakerViewModel;
    private final ViewManagerModel viewManagerModel;

    public PostMakerPresenter(PostMakerViewModel postMakerViewModel, ViewManagerModel viewManagerModel) {
        this.postMakerViewModel = postMakerViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(PostMakerOutputData postMakerOutputData) {
        PostMakerState postMakerState = postMakerViewModel.getState();
        postMakerState.setPosts(postMakerOutputData.getPosts());

        postMakerViewModel.setState(postMakerState);
        postMakerViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(postMakerViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        JOptionPane.showMessageDialog(null, error);
        viewManagerModel.firePropertyChanged();
    }
}
