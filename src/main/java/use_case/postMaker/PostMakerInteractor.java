package use_case.postMaker;

import data_access.PostDataAccessInterface;
import entity.post.Post;

import java.util.ArrayList;

/**
 * interactor for post use case
 */
public class PostMakerInteractor implements PostMakerInputBoundary {
    final PostDataAccessInterface postDataAccessInterface;
    final PostMakerOutputBoundary postMakerPresenter;

    /**
     * create instance of interactor for post use case
     * @param postDataAccessInterface post DAO
     * @param postMakerPresenter presenter for post use case
     */
    public PostMakerInteractor(PostDataAccessInterface postDataAccessInterface,
                               PostMakerOutputBoundary postMakerPresenter) {
        this.postDataAccessInterface = postDataAccessInterface;
        this.postMakerPresenter = postMakerPresenter;
    }

    /**
     * create post and continue actions
     * @param postMakerInputData new data
     */
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
