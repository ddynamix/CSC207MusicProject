package use_case.sign_out.interface_adapter;

import use_case.sign_out.SignOutInputBoundary;

public class SignOutController {
    private final SignOutInputBoundary signOutInteractor;

    public SignOutController(SignOutInputBoundary signOutInteractor) {
        this.signOutInteractor = signOutInteractor;
    }

    public void executeSignOut() {
        signOutInteractor.signOut();
    }
}
