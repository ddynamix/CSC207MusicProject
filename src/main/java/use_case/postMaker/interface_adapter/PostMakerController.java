package use_case.postMaker.interface_adapter;

import data_access.PostDataAccessInterface;
import entity.user.User;
import use_case.postMaker.PostMakerInputBoundary;
import use_case.postMaker.PostMakerInputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PostMakerController {
    final PostMakerInputBoundary postMakerInteractor;
    final PostDataAccessInterface postMakerAccessInterface;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    public PostMakerController(PostMakerInputBoundary postMakerInteractor, PostDataAccessInterface postMakerAccessInterface) {
        this.postMakerInteractor = postMakerInteractor;
        this.postMakerAccessInterface = postMakerAccessInterface;
    }

    public void execute(String title,
                         String text,
                         User author,
                         String timePosted,
                         String attachedMedia) {

        LocalDateTime postDateFormatted = LocalDateTime.parse(timePosted, formatter);

        System.out.println("PostMakerController: execute: title: " + title);
        PostMakerInputData postMakerInputData = new PostMakerInputData(
                title,
                text,
                author,
                postDateFormatted,
                attachedMedia
        );

        postMakerInteractor.attemptPost(postMakerInputData);
    }

    public void switchToHomescreen() {
        postMakerInteractor.switchToHomescreen();
    }

}
