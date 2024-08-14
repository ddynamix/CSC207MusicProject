package use_case.login;

import app.interface_adapter_tools.UserSession;
import data_access.UserDataAccessInterface;
import entity.user.AudienceUser;
import entity.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LoginInteractorTest {

    private LoginInteractor loginInteractor;
    private UserDataAccessInterface userDataAccessObject;
    private LoginOutputBoundary loginPresenter;
    private User user;

    @BeforeEach
    public void setUp() {
        userDataAccessObject = mock(UserDataAccessInterface.class);
        loginPresenter = mock(LoginOutputBoundary.class);
        loginInteractor = new LoginInteractor(userDataAccessObject, loginPresenter);

        user = new AudienceUser("testUser", "Test User", "testPass", "testMail");
    }

    @Test
    public void testAttemptLogin_UserDoesNotExist() {
        LoginInputData inputData = new LoginInputData("nonExistentUser", "testPass");

        when(userDataAccessObject.userExistsInDatabase(inputData.getUsername())).thenReturn(false);

        loginInteractor.attemptLogin(inputData);

        verify(loginPresenter, times(1)).prepareFailView("User does not exist.");
    }

    @Test
    public void testAttemptLogin_IncorrectPassword() {
        LoginInputData inputData = new LoginInputData("testUser", "wrongPass");

        when(userDataAccessObject.userExistsInDatabase(inputData.getUsername())).thenReturn(true);
        when(userDataAccessObject.passwordMatches(inputData.getUsername(), inputData.getPassword())).thenReturn(false);

        loginInteractor.attemptLogin(inputData);

        verify(loginPresenter, times(1)).prepareFailView("Incorrect password.");
    }

    @Test
    public void testAttemptLogin_Success() {
        LoginInputData inputData = new LoginInputData("testUser", "testPass");

        when(userDataAccessObject.userExistsInDatabase(inputData.getUsername())).thenReturn(true);
        when(userDataAccessObject.passwordMatches(inputData.getUsername(), inputData.getPassword())).thenReturn(true);
        when(userDataAccessObject.getUserFromUsername(inputData.getUsername())).thenReturn(user);

        loginInteractor.attemptLogin(inputData);

        verify(loginPresenter, times(1)).prepareSuccessView(any(LoginOutputData.class));
        assertEquals(user, UserSession.getInstance().getLoggedInUser());
    }
}