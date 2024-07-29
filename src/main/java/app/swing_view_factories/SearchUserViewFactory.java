package app.swing_view_factories;

import app.interface_adapter_tools.ViewManagerModel;
import data_access.FollowRelationalAccessInterface;
import data_access.UserDataAccessInterface;
import use_case.follow_user.FollowUserInputBoundary;
import use_case.follow_user.FollowUserInteractor;
import use_case.follow_user.interface_adapter.FollowUserController;
import use_case.search_users.SearchUsersInputBoundary;
import use_case.search_users.SearchUsersInteractor;
import use_case.search_users.SearchUsersOutputBoundary;
import use_case.search_users.interface_adapter.SearchUsersController;
import use_case.search_users.interface_adapter.SearchUsersPresenter;
import use_case.search_users.interface_adapter.SearchUsersViewModel;
import view.jswing_views.SearchUserView;

/**
 * Search user view factory
 */
public class SearchUserViewFactory {

    private SearchUserViewFactory() {
    }

    /**
     * create search user instance
     *
     * @param headerFactory        factory for header
     * @param viewManagerModel     control of view models
     * @param searchUsersViewModel data for search users view
     * @param userDataAccessObject data access object for users
     * @return SearchUserView      the created view
     */
    public static SearchUserView createSearchUserView(HeaderFactory headerFactory, ViewManagerModel viewManagerModel, SearchUsersViewModel searchUsersViewModel, UserDataAccessInterface userDataAccessObject, FollowRelationalAccessInterface followRelationalAccessObject) {
        SearchUsersOutputBoundary searchUsersPresenter = new SearchUsersPresenter(viewManagerModel, searchUsersViewModel);
        SearchUsersInputBoundary searchUsersInteractor = new SearchUsersInteractor(userDataAccessObject, searchUsersPresenter);
        SearchUsersController searchUsersController = new SearchUsersController(searchUsersInteractor);

        FollowUserInputBoundary followUserInteractor = new FollowUserInteractor(followRelationalAccessObject);
        FollowUserController followUserController = new FollowUserController(followUserInteractor);

        return new SearchUserView(searchUsersViewModel, searchUsersController, followUserController, headerFactory.createHeader());
    }
}
