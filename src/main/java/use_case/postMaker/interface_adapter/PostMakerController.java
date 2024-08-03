package use_case.postMaker.interface_adapter;

import data_access.PostDataAccessInterface;
import entity.user.User;
import use_case.postMaker.PostMakerInputBoundary;
import use_case.postMaker.PostMakerInputData;

public class PostMakerController {
    final PostMakerInputBoundary postMakerInteractor;
    final PostDataAccessInterface postDataAccessInterface;

    public PostMakerController(PostMakerInputBoundary postMakerInteractor, PostDataAccessInterface postDataAccessInterface) {
        this.postMakerInteractor = postMakerInteractor;
        this.postDataAccessInterface = postDataAccessInterface;
    }

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
