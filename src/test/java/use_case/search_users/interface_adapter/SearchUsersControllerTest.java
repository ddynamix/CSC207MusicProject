package use_case.search_users.interface_adapter;

import entity.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_case.search_users.SearchUsersInputBoundary;
import use_case.search_users.SearchUsersInputData;

import static org.junit.jupiter.api.Assertions.assertTrue;
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

        ArgumentCaptor<SearchUsersInputData> argumentCaptor = ArgumentCaptor.forClass(SearchUsersInputData.class);
        verify(searchUsersInteractor, times(1)).searchForUser(argumentCaptor.capture());

        SearchUsersInputData actualInputData = argumentCaptor.getValue();
        SearchUsersInputData expectedInputData = new SearchUsersInputData(searchTerm, userType);

        assertTrue(fuzzyMatch(expectedInputData, actualInputData));
    }

    private boolean fuzzyMatch(SearchUsersInputData expected, SearchUsersInputData actual) {
        return expected.getUserClassToSearch().equals(actual.getUserClassToSearch());
    }
}
