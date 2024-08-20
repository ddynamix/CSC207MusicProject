package use_case.search_users;

import app.interface_adapter_tools.UserSession;
import app.interface_adapter_tools.ViewManagerModel;
import data_access.UserAlreadyExistsException;
import data_access.UserDataAccessInterface;
import data_access.csv.UserLocalCSVDataStorage;
import entity.user.AudienceUser;
import entity.user.User;
import org.junit.jupiter.api.*;
import use_case.search_users.interface_adapter.SearchUsersController;
import use_case.search_users.interface_adapter.SearchUsersPresenter;
import view_model.SearchUsersViewModel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class SearchUsersITTest {

    private SearchUsersController searchUsersController;
    private UserDataAccessInterface userStorage;
    private SearchUsersPresenter searchUsersPresenter;
    private SearchUsersViewModel searchUsersViewModel;
    private File userFile;

    @BeforeEach
    public void setUp() throws IOException {
        userFile = Files.createTempFile("test-user", ".csv").toFile();

        userStorage = new UserLocalCSVDataStorage(userFile.getAbsolutePath());
        searchUsersViewModel = new SearchUsersViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        searchUsersPresenter = new SearchUsersPresenter(viewManagerModel, searchUsersViewModel);
        SearchUsersInteractor searchUsersInteractor = new SearchUsersInteractor(userStorage, searchUsersPresenter);
        searchUsersController = new SearchUsersController(searchUsersInteractor);

        UserSession.getInstance().setLoggedInUser(new AudienceUser("testUser", "testUser", "password", "test@email.com"));
    }

    @AfterEach
    public void tearDown() {
        userFile.delete();
    }

    @Test
    public void testSearchUsers() throws IOException {
        User user1 = new AudienceUser("User1", "user1", "password1", "test@1.com");
        User user2 = new AudienceUser("User2", "user2", "password2", "test@2.com");
        try {
            userStorage.create(user1);
            userStorage.create(user2);
        } catch (UserAlreadyExistsException e) {
            e.printStackTrace();
        }

        searchUsersController.searchUser("User", AudienceUser.class);

        ArrayList<? extends User> users = searchUsersViewModel.getState().getUsersToDisplay();
        assertEquals(2, users.size());
        assertEquals("user1", users.get(0).getUsername());
        assertEquals("user2", users.get(1).getUsername());
    }
}
