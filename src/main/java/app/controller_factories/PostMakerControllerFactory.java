package app.controller_factories;

import app.interface_adapter_tools.ViewManagerModel;
import use_case.postMaker.interface_adapter.PostMakerController;
import use_case.postMaker.PostMakerInputBoundary;
import use_case.postMaker.PostMakerInteractor;
import use_case.postMaker.PostMakerOutputBoundary;
import data_access.PostDataAccessInterface;
import use_case.postMaker.interface_adapter.PostMakerPresenter;
import view_model.PostMakerViewModel;

/**
 * Create post maker controllers
 */
public class PostMakerControllerFactory {

    private PostMakerControllerFactory() {}

    /**
     * create post maker controller
     * @param viewManagerModel model for switching view models
     * @param postScreenViewModel model for view
     * @param postDataAccessObject DAO for accessing posts
     * @return new instance f PostMakerController
     */
    public static PostMakerController createMakePostController(ViewManagerModel viewManagerModel,
                                                               PostMakerViewModel postScreenViewModel,
                                                               PostDataAccessInterface postDataAccessObject) {
        PostMakerOutputBoundary postMakerPresenter = new PostMakerPresenter(postScreenViewModel, viewManagerModel);
        PostMakerInputBoundary postMakerInteractor = new PostMakerInteractor(postDataAccessObject, postMakerPresenter);

        return new PostMakerController(postMakerInteractor, postDataAccessObject);
    }
}
