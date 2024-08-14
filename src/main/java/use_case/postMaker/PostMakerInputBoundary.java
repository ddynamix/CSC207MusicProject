package use_case.postMaker;

/**
 * interface for input data for post use case
 */
public interface PostMakerInputBoundary {
    void attemptPost(PostMakerInputData postMakerInputData);
}
