package use_case.sign_out.interface_adapter;

import use_case.sign_out.SignOutInputBoundary;

/**
 * controller for sign out use case
 */
public class SignOutController {
    private final SignOutInputBoundary signOutInteractor;

    /**
     * create instance of controller for sign out use case
     * @param signOutInteractor interactor for sign out use case
     */
    public SignOutController(SignOutInputBoundary signOutInteractor) {
        this.signOutInteractor = signOutInteractor;
    }

    /**
     * perform sign out
     * - reset current user
     * - return to splash view
     */
    public void executeSignOut() {
        signOutInteractor.signOut();
    }
}
