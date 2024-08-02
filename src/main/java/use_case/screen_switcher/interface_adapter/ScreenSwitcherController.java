package use_case.screen_switcher.interface_adapter;

import use_case.screen_switcher.ScreenSwitcherInputBoundary;

public class ScreenSwitcherController {
    private final ScreenSwitcherInputBoundary screenSwitcherInteractor;

    public ScreenSwitcherController(ScreenSwitcherInputBoundary screenSwitcherInteractor) {
        this.screenSwitcherInteractor = screenSwitcherInteractor;
    }

    public void switchToLogin() {
        screenSwitcherInteractor.switchToLogin();
    }

    public void switchToSplash() {
        screenSwitcherInteractor.switchToSplash();
    }

    public void switchToSignup() {
        screenSwitcherInteractor.switchToSignup();
    }

    public void switchToHome() {
        screenSwitcherInteractor.switchToHome();
    }

    public void switchToMyEvents() {
        screenSwitcherInteractor.switchToMyEvents();
    }

    public void switchToSearchEvents() {
        screenSwitcherInteractor.switchToSearchEvents();
    }

    public void switchToSearchUsers() {
        screenSwitcherInteractor.switchToSearchUsers();
    }

    public void switchToEventCrafter() {
        screenSwitcherInteractor.switchToEventCrafter();
    }

    public void switchToIsFollowing() {
        screenSwitcherInteractor.switchToIsFollowing();
    }

    public void switchToMyFollowers() {
        screenSwitcherInteractor.switchToMyFollowers();
    }

    public void switchToPost() { screenSwitcherInteractor.switchToPost();}
}
