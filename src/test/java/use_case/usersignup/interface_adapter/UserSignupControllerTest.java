package use_case.usersignup.interface_adapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_case.usersignup.UserSignupInputBoundary;
import use_case.usersignup.UserSignupData;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class UserSignupControllerTest {

    private UserSignupController userSignupController;
    private UserSignupInputBoundary userSignupInteractor;

    @BeforeEach
    public void setUp() {
        userSignupInteractor = mock(UserSignupInputBoundary.class);
        userSignupController = new UserSignupController(userSignupInteractor);
    }

    @Test
    public void testExecute() {
        String type = "testType";
        String username = "testUser";
        String password = "testPass";
        String repeatPass = "testPass";
        String email = "test@example.com";
        String name = "Test Name";

        userSignupController.execute(type, username, password, repeatPass, email, name);

        ArgumentCaptor<UserSignupData> argumentCaptor = ArgumentCaptor.forClass(UserSignupData.class);
        verify(userSignupInteractor, times(1)).attemptSignUp(argumentCaptor.capture());

        UserSignupData actualInputData = argumentCaptor.getValue();
        UserSignupData expectedInputData = new UserSignupData(type, username, password, repeatPass, email, name);

        assertTrue(fuzzyMatch(expectedInputData, actualInputData));
    }

    private boolean fuzzyMatch(UserSignupData expected, UserSignupData actual) {
        return expected.getUsername().equals(actual.getUsername());
    }
}
