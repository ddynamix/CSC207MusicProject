package use_case.search_users.interface_adapter;

import app.interface_adapter_tools.ViewManagerModel;
import use_case.search_users.SearchUsersOutputBoundary;
import use_case.search_users.SearchUsersOutputData;
import view_model.SearchUsersState;
import view_model.SearchUsersViewModel;

/**
 * presenter for user search use case
 */
public class SearchUsersPresenter implements SearchUsersOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final SearchUsersViewModel searchUsersViewModel;

    /**
     * create instance of presenter for user search use case
     * @param viewManagerModel manager for changing models
     * @param searchUsersViewModel model for user search use case
     */
    public SearchUsersPresenter(ViewManagerModel viewManagerModel, SearchUsersViewModel searchUsersViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.searchUsersViewModel = searchUsersViewModel;
    }

    /**
     * set output data for user search use case
     * @param outputData new data
     */
    @Override
    public void updateDisplayedUsers(SearchUsersOutputData outputData) {
        SearchUsersState state = searchUsersViewModel.getState();
        state.setUsersToDisplay(outputData.getReturnUsers());
        searchUsersViewModel.setState(state);
        searchUsersViewModel.firePropertyChanged();
    }
}
