package use_case.splash;

public class SplashInteractor implements SplashInputBoundary {
    private final SplashOutputBoundary presenter;

    public SplashInteractor(SplashOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void goToLogin() {
        presenter.prepareLoginView();
    }

    @Override
    public void goToSignUp() {
        presenter.prepareSignUpView();
    }
}
