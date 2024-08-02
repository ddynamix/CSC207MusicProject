package app.swing_view_factories;

import data_access.PostMakerAccessInterface;
import data_access.UserDataAccessInterface;
import app.interface_adapter_tools.ViewManagerModel;
import use_case.postMaker.interface_adapter.PostMakerController;
import use_case.postMaker.interface_adapter.PostMakerPresenter;
import use_case.postMaker.interface_adapter.PostMakerViewModel;
import use_case.postMaker.PostMakerInputBoundary;
import use_case.postMaker.PostMakerInteractor;
import use_case.postMaker.PostMakerOutputBoundary;
import view.jswing_views.PostMakerView;
import view_model.HomescreenViewModel;

/**
 * Post maker factory
 */
public class PostMakerViewFactory {

    private PostMakerViewFactory() {}

    /**
     * create event crafter screen instance
     *
     * @param viewManagerModel      control of view models
     * @param homescreenViewModel   data for homescreen view
     * @param postMakerViewModel data for this view
     * @param postMakerAccessObject data access object for posts
     * @param userDataAccessObject  data access object for users
     * @return EventCrafterView     the created view
     */
    public static PostMakerView postMakerCrafterView(ViewManagerModel viewManagerModel, HomescreenViewModel homescreenViewModel, PostMakerViewModel postMakerViewModel, PostMakerAccessInterface postMakerAccessObject, UserDataAccessInterface userDataAccessObject) {
        PostMakerOutputBoundary postMakerPresenter = new PostMakerPresenter(homescreenViewModel, viewManagerModel);
        PostMakerInputBoundary postMakerInteractor = new PostMakerInteractor(postMakerAccessObject, postMakerPresenter);
        PostMakerController postMakerController = new PostMakerController(postMakerInteractor, postMakerAccessObject);

        return new PostMakerView(postMakerViewModel, postMakerController);
    }
}
