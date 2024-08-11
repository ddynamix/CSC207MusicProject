package app.controller_factories;

import use_case.sign_out.SignOutInputBoundary;
import use_case.sign_out.SignOutInteractor;
import use_case.sign_out.interface_adapter.SignOutController;

public class SignOutControllerFactory {
    private SignOutControllerFactory() {}

    public static SignOutController createSignOutController() {
        SignOutInputBoundary signOutInteractor = new SignOutInteractor();

        return new SignOutController(signOutInteractor);
    }
}
