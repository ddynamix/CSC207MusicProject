package use_case.splash.interface_adapter;

import use_case.splash.SplashInputBoundary;

public class SplashController {
    final SplashInputBoundary splashInteractor;

    public SplashController(SplashInputBoundary splashInteractor) {
        this.splashInteractor = splashInteractor;
    }

    public void signUpClicked() {
        splashInteractor.goToSignUp();
    }

    public void logInClicked() {
        splashInteractor.goToLogin();
    }
}
