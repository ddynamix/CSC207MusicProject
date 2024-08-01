package use_case.usersignup;

import data_access.UserDataAccessInterface;
import data_access.UserDataAccessObject;
import entity.user.ArtistUser;
import entity.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserSignupInteractorTest {

    private UserDataAccessInterface userDataAccessInterface;
    private SignupOutputBoundary userPresenter;
    private UserSignupInteractor userSignupInteractor;

    @BeforeEach
    void setUp() {
        userDataAccessInterface = mock(UserDataAccessInterface.class);
        userPresenter = mock(SignupOutputBoundary.class);
        userSignupInteractor = new UserSignupInteractor(userDataAccessInterface, userPresenter);
    }

    @Test
    void testAttemptSignUp_PasswordsDoNotMatch() {
        UserSignupData signupData = new UserSignupData("John Doe", "johndoe", "password123", "password1234", "artist", "johndoe@example.com");

        userSignupInteractor.attemptSignUp(signupData);

        verify(userPresenter).prepareFailView("Passwords don't match.");
    }

    @Test
    void testAttemptSignUp_SuccessfulSignup() throws Exception {
        UserSignupData signupData = new UserSignupData("John Doe", "johndoe", "password123", "password123", "artist", "johndoe@example.com");
        User user = new ArtistUser("John Doe", "johndoe", "password123", "johndoe@example.com");

        doNothing().when(userDataAccessInterface).create(any(User.class));

        userSignupInteractor.attemptSignUp(signupData);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userDataAccessInterface).create(userCaptor.capture());
        assertEquals("johndoe", userCaptor.getValue().getUsername());

        ArgumentCaptor<SignupOutputData> outputDataCaptor = ArgumentCaptor.forClass(SignupOutputData.class);
        verify(userPresenter).prepareSuccessView(outputDataCaptor.capture());

        SignupOutputData outputData = outputDataCaptor.getValue();
        assertEquals("johndoe", outputData.getUsername());
    }

    @Test
    void testAttemptSignUp_UsernameAlreadyExists() throws Exception {
        UserSignupData signupData = new UserSignupData("John Doe", "johndoe", "password123", "password123", "artist", "johndoe@example.com");

        doThrow(new UserDataAccessObject.DuplicateUsernameException()).when(userDataAccessInterface).create(any(User.class));

        userSignupInteractor.attemptSignUp(signupData);

        verify(userPresenter).prepareFailView("Username already exists.");
    }
}
