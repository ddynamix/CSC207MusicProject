package use_case.postMaker.interface_adapter;

import entity.post.Post;
import entity.user.AudienceUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_case.postMaker.PostMakerOutputData;
import view_model.PostMakerState;
import view_model.PostMakerViewModel;
import app.interface_adapter_tools.ViewManagerModel;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PostMakerPresenterTest {

    private PostMakerPresenter postMakerPresenter;
    private PostMakerViewModel postMakerViewModel;
    private ViewManagerModel viewManagerModel;

    @BeforeEach
    public void setUp() {
        postMakerViewModel = mock(PostMakerViewModel.class);
        viewManagerModel = mock(ViewManagerModel.class);
        postMakerPresenter = new PostMakerPresenter(postMakerViewModel, viewManagerModel);
    }

    @Test
    public void testPrepareSuccessView() {
        AudienceUser author = new AudienceUser("testUser", "Test User", "testPass", "testMail");
        Post post1 = new Post("Post1", "content", author,  "Test Media");
        Post post2 = new Post("Post2", "content", author,  "Test Media");
        ArrayList<Post> posts = new ArrayList<>();
        PostMakerOutputData postMakerOutputData = new PostMakerOutputData(posts);

        PostMakerState postMakerState = new PostMakerState();
        when(postMakerViewModel.getState()).thenReturn(postMakerState);

        postMakerPresenter.prepareSuccessView(postMakerOutputData);

        assertEquals(posts, postMakerState.getPosts());
        verify(postMakerViewModel, times(1)).setState(postMakerState);
        verify(postMakerViewModel, times(1)).firePropertyChanged();
        verify(viewManagerModel, times(1)).setActiveView(postMakerViewModel.getViewName());
        verify(viewManagerModel, times(1)).firePropertyChanged();
    }

    @Test
    public void testPrepareFailView() {
        postMakerPresenter.prepareFailView("Post creation failed");

        verify(viewManagerModel, times(1)).firePropertyChanged();
    }
}