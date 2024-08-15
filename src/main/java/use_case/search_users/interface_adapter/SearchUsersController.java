package use_case.search_users.interface_adapter;

import entity.user.User;
import use_case.search_users.SearchUsersInputBoundary;
import use_case.search_users.SearchUsersInputData;

/**
 * controller for user search use case
 */
public class SearchUsersController {
    private final SearchUsersInputBoundary searchUsersInteractor;

    /**
     * create instance of controller for user search use case
     * @param searchUsersInteractor interactor to pass information to controller
     */
    public SearchUsersController(SearchUsersInputBoundary searchUsersInteractor) {
        this.searchUsersInteractor = searchUsersInteractor;
    }

    /**
     * access and update data
     * @param searchTerm comparison object
     * @param userType designated class type
     */
    public void searchUser(String searchTerm, Class<? extends User> userType) {
        SearchUsersInputData inputData = new SearchUsersInputData(searchTerm, userType);
        searchUsersInteractor.searchForUser(inputData);
    }
}
