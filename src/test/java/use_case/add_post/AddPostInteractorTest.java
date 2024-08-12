package use_case.add_post;

import app.interface_adapter_tools.UserSession;
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

public class AddPostInteractorTest {

    private AddPostInteractor addPostInteractor;
    private AddPostOutputBoundary addPostPresenter;
    private UsersPostsRelationalAccessInterface usersPostsRelationalAccessInterface;
    private User user;

    @BeforeEach
    public void setUp() {
        addPostPresenter = mock(AddPostOutputBoundary.class);
        usersPostsRelationalAccessInterface = mock(UsersPostsRelationalAccessInterface.class);
        addPostInteractor = new AddPostInteractor(addPostPresenter, usersPostsRelationalAccessInterface);
        user = new AudienceUser("testUser", "Test User", "testPass", "testMail");
        UserSession.getInstance().setLoggedInUser(user);
    }

    @Test
    public void testAddPost() {
        Post post = new Post("Test Post", "Test Content", null, null);
        AddPostInputData inputData = new AddPostInputData(post);

        addPostInteractor.addPost(inputData);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        ArgumentCaptor<Post> postCaptor = ArgumentCaptor.forClass(Post.class);
        verify(usersPostsRelationalAccessInterface, times(1)).addPost(userCaptor.capture(), postCaptor.capture());

        assertEquals(user, userCaptor.getValue());
        assertEquals(post, postCaptor.getValue());
    }

    @Test
    public void testRemovePost() {
        Post post = new Post("Test Post", "Test Content", null, null);
        AddPostInputData inputData = new AddPostInputData(post);
        user.addPost(post);

        addPostInteractor.removePost(inputData);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        ArgumentCaptor<Post> postCaptor = ArgumentCaptor.forClass(Post.class);
        verify(usersPostsRelationalAccessInterface, times(1)).removePost(userCaptor.capture(), postCaptor.capture());

        assertEquals(user, userCaptor.getValue());
        assertEquals(post, postCaptor.getValue());

        ArgumentCaptor<AddPostOutputData> outputDataCaptor = ArgumentCaptor.forClass(AddPostOutputData.class);
        verify(addPostPresenter, times(1)).updatePostsView(outputDataCaptor.capture());

        AddPostOutputData capturedOutputData = outputDataCaptor.getValue();
        assertEquals(new ArrayList<>(), capturedOutputData.getPostsToDisplay());
    }
}