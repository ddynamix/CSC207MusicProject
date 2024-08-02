package use_case.postMaker;

public interface PostMakerOutputBoundary {
    void prepareSuccessView(PostMakerOutputData postMakerOutputData);

    void prepareFailView(String error);
}
