package use_case.postMaker;

import data_access.PostDataAccessInterface;
import entity.post.Post;
import entity.user.User;

import java.util.ArrayList;
import java.util.Collection;

public class PostMakerInteractor implements PostMakerInputBoundary {
    final PostDataAccessInterface postDataAccessInterface;
    final PostMakerOutputBoundary postMakerPresenter;
    User signedInAs;

    public PostMakerInteractor(PostDataAccessInterface postDataAccessInterface,
                               PostMakerOutputBoundary postMakerPresenter) {
        this.postDataAccessInterface = postDataAccessInterface;
        this.postMakerPresenter = postMakerPresenter;
        this.signedInAs = null;
    }

    @Override
    public void attemptPost(PostMakerInputData postMakerInputData) {
        Post post = new Post(postMakerInputData.getTitle(),
                postMakerInputData.getText(),
                postMakerInputData.getAuthor(),
                postMakerInputData.getAttachedMedia());
        postDataAccessInterface.createPost(post);

        Collection<Post> values = postDataAccessInterface.getPosts();
        ArrayList<Post> listOfPosts = new ArrayList<>(values);
        PostMakerOutputData postMakerOutputData = new PostMakerOutputData(listOfPosts);

        postMakerPresenter.prepareSuccessView(postMakerOutputData);
        System.out.println("Post posted successfully!");
    }
}
