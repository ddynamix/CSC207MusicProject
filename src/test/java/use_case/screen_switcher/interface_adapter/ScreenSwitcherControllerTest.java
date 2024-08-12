package use_case.screen_switcher.interface_adapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.screen_switcher.ScreenSwitcherInputBoundary;

import static org.mockito.Mockito.*;

public class ScreenSwitcherControllerTest {

    private ScreenSwitcherController screenSwitcherController;
    private ScreenSwitcherInputBoundary screenSwitcherInteractor;

    @BeforeEach
    public void setUp() {
        screenSwitcherInteractor = mock(ScreenSwitcherInputBoundary.class);
        screenSwitcherController = new ScreenSwitcherController(screenSwitcherInteractor);
    }

    @Test
    public void testSwitchToLogin() {
        screenSwitcherController.switchToLogin();
        verify(screenSwitcherInteractor, times(1)).switchToLogin();
    }

    @Test
    public void testSwitchToSplash() {
        screenSwitcherController.switchToSplash();
        verify(screenSwitcherInteractor, times(1)).switchToSplash();
    }

    @Test
    public void testSwitchToSignup() {
        screenSwitcherController.switchToSignup();
        verify(screenSwitcherInteractor, times(1)).switchToSignup();
    }

    @Test
    public void testSwitchToHome() {
        screenSwitcherController.switchToHome();
        verify(screenSwitcherInteractor, times(1)).switchToHome();
    }

    @Test
    public void testSwitchToMyEvents() {
        screenSwitcherController.switchToMyEvents();
        verify(screenSwitcherInteractor, times(1)).switchToMyEvents();
    }

    @Test
    public void testSwitchToSearchEvents() {
        screenSwitcherController.switchToSearchEvents();
        verify(screenSwitcherInteractor, times(1)).switchToSearchEvents();
    }

    @Test
    public void testSwitchToSearchUsers() {
        screenSwitcherController.switchToSearchUsers();
        verify(screenSwitcherInteractor, times(1)).switchToSearchUsers();
    }

    @Test
    public void testSwitchToEventCrafter() {
        screenSwitcherController.switchToEventCrafter();
        verify(screenSwitcherInteractor, times(1)).switchToEventCrafter();
    }

    @Test
    public void testSwitchToIsFollowing() {
        screenSwitcherController.switchToIsFollowing();
        verify(screenSwitcherInteractor, times(1)).switchToIsFollowing();
    }

    @Test
    public void testSwitchToMyFollowers() {
        screenSwitcherController.switchToMyFollowers();
        verify(screenSwitcherInteractor, times(1)).switchToMyFollowers();
    }

    @Test
    public void testSwitchToPost() {
        screenSwitcherController.switchToPost();
        verify(screenSwitcherInteractor, times(1)).switchToPost();
    }

    @Test
    public void testSwitchToMyProfile() {
        screenSwitcherController.switchToMyProfile();
        verify(screenSwitcherInteractor, times(1)).switchToMyProfile();
    }
}