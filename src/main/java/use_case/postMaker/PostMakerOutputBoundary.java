package use_case.postMaker;

/**
 * interface for post use case
 */
public interface PostMakerOutputBoundary {
    void prepareSuccessView(PostMakerOutputData postMakerOutputData);

    void prepareFailView(String error);
}
