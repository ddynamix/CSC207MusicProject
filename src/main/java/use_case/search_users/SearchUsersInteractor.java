package use_case.search_users;

import data_access.UserDataAccessInterface;
import entity.user.ArtistUser;
import entity.user.AudienceUser;
import entity.user.VenueUser;

public class SearchUsersInteractor implements SearchUsersInputBoundary {
    private final SearchUsersOutputBoundary searchUsersPresenter;
    private final UserDataAccessInterface userDataAccess;

    public SearchUsersInteractor(UserDataAccessInterface userDataAccess, SearchUsersOutputBoundary searchUsersPresenter) {
        this.userDataAccess = userDataAccess;
        this.searchUsersPresenter = searchUsersPresenter;
    }

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
