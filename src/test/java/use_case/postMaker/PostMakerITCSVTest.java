package use_case.post_maker;

import app.interface_adapter_tools.UserSession;
import app.interface_adapter_tools.ViewManagerModel;
import data_access.PostDataAccessInterface;
import data_access.UserDataAccessInterface;
import data_access.csv.PostLocalCSVDataStorage;
import data_access.csv.UserLocalCSVDataStorage;
import entity.user.AudienceUser;
import org.junit.jupiter.api.*;
import use_case.postMaker.PostMakerInteractor;
import use_case.postMaker.interface_adapter.PostMakerController;
import use_case.postMaker.interface_adapter.PostMakerPresenter;
import view_model.PostMakerViewModel;
import view_model.ProfileViewModel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class PostMakerITCSVTest {

    private PostMakerController postMakerController;
    private PostDataAccessInterface postStorage;
    private PostMakerPresenter postMakerPresenter;
    private PostMakerViewModel postMakerViewModel;
    private ProfileViewModel profileViewModel;
    private File postFile;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @BeforeEach
    public void setUp() throws IOException {
        File userDataFile = Files.createTempFile("test-user", ".csv").toFile();
        postFile = Files.createTempFile("test-post", ".csv").toFile();

        UserDataAccessInterface userDataAccess = new UserLocalCSVDataStorage(userDataFile.getAbsolutePath());
        postStorage = new PostLocalCSVDataStorage(postFile.getAbsolutePath(), userDataAccess);
        profileViewModel = new ProfileViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        postMakerViewModel = new PostMakerViewModel();
        postMakerPresenter = new PostMakerPresenter(postMakerViewModel, viewManagerModel);
        PostMakerInteractor postMakerInteractor = new PostMakerInteractor(postStorage, postMakerPresenter);
        postMakerController = new PostMakerController(postMakerInteractor, postStorage);

        UserSession.getInstance().setLoggedInUser(new AudienceUser("Test User", "testUser", "password", "test@email.com"));
    }

    @AfterEach
    public void tearDown() {
        // Delete the temporary file
        postFile.delete();
    }

    @Test
    public void testCreatePost() throws IOException {
        postMakerController.execute("Test Post", "Test content", UserSession.getInstance().getLoggedInUser(), "testMedia");

        String fileContent = new String(Files.readAllBytes(postFile.toPath()));

        String nowString = LocalDateTime.now().format(formatter);
        assertTrue(fileContent.contains("Test Post,Test content,testUser," + nowString + ",testMedia"));

        //assertEquals(1, profileViewModel.getState().getViewing().getMyPosts().size());
        //assertEquals("Test Post", profileViewModel.getState().getViewing().getMyPosts().get(0).getTitle());
    }
}
