package app.controller_factories;

import use_case.sign_out.SignOutInputBoundary;
import use_case.sign_out.SignOutInteractor;
import use_case.sign_out.interface_adapter.SignOutController;

/**
 * Create Sign out controllers
 */
public class SignOutControllerFactory {
    private SignOutControllerFactory() {}

    /**
     * Create instances of sign out controller
     * @return new instance of SignOutController
     */
    public static SignOutController createSignOutController() {
        SignOutInputBoundary signOutInteractor = new SignOutInteractor();

        return new SignOutController(signOutInteractor);
    }
}
