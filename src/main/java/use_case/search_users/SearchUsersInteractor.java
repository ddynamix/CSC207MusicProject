package use_case.search_users;

import data_access.UserDataAccessInterface;
import entity.user.ArtistUser;
import entity.user.AudienceUser;
import entity.user.VenueUser;

/**
 * interactor for user search use case
 */
public class SearchUsersInteractor implements SearchUsersInputBoundary {
    private final SearchUsersOutputBoundary searchUsersPresenter;
    private final UserDataAccessInterface userDataAccess;

    /**
     * create instance of interactor for user search use case
     * @param userDataAccess user DAO
     * @param searchUsersPresenter presenter for user search use case
     */
    public SearchUsersInteractor(UserDataAccessInterface userDataAccess, SearchUsersOutputBoundary searchUsersPresenter) {
        this.userDataAccess = userDataAccess;
        this.searchUsersPresenter = searchUsersPresenter;
    }

    /**
     * compare user data
     * @param inputData users searched
     */
    @Override
    public void searchForUser(SearchUsersInputData inputData) {
        if (ArtistUser.class.isAssignableFrom(inputData.getUserClassToSearch())) {
            SearchUsersOutputData outputData = new SearchUsersOutputData(userDataAccess.getArtistUsers());
            searchUsersPresenter.updateDisplayedUsers(outputData);
        } else if (VenueUser.class.isAssignableFrom(inputData.getUserClassToSearch())) {
            SearchUsersOutputData outputData = new SearchUsersOutputData(userDataAccess.getVenueUsers());
            searchUsersPresenter.updateDisplayedUsers(outputData);
        } else if (AudienceUser.class.isAssignableFrom(inputData.getUserClassToSearch())) {
            SearchUsersOutputData outputData = new SearchUsersOutputData(userDataAccess.getAudienceUsers());
            searchUsersPresenter.updateDisplayedUsers(outputData);
        } else {
            SearchUsersOutputData outputData = new SearchUsersOutputData(userDataAccess.getAllUsers());
            searchUsersPresenter.updateDisplayedUsers(outputData);
        }
    }
}
