package use_case.search_users.interface_adapter;

import app.interface_adapter_tools.ViewManagerModel;
import entity.user.AudienceUser;
import entity.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.search_users.SearchUsersOutputData;
import view_model.SearchUsersState;
import view_model.SearchUsersViewModel;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SearchUsersPresenterTest {

    private SearchUsersPresenter searchUsersPresenter;
    private ViewManagerModel viewManagerModel;
    private SearchUsersViewModel searchUsersViewModel;

    @BeforeEach
    public void setUp() {
        viewManagerModel = mock(ViewManagerModel.class);
        searchUsersViewModel = mock(SearchUsersViewModel.class);
        searchUsersPresenter = new SearchUsersPresenter(viewManagerModel, searchUsersViewModel);
    }

    @Test
    public void testUpdateDisplayedUsers() {
        AudienceUser user1 = new AudienceUser("User1", "User1", "User1", "User1");
        AudienceUser user2 = new AudienceUser("User2", "User2", "User2", "User2");
        ArrayList<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        SearchUsersOutputData outputData = new SearchUsersOutputData(users);
        SearchUsersState state = new SearchUsersState();
        when(searchUsersViewModel.getState()).thenReturn(state);

        searchUsersPresenter.updateDisplayedUsers(outputData);

        verify(searchUsersViewModel, times(1)).setState(state);
        verify(searchUsersViewModel, times(1)).firePropertyChanged();
        assertEquals(outputData.getReturnUsers(), state.getUsersToDisplay());
    }
}