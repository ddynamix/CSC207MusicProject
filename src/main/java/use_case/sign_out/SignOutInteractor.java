package use_case.sign_out;

import app.interface_adapter_tools.UserSession;

/**
 * interactor for sign out use case
 */
public class SignOutInteractor implements SignOutInputBoundary {
    public SignOutInteractor() {
    }

    /**
     * perform sign out on current user
     */
    @Override
    public void signOut() {
        UserSession.getInstance().signOut();
    }
}
