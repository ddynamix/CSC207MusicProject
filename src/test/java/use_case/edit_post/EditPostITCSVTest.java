package use_case.edit_post;

import app.interface_adapter_tools.UserSession;
import app.interface_adapter_tools.ViewManagerModel;
import data_access.PostDataAccessInterface;
import data_access.UserDataAccessInterface;
import data_access.UsersPostsRelationalAccessInterface;
import data_access.csv.PostLocalCSVDataStorage;
import data_access.csv.UserLocalCSVDataStorage;
import data_access.csv.UsersPostsRelationalCSVDataStorage;
import entity.post.Post;
import entity.user.AudienceUser;
import org.junit.jupiter.api.*;
import use_case.edit_post.interface_adapter.EditPostController;
import use_case.edit_post.interface_adapter.EditPostPresenter;
import view_model.PostEditorViewModel;
import view_model.ProfileViewModel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class EditPostITCSVTest {

    private EditPostController editPostController;
    private PostDataAccessInterface postStorage;
    private EditPostPresenter editPostPresenter;
    private ProfileViewModel profileViewModel;
    private PostEditorViewModel postEditorViewModel;
    private File postFile;
    private File usersPostsRelationalFile;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    @BeforeEach
    public void setUp() throws IOException {
        File userDataFile = Files.createTempFile("test-user", ".csv").toFile();
        postFile = Files.createTempFile("test-post", ".csv").toFile();
        usersPostsRelationalFile = Files.createTempFile("test-relational", ".csv").toFile();

        UserDataAccessInterface userDataAccess = new UserLocalCSVDataStorage(userDataFile.getAbsolutePath());
        postStorage = new PostLocalCSVDataStorage(postFile.getAbsolutePath(), userDataAccess);
        UsersPostsRelationalAccessInterface relationalPostsStorage = new UsersPostsRelationalCSVDataStorage(usersPostsRelationalFile.getAbsolutePath(), userDataAccess, postStorage);
        profileViewModel = new ProfileViewModel();
        postEditorViewModel = new PostEditorViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        editPostPresenter = new EditPostPresenter(viewManagerModel, postEditorViewModel);
        EditPostInteractor editPostInteractor = new EditPostInteractor(editPostPresenter, postStorage, relationalPostsStorage);
        editPostController = new EditPostController(editPostInteractor);

        UserSession.getInstance().setLoggedInUser(new AudienceUser("Test User", "testUser", "password", "test@email.com"));
    }

    @AfterEach
    public void tearDown() {
        postFile.delete();
    }

    @Test
    public void testEditPost() throws IOException {
        Post post = new Post("Test Post", "testContent", UserSession.getInstance().getLoggedInUser(), "media");
        postStorage.createPost(post);

        Post editedPost = new Post("Test Post", "editContent", UserSession.getInstance().getLoggedInUser(), "media");
        editPostController.editPost(editedPost);

        String fileContent = new String(Files.readAllBytes(postFile.toPath()));

        String nowString = LocalDate.now().format(formatter);
        System.out.println(fileContent);
        assertTrue(fileContent.contains("Test Post,editContent," + nowString + ",media"));

        // Verify that the presenter updated the view model
        assertEquals(1, profileViewModel.getState().getViewing().getMyPosts().size());
        assertEquals("This is an edited test post content.", profileViewModel.getState().getViewing().getMyPosts().get(0).getText());
    }
}
