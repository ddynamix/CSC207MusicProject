package use_case.usersignup;

import data_access.UserAlreadyExistsException;
import data_access.UserDataAccessInterface;
import entity.user.ArtistUser;
import entity.user.AudienceUser;
import entity.user.User;
import entity.user.VenueUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class UserSignupInteractorTest {

    private UserSignupInteractor userSignupInteractor;
    private UserDataAccessInterface userDataAccess;
    private SignupOutputBoundary userPresenter;

    @BeforeEach
    public void setUp() {
        userDataAccess = mock(UserDataAccessInterface.class);
        userPresenter = mock(SignupOutputBoundary.class);
        userSignupInteractor = new UserSignupInteractor(userDataAccess, userPresenter);
    }

    @Test
    public void testSuccessfulSignupForArtistUser() throws UserAlreadyExistsException {
        UserSignupData inputData = new UserSignupData("Artist", "artistUser", "password", "password", "artist@example.com", "Artist Name");
        userSignupInteractor.attemptSignUp(inputData);

        verify(userDataAccess, times(1)).create(any(ArtistUser.class));
        verify(userPresenter, times(1)).prepareSuccessView(any(SignupOutputData.class));
    }

    @Test
    public void testSuccessfulSignupForVenueUser() throws UserAlreadyExistsException {
        UserSignupData inputData = new UserSignupData("Venue", "venueUser", "password", "password", "venue@example.com", "Venue Name");
        userSignupInteractor.attemptSignUp(inputData);

        verify(userDataAccess, times(1)).create(any(VenueUser.class));
        verify(userPresenter, times(1)).prepareSuccessView(any(SignupOutputData.class));
    }

    @Test
    public void testSuccessfulSignupForAudienceUser() throws UserAlreadyExistsException {
        UserSignupData inputData = new UserSignupData("Audience", "audienceUser", "password", "password", "audience@example.com", "Audience Name");
        userSignupInteractor.attemptSignUp(inputData);

        verify(userDataAccess, times(1)).create(any(AudienceUser.class));
        verify(userPresenter, times(1)).prepareSuccessView(any(SignupOutputData.class));
    }

    @Test
    public void testPasswordsDoNotMatch() throws UserAlreadyExistsException {
        UserSignupData inputData = new UserSignupData("Artist", "artistUser", "password", "differentPassword", "artist@example.com", "Artist Name");
        userSignupInteractor.attemptSignUp(inputData);

        verify(userDataAccess, never()).create(any(User.class));
        verify(userPresenter, times(1)).prepareFailView("Passwords don't match.");
    }

    @Test
    public void testUsernameAlreadyExists() throws UserAlreadyExistsException {
        UserSignupData inputData = new UserSignupData("Artist", "artistUser", "password", "password", "artist@example.com", "Artist Name");
        doThrow(new UserAlreadyExistsException()).when(userDataAccess).create(any(User.class));
        userSignupInteractor.attemptSignUp(inputData);

        verify(userDataAccess, times(1)).create(any(User.class));
        verify(userPresenter, times(1)).prepareFailView("Username already exists.");
    }
}