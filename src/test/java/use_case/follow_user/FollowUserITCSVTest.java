package use_case.follow_user;

import app.interface_adapter_tools.UserSession;
import data_access.FollowRelationalAccessInterface;
import data_access.UserDataAccessInterface;
import data_access.csv.FollowRelationalCSVDataStorage;
import data_access.csv.UserLocalCSVDataStorage;
import entity.user.AudienceUser;
import entity.user.User;
import org.junit.jupiter.api.*;
import use_case.follow_user.interface_adapter.FollowUserController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

public class FollowUserITCSVTest {

    private FollowUserController followUserController;
    private FollowRelationalAccessInterface followStorage;
    private File followFile;

    @BeforeEach
    public void setUp() throws IOException {
        File userDataFile = Files.createTempFile("test-user", ".csv").toFile();
        followFile = Files.createTempFile("test-follow", ".csv").toFile();

        UserDataAccessInterface userDataAccess = new UserLocalCSVDataStorage(userDataFile.getAbsolutePath());
        FollowRelationalAccessInterface followDataAccess = new FollowRelationalCSVDataStorage(followFile.getAbsolutePath(), userDataAccess);
        FollowUserInteractor followUserInteractor = new FollowUserInteractor(followDataAccess);
        followUserController = new FollowUserController(followUserInteractor);

        UserSession.getInstance().setLoggedInUser(new AudienceUser("Test User", "testUser", "password", "test@email.com"));
    }

    @AfterEach
    public void tearDown() {
        followFile.delete();
    }

    @Test
    public void testFollowUser() throws IOException {
        User userToFollow = new AudienceUser("Follow User", "followUser", "password", "test@email.com");
        followUserController.followUser(userToFollow);

        String fileContent = new String(Files.readAllBytes(followFile.toPath()));

        assertTrue(fileContent.contains("testUser,followUser"));

        //assertEquals(1, userToFollow.getFollowers().size());
        //assertEquals("followUser", UserSession.getInstance().getLoggedInUser().getFollowers().get(0).getUsername());
    }

    @Test
    public void testUnfollowUser() throws IOException {
        User userToFollow = new AudienceUser("followUser", "password", "follow@example.com", "Follow User");
        followUserController.followUser(userToFollow);
        followUserController.unfollowUser(userToFollow);

        String fileContent = new String(Files.readAllBytes(followFile.toPath()));

        assertFalse(fileContent.contains("testUser,followUser"));

        assertTrue(UserSession.getInstance().getLoggedInUser().getFollowers().isEmpty());
    }
}
