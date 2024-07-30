package use_case.sign_out;

import app.interface_adapter_tools.UserSession;

public class SignOutInteractor implements SignOutInputBoundary {
    public SignOutInteractor() {
    }

    @Override
    public void signOut() {
        UserSession.getInstance().signOut();
    }
}
