package use_case.postMaker.interface_adapter;

import data_access.PostMakerAccessInterface;
import data_access.UserDataAccessInterface;
import entity.user.User;
import use_case.postMaker.PostMakerInputBoundary;
import use_case.postMaker.PostMakerInputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class PostMakerController {
    final PostMakerInputBoundary postMakerInteractor;
    final PostMakerAccessInterface postMakerAccessInterface;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    public PostMakerController(PostMakerInputBoundary postMakerInteractor, PostMakerAccessInterface postMakerAccessInterface) {
        this.postMakerInteractor = postMakerInteractor;
        this.postMakerAccessInterface = postMakerAccessInterface;
    }

    public void execute(String title,
                         String text,
                         User author,
                         String timePosted,
                         String attachedMedia) {

        LocalDateTime postDateFormatted = LocalDateTime.parse(timePosted, formatter);

        System.out.println("PostMakerController: excecute: title: " + title);
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

    private ArrayList<String> stringToArrayList(String tags) {
        return new ArrayList<>(Arrays.asList(tags.split(";")));
    }
}
