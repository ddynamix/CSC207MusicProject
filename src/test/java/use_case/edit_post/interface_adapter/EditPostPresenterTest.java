package use_case.edit_post.interface_adapter;

import app.interface_adapter_tools.ViewManagerModel;
import entity.post.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_case.edit_post.EditPostOutputData;
import use_case.edit_post.EditPostsSuccessOutputData;
import view_model.PostEditorState;
import view_model.PostEditorViewModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EditPostPresenterTest {

    private EditPostPresenter editPostPresenter;
    private ViewManagerModel viewManagerModel;
    private PostEditorViewModel postEditorViewModel;

    @BeforeEach
    public void setUp() {
        viewManagerModel = mock(ViewManagerModel.class);
        postEditorViewModel = mock(PostEditorViewModel.class);
        editPostPresenter = new EditPostPresenter(viewManagerModel, postEditorViewModel);
    }

    @Test
    public void testGoToPostEditor() {
        EditPostOutputData outputData = new EditPostOutputData(new Post("Test Post", "Test Content", null, null));

        PostEditorState postEditorState = new PostEditorState();
        when(postEditorViewModel.getState()).thenReturn(postEditorState);

        editPostPresenter.goToPostEditor(outputData);

        ArgumentCaptor<PostEditorState> stateCaptor = ArgumentCaptor.forClass(PostEditorState.class);
        verify(postEditorViewModel, times(1)).setState(stateCaptor.capture());
        verify(postEditorViewModel, times(1)).firePropertyChanged();
        verify(viewManagerModel, times(1)).setActiveView("post editor");
        verify(viewManagerModel, times(1)).firePropertyChanged();

        PostEditorState capturedState = stateCaptor.getValue();
        assertEquals(outputData.getGetPostToEdit(), capturedState.getPostToEdit());
    }

    @Test
    public void testPrepareSuccessView() {
        Post post = new Post("Test Post", "Test Content", null, null);
        ArrayList<Post> posts = new ArrayList<>();
        posts.add(post);
        EditPostsSuccessOutputData outputData = new EditPostsSuccessOutputData(posts);

        editPostPresenter.prepareSuccessView(outputData);

        verify(viewManagerModel, times(1)).firePropertyChanged();
    }
}