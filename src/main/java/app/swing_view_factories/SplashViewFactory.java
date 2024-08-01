package app.swing_view_factories;

import use_case.splash.SplashInputBoundary;
import use_case.splash.SplashInteractor;
import use_case.splash.SplashOutputBoundary;
import use_case.splash.interface_adapter.SplashController;
import use_case.splash.interface_adapter.SplashPresenter;
import use_case.splash.interface_adapter.SplashViewModel;
import app.interface_adapter_tools.ViewManagerModel;
import use_case.login.interface_adapter.LoginViewModel;
import use_case.usersignup.interface_adapter.UserSignupViewModel;
import view.jswing_views.SplashView;

/**
 * Splash screen factory
 */
public class SplashViewFactory {

    private SplashViewFactory () {}

    /**
     * create splash screen instance
     *
     * @param viewManagerModel  control of view models
     * @param loginViewModel    data for login view
     * @param splashViewModel   data for this view
     * @param signupViewModel   data for signup view
     * @return SplashView       the created view
     */
    public static SplashView createSplashView(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SplashViewModel splashViewModel, UserSignupViewModel signupViewModel) {
        SplashOutputBoundary splashPresenter = new SplashPresenter(viewManagerModel, loginViewModel, signupViewModel);
        SplashInputBoundary splashInteractor = new SplashInteractor(splashPresenter);
        SplashController splashController = new SplashController(splashInteractor);

        return new SplashView(splashViewModel, splashController);
    }
}
