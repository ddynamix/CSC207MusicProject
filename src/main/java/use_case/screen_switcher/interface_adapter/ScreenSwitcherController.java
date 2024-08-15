package use_case.screen_switcher.interface_adapter;

import use_case.screen_switcher.ScreenSwitcherInputBoundary;

/**
 * controller for switcher
 */
public class ScreenSwitcherController {
    private final ScreenSwitcherInputBoundary screenSwitcherInteractor;

    /**
     * create instance of controller for switcher
     * @param screenSwitcherInteractor interactor for switcher
     */
    public ScreenSwitcherController(ScreenSwitcherInputBoundary screenSwitcherInteractor) {
        this.screenSwitcherInteractor = screenSwitcherInteractor;
    }

    /**
     * interactor change to login
     */
    public void switchToLogin() {
        screenSwitcherInteractor.switchToLogin();
    }

    /**
     * interactor change to splash
     */
    public void switchToSplash() {
        screenSwitcherInteractor.switchToSplash();
    }

    /**
     * interactor change to signup
     */
    public void switchToSignup() {
        screenSwitcherInteractor.switchToSignup();
    }

    /**
     * interactor change to homescreen
     */
    public void switchToHome() {
        screenSwitcherInteractor.switchToHome();
    }

    /**
     * interactor change to event screen
     */
    public void switchToMyEvents() {
        screenSwitcherInteractor.switchToMyEvents();
    }

    /**
     * interactor change to event search
     */
    public void switchToSearchEvents() {
        screenSwitcherInteractor.switchToSearchEvents();
    }

    /**
     * interactor change to user search
     */
    public void switchToSearchUsers() {
        screenSwitcherInteractor.switchToSearchUsers();
    }

    /**
     * interactor change to event craft
     */
    public void switchToEventCrafter() {
        screenSwitcherInteractor.switchToEventCrafter();
    }

    /**
     * interactor change to following
     */
    public void switchToIsFollowing() {
        screenSwitcherInteractor.switchToIsFollowing();
    }

    /**
     * interactor change to follower
     */
    public void switchToMyFollowers() {
        screenSwitcherInteractor.switchToMyFollowers();
    }

    /**
     * inteactor change to make post
     */
    public void switchToPost() { screenSwitcherInteractor.switchToPost();}

    /**
     * interactor change to profile
     */
    public void switchToMyProfile() {
        screenSwitcherInteractor.switchToMyProfile();
    }
}
