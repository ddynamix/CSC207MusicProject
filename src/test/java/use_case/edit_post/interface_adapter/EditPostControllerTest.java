package use_case.edit_post.interface_adapter;

import entity.post.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_case.edit_post.EditPostInputBoundary;
import use_case.edit_post.EditPostInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EditPostControllerTest {

    Post post = new Post("Test Post", "Test Content", null, null);

    private EditPostController editPostController;
    private EditPostInputBoundary editPostInteractor;

    @BeforeEach
    public void setUp() {
        editPostInteractor = mock(EditPostInputBoundary.class);
        editPostController = new EditPostController(editPostInteractor);
    }

    @Test
    public void testEditPost() {
        Post post = new Post("Test Post", "Test Content", null, null);
        editPostController.editPost(post);

        ArgumentCaptor<EditPostInputData> inputDataCaptor = ArgumentCaptor.forClass(EditPostInputData.class);
        verify(editPostInteractor, times(1)).editPost(inputDataCaptor.capture());

        EditPostInputData capturedInputData = inputDataCaptor.getValue();
        assertEquals(post, capturedInputData.getPostToAlter());
    }

    @Test
    public void testDeletePost() {
        Post post = new Post("Test Post", "Test Content", null, null);
        editPostController.deletePost(post);

        ArgumentCaptor<EditPostInputData> inputDataCaptor = ArgumentCaptor.forClass(EditPostInputData.class);
        verify(editPostInteractor, times(1)).deletePost(inputDataCaptor.capture());

        EditPostInputData capturedInputData = inputDataCaptor.getValue();
        assertEquals(post, capturedInputData.getPostToAlter());
    }

    @Test
    public void testUpdatePost() {
        Post post = new Post("Test Post", "Test Content", null, null);
        String title = "Updated Title";
        String content = "Updated Content";
        String media = "updatedMedia";

        editPostController.updatePost(post, title, content, media);

        ArgumentCaptor<EditPostInputData> inputDataCaptor = ArgumentCaptor.forClass(EditPostInputData.class);
        verify(editPostInteractor, times(1)).updatePost(inputDataCaptor.capture());

        EditPostInputData capturedInputData = inputDataCaptor.getValue();
        assertEquals(post, capturedInputData.getPostToAlter());
        assertEquals(title, capturedInputData.getTitle());
        assertEquals(content, capturedInputData.getText());
        assertEquals(media, capturedInputData.getMedia());
    }
}
