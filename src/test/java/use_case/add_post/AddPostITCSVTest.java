package use_case.add_post;

import app.interface_adapter_tools.UserSession;
import data_access.PostDataAccessInterface;
import data_access.UserDataAccessInterface;
import data_access.UsersPostsRelationalAccessInterface;
import data_access.csv.PostLocalCSVDataStorage;
import data_access.csv.UserLocalCSVDataStorage;
import data_access.csv.UsersPostsRelationalCSVDataStorage;
import entity.post.Post;
import entity.user.AudienceUser;
import org.junit.jupiter.api.*;
import use_case.add_post.interface_adapter.AddPostController;
import use_case.add_post.interface_adapter.AddPostPresenter;
import view_model.HomescreenViewModel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

public class AddPostITCSVTest {

    private AddPostController addPostController;
    private PostLocalCSVDataStorage postStorage;
    private AddPostPresenter addPostPresenter;
    private HomescreenViewModel homescreenViewModel;
    private File postFile;
    private File relationalPostsFile;

    @BeforeEach
    public void setUp() throws IOException {
        File userDataFile = Files.createTempFile("test-user", ".csv").toFile();
        postFile = Files.createTempFile("test-post", ".csv").toFile();
        relationalPostsFile = Files.createTempFile("test-relational", ".csv").toFile();

        UserDataAccessInterface userDataAccess = new UserLocalCSVDataStorage(userDataFile.getAbsolutePath());
        PostDataAccessInterface postDataAccess = new PostLocalCSVDataStorage(postFile.getAbsolutePath(), userDataAccess);
        UsersPostsRelationalAccessInterface relationalPostsStorage = new UsersPostsRelationalCSVDataStorage(relationalPostsFile.getAbsolutePath(), userDataAccess, postDataAccess);
        homescreenViewModel = new HomescreenViewModel();
        addPostPresenter = new AddPostPresenter(homescreenViewModel);
        AddPostInteractor addPostInteractor = new AddPostInteractor(addPostPresenter, relationalPostsStorage);
        addPostController = new AddPostController(addPostInteractor);

        UserSession.getInstance().setLoggedInUser(new AudienceUser("Test User", "testUser", "password", "test@email.com"));
    }

    @AfterEach
    public void tearDown() {
        postFile.delete();
    }

    @Test
    public void testAddPost() throws IOException {
        Post post = new Post("Test Post", "This is a test post content.", UserSession.getInstance().getLoggedInUser(), "media");
        addPostController.addPost(post);

        String fileContent = new String(Files.readAllBytes(relationalPostsFile.toPath()));

        assertTrue(fileContent.contains("testUser,Test Post"));

        //assertEquals(1, homescreenViewModel.getState().getPosts().size());
        //assertEquals("Test Post", homescreenViewModel.getState().getPosts().get(0).getTitle());
    }

    @Test
    public void testRemovePost() throws IOException {
        Post post = new Post("Test Post", "This is a test post content.", UserSession.getInstance().getLoggedInUser(), "media");
        addPostController.addPost(post);
        addPostController.removePost(post);

        String fileContent = new String(Files.readAllBytes(relationalPostsFile.toPath()));

        assertFalse(fileContent.contains("testUser,Test Post"));

        assertTrue(homescreenViewModel.getState().getPosts().isEmpty());
    }
}
