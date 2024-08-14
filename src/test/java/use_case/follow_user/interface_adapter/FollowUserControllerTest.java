package use_case.follow_user.interface_adapter;

import entity.user.AudienceUser;
import entity.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_case.follow_user.FollowUserInputBoundary;
import use_case.follow_user.FollowUserInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FollowUserControllerTest {

    private FollowUserController followUserController;
    private FollowUserInputBoundary followUserInteractor;

    @BeforeEach
    public void setUp() {
        followUserInteractor = mock(FollowUserInputBoundary.class);
        followUserController = new FollowUserController(followUserInteractor);
    }

    @Test
    public void testFollowUser() {
        User userToFollow = new AudienceUser("testUser", "Test User", "testPass", "testMail");

        followUserController.followUser(userToFollow);

        ArgumentCaptor<FollowUserInputData> inputDataCaptor = ArgumentCaptor.forClass(FollowUserInputData.class);
        verify(followUserInteractor, times(1)).followUser(inputDataCaptor.capture());

        FollowUserInputData capturedInputData = inputDataCaptor.getValue();
        assertEquals(userToFollow, capturedInputData.getUser());
    }

    @Test
    public void testUnfollowUser() {
        User userToUnfollow = new AudienceUser("testUser", "Test User", "testPass", "testMail");

        followUserController.unfollowUser(userToUnfollow);

        ArgumentCaptor<FollowUserInputData> inputDataCaptor = ArgumentCaptor.forClass(FollowUserInputData.class);
        verify(followUserInteractor, times(1)).unfollowUser(inputDataCaptor.capture());

        FollowUserInputData capturedInputData = inputDataCaptor.getValue();
        assertEquals(userToUnfollow, capturedInputData.getUser());
    }
}