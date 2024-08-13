package use_case.postMaker.interface_adapter;

import data_access.PostDataAccessInterface;
import entity.user.User;
import use_case.postMaker.PostMakerInputBoundary;
import use_case.postMaker.PostMakerInputData;

/**
 * controller for post use case
 */
public class PostMakerController {
    final PostMakerInputBoundary postMakerInteractor;
    final PostDataAccessInterface postDataAccessInterface;

    /**
     * create instance of controller for post use case
     * @param postMakerInteractor interactor for post use case
     * @param postDataAccessInterface post DAO
     */
    public PostMakerController(PostMakerInputBoundary postMakerInteractor, PostDataAccessInterface postDataAccessInterface) {
        this.postMakerInteractor = postMakerInteractor;
        this.postDataAccessInterface = postDataAccessInterface;
    }

    /**
     * process data and continue actions
     * @param title of post
     * @param text of post
     * @param author of post
     * @param attachedMedia of post
     */
    public void execute(String title,
                         String text,
                         User author,
                         String attachedMedia) {


        System.out.println("PostMakerController: execute: title: " + title);
        PostMakerInputData postMakerInputData = new PostMakerInputData(
                title,
                text,
                author,
                attachedMedia
        );

        postMakerInteractor.attemptPost(postMakerInputData);
    }
}
