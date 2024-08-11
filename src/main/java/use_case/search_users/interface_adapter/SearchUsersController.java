package use_case.search_users.interface_adapter;

import entity.user.User;
import use_case.search_users.SearchUsersInputBoundary;
import use_case.search_users.SearchUsersInputData;

public class SearchUsersController {
    private final SearchUsersInputBoundary searchUsersInteractor;

    public SearchUsersController(SearchUsersInputBoundary searchUsersInteractor) {
        this.searchUsersInteractor = searchUsersInteractor;
    }

    public void searchUser(String searchTerm, Class<? extends User> userType) {
        SearchUsersInputData inputData = new SearchUsersInputData(searchTerm, userType);
        searchUsersInteractor.searchForUser(inputData);
    }
}
