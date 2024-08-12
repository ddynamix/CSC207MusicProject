package use_case.search_users.interface_adapter;

import entity.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.search_users.SearchUsersInputBoundary;
import use_case.search_users.SearchUsersInputData;

import static org.mockito.Mockito.*;

public class SearchUsersControllerTest {

    private SearchUsersController searchUsersController;
    private SearchUsersInputBoundary searchUsersInteractor;

    @BeforeEach
    public void setUp() {
        searchUsersInteractor = mock(SearchUsersInputBoundary.class);
        searchUsersController = new SearchUsersController(searchUsersInteractor);
    }

    @Test
    public void testSearchUser() {
        String searchTerm = "testUser";
        Class<? extends User> userType = User.class;

        searchUsersController.searchUser(searchTerm, userType);

        SearchUsersInputData expectedInputData = new SearchUsersInputData(searchTerm, userType);
        verify(searchUsersInteractor, times(1)).searchForUser(expectedInputData);
    }
}