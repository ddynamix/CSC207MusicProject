package use_case.edit_post;

import app.interface_adapter_tools.UserSession;
import data_access.PostDataAccessInterface;
import data_access.PostDoesntExistException;
import data_access.UsersPostsRelationalAccessInterface;
import entity.post.Post;
import entity.user.AudienceUser;
import entity.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EditPostInteractorTest {

    private EditPostInteractor editPostInteractor;
    private EditPostOutputBoundary editPostPresenter;
    private PostDataAccessInterface postDataAccessInterface;
    private UsersPostsRelationalAccessInterface usersPostsRelationalAccessInterface;
    private User user;
    private Post post;

    @BeforeEach
    public void setUp() {
        editPostPresenter = mock(EditPostOutputBoundary.class);
        postDataAccessInterface = mock(PostDataAccessInterface.class);
        usersPostsRelationalAccessInterface = mock(UsersPostsRelationalAccessInterface.class);
        editPostInteractor = new EditPostInteractor(editPostPresenter, postDataAccessInterface, usersPostsRelationalAccessInterface);

        user = new AudienceUser("testUser", "Test User", "testPass", "testMail");
        post = new Post("Test Post", "Test Content", null, null);

        UserSession.getInstance().setLoggedInUser(user);
    }

    @Test
    public void testEditPost() {
        EditPostInputData inputData = new EditPostInputData(post);

        editPostInteractor.editPost(inputData);

        ArgumentCaptor<EditPostOutputData> outputDataCaptor = ArgumentCaptor.forClass(EditPostOutputData.class);
        verify(editPostPresenter, times(1)).goToPostEditor(outputDataCaptor.capture());

        EditPostOutputData capturedOutputData = outputDataCaptor.getValue();
        assertEquals(post, capturedOutputData.getGetPostToEdit());
    }

    @Test
    public void testDeletePost() throws PostDoesntExistException {
        EditPostInputData inputData = new EditPostInputData(post);
        user.addPost(post);

        editPostInteractor.deletePost(inputData);

        ArgumentCaptor<Post> postCaptor = ArgumentCaptor.forClass(Post.class);
        verify(postDataAccessInterface, times(1)).deletePost(postCaptor.capture());
        verify(usersPostsRelationalAccessInterface, times(1)).removePost(eq(user), postCaptor.capture());

        assertEquals(post, postCaptor.getValue());

        ArgumentCaptor<EditPostsSuccessOutputData> outputDataCaptor = ArgumentCaptor.forClass(EditPostsSuccessOutputData.class);
        verify(editPostPresenter, times(1)).prepareSuccessView(outputDataCaptor.capture());

        EditPostsSuccessOutputData capturedOutputData = outputDataCaptor.getValue();
        assertEquals(new ArrayList<>(), capturedOutputData.getPosts());
    }

    @Test
    public void testUpdatePost() throws PostDoesntExistException {
        EditPostInputData inputData = new EditPostInputData(post, "Updated Title", "Updated Content", "updatedMedia");

        editPostInteractor.updatePost(inputData);

        ArgumentCaptor<Post> postCaptor = ArgumentCaptor.forClass(Post.class);
        ArgumentCaptor<String> titleCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> contentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> mediaCaptor = ArgumentCaptor.forClass(String.class);

        verify(postDataAccessInterface, times(1)).updatePost(postCaptor.capture(), titleCaptor.capture(), contentCaptor.capture(), mediaCaptor.capture());

        assertEquals(post, postCaptor.getValue());
        assertEquals("Updated Title", titleCaptor.getValue());
        assertEquals("Updated Content", contentCaptor.getValue());
        assertEquals("updatedMedia", mediaCaptor.getValue());

        ArgumentCaptor<EditPostsSuccessOutputData> outputDataCaptor = ArgumentCaptor.forClass(EditPostsSuccessOutputData.class);
        verify(editPostPresenter, times(1)).prepareSuccessView(outputDataCaptor.capture());

        EditPostsSuccessOutputData capturedOutputData = outputDataCaptor.getValue();
        assertEquals(new ArrayList<>(), capturedOutputData.getPosts());
    }
}