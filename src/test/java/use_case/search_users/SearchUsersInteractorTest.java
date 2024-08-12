package use_case.search_users;

import data_access.UserDataAccessInterface;
import entity.user.ArtistUser;
import entity.user.AudienceUser;
import entity.user.User;
import entity.user.VenueUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class SearchUsersInteractorTest {

    private SearchUsersInteractor searchUsersInteractor;
    private SearchUsersOutputBoundary searchUsersPresenter;
    private UserDataAccessInterface userDataAccess;

    @BeforeEach
    public void setUp() {
        searchUsersPresenter = mock(SearchUsersOutputBoundary.class);
        userDataAccess = mock(UserDataAccessInterface.class);
        searchUsersInteractor = new SearchUsersInteractor(userDataAccess, searchUsersPresenter);
    }

    @Test
    public void testSearchForArtistUser() {
        ArtistUser artistUser1 = new ArtistUser("Artist1", "Artist1", "Artist1", "Artist1");
        ArtistUser artistUser2 = new ArtistUser("Artist2", "Artist2", "Artist2", "Artist2");
        ArrayList<ArtistUser> artistUsers = new ArrayList<>();
        artistUsers.add(artistUser1);
        artistUsers.add(artistUser2);

        when(userDataAccess.getArtistUsers()).thenReturn(artistUsers);

        SearchUsersInputData inputData = new SearchUsersInputData("Artist", ArtistUser.class);
        searchUsersInteractor.searchForUser(inputData);

        SearchUsersOutputData expectedOutputData = new SearchUsersOutputData(artistUsers);
        verify(searchUsersPresenter, times(1)).updateDisplayedUsers(expectedOutputData);
    }

    @Test
    public void testSearchForVenueUser() {
        VenueUser venueUser1 = new VenueUser("Venue1", "Venue1", "Venue1", "Venue1");
        VenueUser venueUser2 = new VenueUser("Venue2", "Venue2", "Venue2", "Venue2");
        ArrayList<VenueUser> venueUsers = new ArrayList<>();
        venueUsers.add(venueUser1);
        venueUsers.add(venueUser2);

        when(userDataAccess.getVenueUsers()).thenReturn(venueUsers);

        SearchUsersInputData inputData = new SearchUsersInputData("Venue", VenueUser.class);
        searchUsersInteractor.searchForUser(inputData);

        SearchUsersOutputData expectedOutputData = new SearchUsersOutputData(venueUsers);
        verify(searchUsersPresenter, times(1)).updateDisplayedUsers(expectedOutputData);
    }

    @Test
    public void testSearchForAudienceUser() {
        AudienceUser audienceUser1 = new AudienceUser("Audience1", "Audience1", "Audience1", "Audience1");
        AudienceUser audienceUser2 = new AudienceUser("Audience2", "Audience2", "Audience2", "Audience2");
        ArrayList<AudienceUser> audienceUsers = new ArrayList<>();
        audienceUsers.add(audienceUser1);
        audienceUsers.add(audienceUser2);

        when(userDataAccess.getAudienceUsers()).thenReturn(audienceUsers);

        SearchUsersInputData inputData = new SearchUsersInputData("Audience", AudienceUser.class);
        searchUsersInteractor.searchForUser(inputData);

        SearchUsersOutputData expectedOutputData = new SearchUsersOutputData(audienceUsers);
        verify(searchUsersPresenter, times(1)).updateDisplayedUsers(expectedOutputData);
    }

    @Test
    public void testSearchForAllUsers() {
        ArrayList<User> allUsers = new ArrayList<>();
        allUsers.add(new ArtistUser("Artist1", "Artist1", "Artist1", "Artist1"));
        allUsers.add(new VenueUser("Venue1", "Venue1", "Venue1", "Venue1"));
        allUsers.add(new AudienceUser("Audience1", "Audience1", "Audience1", "Audience1"));
        when(userDataAccess.getAllUsers()).thenReturn(allUsers);

        SearchUsersInputData inputData = new SearchUsersInputData("All", User.class);
        searchUsersInteractor.searchForUser(inputData);

        SearchUsersOutputData expectedOutputData = new SearchUsersOutputData(allUsers);
        verify(searchUsersPresenter, times(1)).updateDisplayedUsers(expectedOutputData);
    }
}