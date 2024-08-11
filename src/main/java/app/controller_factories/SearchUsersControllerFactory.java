package app.controller_factories;

import app.interface_adapter_tools.ViewManagerModel;
import data_access.UserDataAccessInterface;
import use_case.search_users.SearchUsersInputBoundary;
import use_case.search_users.SearchUsersInteractor;
import use_case.search_users.SearchUsersOutputBoundary;
import use_case.search_users.interface_adapter.SearchUsersController;
import use_case.search_users.interface_adapter.SearchUsersPresenter;
import view_model.SearchUsersViewModel;

public class SearchUsersControllerFactory {

    private SearchUsersControllerFactory() {}

    public static SearchUsersController createSearchUsersController(ViewManagerModel viewManagerModel, SearchUsersViewModel searchUsersViewModel, UserDataAccessInterface userDataAccessObject) {
        SearchUsersOutputBoundary searchUsersPresenter = new SearchUsersPresenter(viewManagerModel, searchUsersViewModel);
        SearchUsersInputBoundary searchUsersInteractor = new SearchUsersInteractor(userDataAccessObject, searchUsersPresenter);

        return new SearchUsersController(searchUsersInteractor);
    }
}
