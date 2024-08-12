package use_case.postMaker;

import data_access.PostDataAccessInterface;
import entity.post.Post;
import entity.user.AudienceUser;
import entity.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PostMakerInteractorTest {

    private PostMakerInteractor postMakerInteractor;
    private PostDataAccessInterface postDataAccessInterface;
    private PostMakerOutputBoundary postMakerPresenter;

    @BeforeEach
    public void setUp() {
        postDataAccessInterface = mock(PostDataAccessInterface.class);
        postMakerPresenter = mock(PostMakerOutputBoundary.class);
        postMakerInteractor = new PostMakerInteractor(postDataAccessInterface, postMakerPresenter);
    }

    @Test
    public void testAttemptPost() {
        User author = new AudienceUser("testUser", "Test User", "testPass", "testMail");
        PostMakerInputData inputData = new PostMakerInputData("Test Title", "Test Text", author, "testMedia");
        Post post = new Post(inputData.getTitle(), inputData.getText(), inputData.getAuthor(), inputData.getAttachedMedia());

        ArrayList<Post> posts = new ArrayList<>();
        posts.add(post);
        when(postDataAccessInterface.getPosts()).thenReturn(posts);

        postMakerInteractor.attemptPost(inputData);

        ArgumentCaptor<Post> postCaptor = ArgumentCaptor.forClass(Post.class);
        verify(postDataAccessInterface, times(1)).createPost(postCaptor.capture());
        assertEquals(post, postCaptor.getValue());

        ArgumentCaptor<PostMakerOutputData> outputDataCaptor = ArgumentCaptor.forClass(PostMakerOutputData.class);
        verify(postMakerPresenter, times(1)).prepareSuccessView(outputDataCaptor.capture());
        assertEquals(posts, outputDataCaptor.getValue().getPosts());
    }
}