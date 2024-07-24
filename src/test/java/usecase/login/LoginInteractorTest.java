package usecase.login;

import dataaccess.UserDataAccessInterface;
import entity.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class LoginInteractorTest {

    private LoginInteractor loginInteractor;

    @Mock
    private UserDataAccessInterface userDataAccessObject;

    @Mock
    private LoginOutputBoundary loginPresenter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        loginInteractor = new LoginInteractor(userDataAccessObject, loginPresenter);
    }

    @Test
    public void testAttemptLogin_UserDoesNotExist() {
        // Arrange
        LoginInputData loginInputData = new LoginInputData("user", "password");
        when(userDataAccessObject.userExistsInDatabase("user")).thenReturn(false);

        // Act
        loginInteractor.attemptLogin(loginInputData);

        // Assert
        verify(loginPresenter).prepareFailView("User does not exist.");
        verify(loginPresenter, never()).prepareSuccessView(any());
    }

    @Test
    public void testAttemptLogin_IncorrectPassword() {
        // Arrange
        LoginInputData loginInputData = new LoginInputData("user", "password");
        when(userDataAccessObject.userExistsInDatabase("user")).thenReturn(true);
        when(userDataAccessObject.passwordMatches("user", "password")).thenReturn(false);

        // Act
        loginInteractor.attemptLogin(loginInputData);

        // Assert
        verify(loginPresenter).prepareFailView("Incorrect password.");
        verify(loginPresenter, never()).prepareSuccessView(any());
    }

    @Test
    public void testAttemptLogin_Success() {
        LoginInputData loginInputData = new LoginInputData("user", "password");
        when(userDataAccessObject.userExistsInDatabase("user")).thenReturn(true);
        when(userDataAccessObject.passwordMatches("user", "password")).thenReturn(true);

        LoginOutputData expectedOutputData = new LoginOutputData(new User("name", "user", "password", "email"));

        loginInteractor.attemptLogin(loginInputData);

        verify(loginPresenter).prepareSuccessView(expectedOutputData);
        verify(loginPresenter, never()).prepareFailView(any());
    }
}
