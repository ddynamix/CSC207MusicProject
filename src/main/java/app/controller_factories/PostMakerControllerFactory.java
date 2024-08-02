package app.controller_factories;

import app.interface_adapter_tools.ViewManagerModel;
import use_case.postMaker.interface_adapter.PostMakerController;
import use_case.postMaker.PostMakerInputBoundary;
import use_case.postMaker.PostMakerInteractor;
import use_case.postMaker.PostMakerOutputBoundary;
import data_access.PostDataAccessInterface;
import data_access.UserDataAccessInterface;
import use_case.postMaker.interface_adapter.PostMakerPresenter;
import view_model.PostMakerViewModel;

public class PostMakerControllerFactory {

    private PostMakerControllerFactory() {}

    public static PostMakerController createMakePostController(ViewManagerModel viewManagerModel, PostMakerViewModel postScreenViewModel, PostDataAccessInterface postDataAccessObject, UserDataAccessInterface userDataAccessObject) {
        PostMakerOutputBoundary postMakerPresenter = new PostMakerPresenter(postScreenViewModel, viewManagerModel);
        PostMakerInputBoundary postMakerInteractor = new PostMakerInteractor(postDataAccessObject, postMakerPresenter);

        return new PostMakerController(postMakerInteractor, postDataAccessObject);
    }
}
