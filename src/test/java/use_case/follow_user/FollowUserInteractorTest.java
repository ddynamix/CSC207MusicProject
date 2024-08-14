package use_case.follow_user;

import app.interface_adapter_tools.UserSession;
import data_access.FollowRelationalAccessInterface;
import entity.user.AudienceUser;
import entity.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FollowUserInteractorTest {

    private FollowUserInteractor followUserInteractor;
    private FollowRelationalAccessInterface followRelationalAccess;
    private User follower;
    private User followee;

    @BeforeEach
    public void setUp() {
        followRelationalAccess = mock(FollowRelationalAccessInterface.class);
        followUserInteractor = new FollowUserInteractor(followRelationalAccess);

        follower = new AudienceUser("followerUser", "Follower User", "followerPass", "followerMail");
        followee = new AudienceUser("followeeUser", "Followee User", "followeePass", "followeeMail");
        UserSession.getInstance().setLoggedInUser(follower);
    }

    @Test
    public void testFollowUser() {
        FollowUserInputData inputData = new FollowUserInputData(followee);

        followUserInteractor.followUser(inputData);

        ArgumentCaptor<User> followerCaptor = ArgumentCaptor.forClass(User.class);
        ArgumentCaptor<User> followeeCaptor = ArgumentCaptor.forClass(User.class);
        verify(followRelationalAccess, times(1)).addFollower(followerCaptor.capture(), followeeCaptor.capture());

        assertEquals(follower, followerCaptor.getValue());
        assertEquals(followee, followeeCaptor.getValue());
    }

    @Test
    public void testUnfollowUser() {
        FollowUserInputData inputData = new FollowUserInputData(followee);

        followUserInteractor.unfollowUser(inputData);

        ArgumentCaptor<User> followerCaptor = ArgumentCaptor.forClass(User.class);
        ArgumentCaptor<User> followeeCaptor = ArgumentCaptor.forClass(User.class);
        verify(followRelationalAccess, times(1)).removeFollower(followerCaptor.capture(), followeeCaptor.capture());

        assertEquals(follower, followerCaptor.getValue());
        assertEquals(followee, followeeCaptor.getValue());
    }
}