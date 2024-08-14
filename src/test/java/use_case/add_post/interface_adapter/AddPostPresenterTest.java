package use_case.add_post.interface_adapter;

import entity.post.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_case.add_post.AddPostOutputData;
import view_model.HomescreenState;
import view_model.HomescreenViewModel;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AddPostPresenterTest {

    private AddPostPresenter addPostPresenter;
    private HomescreenViewModel homescreenViewModel;

    @BeforeEach
    public void setUp() {
        homescreenViewModel = mock(HomescreenViewModel.class);
        addPostPresenter = new AddPostPresenter(homescreenViewModel);
    }

    @Test
    public void testUpdatePostsView() {
        Post post1 = new Post("Test Post", "Test Content", null, null);
        Post post2 = new Post("Test Post 2", "Test Content 2", null, null);
        AddPostOutputData outputData = new AddPostOutputData(new ArrayList<>(List.of(post1, post2)));

        HomescreenState homescreenState = new HomescreenState();
        when(homescreenViewModel.getState()).thenReturn(homescreenState);

        addPostPresenter.updatePostsView(outputData);

        ArgumentCaptor<HomescreenState> stateCaptor = ArgumentCaptor.forClass(HomescreenState.class);
        verify(homescreenViewModel, times(1)).setState(stateCaptor.capture());
        verify(homescreenViewModel, times(1)).firePropertyChanged();

        HomescreenState capturedState = stateCaptor.getValue();
        assertEquals(outputData.getPostsToDisplay(), capturedState.getPosts());
    }
}