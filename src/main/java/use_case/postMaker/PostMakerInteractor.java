package use_case.postMaker;

import data_access.PostMakerAccessInterface;
import entity.post.Post;

import java.util.ArrayList;
import java.util.Collection;

public class PostMakerInteractor implements PostMakerInputBoundary {
    final PostMakerAccessInterface postMakerAccessInterface;
    final PostMakerOutputBoundary postMakerPresenter;

    public PostMakerInteractor(PostMakerAccessInterface postMakerAccessInterface, PostMakerOutputBoundary postMakerPresenter) {
        this.postMakerAccessInterface = postMakerAccessInterface;
        this.postMakerPresenter = postMakerPresenter;
    }

    @Override
    public void attemptPost(PostMakerInputData postMakerInputData) {
        Post post = new Post(postMakerInputData.getTitle(),
                postMakerInputData.getText(),
                postMakerInputData.getAuthor(),
                postMakerInputData.getAttachedMedia());
        postMakerAccessInterface.createPost(post);

        Collection<Post> values = postMakerAccessInterface.getPosts().values();
        ArrayList<Post> listOfPosts = new ArrayList<>(values);
        PostMakerOutputData postMakerOutputData = new PostMakerOutputData(listOfPosts);

        postMakerPresenter.prepareSuccessView(postMakerOutputData);
        System.out.println("Post posted successfully!");
    }

    @Override
    public void switchToHomescreen() {
        postMakerPresenter.prepareSuccessView(new PostMakerOutputData(new ArrayList<>()));
    }
}
