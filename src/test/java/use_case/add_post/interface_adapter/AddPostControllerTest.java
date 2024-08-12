package use_case.add_post.interface_adapter;

import entity.post.Post;
import entity.user.AudienceUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_case.add_post.AddPostInputBoundary;
import use_case.add_post.AddPostInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AddPostControllerTest {

    private AddPostController addPostController;
    private AddPostInputBoundary addPostInteractor;

    private AudienceUser audienceUser;

    @BeforeEach
    public void setUp() {
        addPostInteractor = mock(AddPostInputBoundary.class);
        addPostController = new AddPostController(addPostInteractor);
        audienceUser = new AudienceUser("testUser", "Test User", "testUser", "testMail");
    }

    @Test
    public void testAddPost() {
        Post post = new Post("Test Post", "Test Content", audienceUser, null);
        addPostController.addPost(post);

        ArgumentCaptor<AddPostInputData> inputDataCaptor = ArgumentCaptor.forClass(AddPostInputData.class);
        verify(addPostInteractor, times(1)).addPost(inputDataCaptor.capture());

        AddPostInputData capturedInputData = inputDataCaptor.getValue();
        assertEquals(post, capturedInputData.getPostToAddOrRemove());
    }

    @Test
    public void testRemovePost() {
        Post post = new Post("Test Post", "Test Content", audienceUser, null);
        addPostController.removePost(post);

        ArgumentCaptor<AddPostInputData> inputDataCaptor = ArgumentCaptor.forClass(AddPostInputData.class);
        verify(addPostInteractor, times(1)).removePost(inputDataCaptor.capture());

        AddPostInputData capturedInputData = inputDataCaptor.getValue();
        assertEquals(post, capturedInputData.getPostToAddOrRemove());
    }
}