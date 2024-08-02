package use_case.postMaker.interface_adapter;

import app.interface_adapter_tools.ViewManagerModel;
import use_case.postMaker.PostMakerOutputBoundary;
import use_case.postMaker.PostMakerOutputData;
import use_case.homescreen.interface_adapter.HomescreenState;
import use_case.homescreen.interface_adapter.HomescreenViewModel;

import javax.swing.*;

public class PostMakerPresenter implements PostMakerOutputBoundary {
    private final HomescreenViewModel homescreenViewModel;
    private final ViewManagerModel viewManagerModel;

    public PostMakerPresenter(HomescreenViewModel homescreenViewModel, ViewManagerModel viewManagerModel) {
        this.homescreenViewModel = homescreenViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(PostMakerOutputData postMakerOutputData) {
        HomescreenState homescreenState = homescreenViewModel.getState();
        homescreenState.setPosts(postMakerOutputData.getPosts());
        homescreenViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(homescreenViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        JOptionPane.showMessageDialog(null, error);
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToHomescreen() {
        viewManagerModel.setActiveView(homescreenViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
