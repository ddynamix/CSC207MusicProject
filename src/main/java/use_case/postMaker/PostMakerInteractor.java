package use_case.postMaker;

import app.interface_adapter_tools.UserSession;
import data_access.PostDataAccessInterface;
import entity.post.Post;
import entity.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

public class PostMakerInteractor implements PostMakerInputBoundary {
    final PostDataAccessInterface postDataAccessInterface;
    final PostMakerOutputBoundary postMakerPresenter;

    public PostMakerInteractor(PostDataAccessInterface postDataAccessInterface,
                               PostMakerOutputBoundary postMakerPresenter) {
        this.postDataAccessInterface = postDataAccessInterface;
        this.postMakerPresenter = postMakerPresenter;
    }

    @Override
    public void attemptPost(PostMakerInputData postMakerInputData) {
        Post post = new Post(postMakerInputData.getTitle(),
                postMakerInputData.getText(),
                postMakerInputData.getAuthor(),
                postMakerInputData.getAttachedMedia());
        postDataAccessInterface.createPost(post);

        ArrayList<Post> values = postDataAccessInterface.getPosts();
        PostMakerOutputData postMakerOutputData = new PostMakerOutputData(values);

        postMakerPresenter.prepareSuccessView(postMakerOutputData);
        System.out.println("Post posted successfully!");
    }
}
