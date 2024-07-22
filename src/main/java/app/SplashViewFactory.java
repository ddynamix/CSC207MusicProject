package app;

import interface_adapter.splash.SplashPresenter;
import interface_adapter.splash.SplashViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.usersignup.UserSignupViewModel;
import view.SplashView;

/**
 * Factory class for splash
 */
public class SplashViewFactory {

    private SplashViewFactory () {}

    /**
     * create instance
     * @param viewManagerModel control of view pages
     * @param loginViewModel    login view creator
     * @param splashViewModel   splash view creator
     * @param signupViewModel   signup view creator
     * @return
     */
    public static SplashView createSplashView(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SplashViewModel splashViewModel, UserSignupViewModel signupViewModel) {
        SplashPresenter splashPresenter = new SplashPresenter(viewManagerModel, loginViewModel, signupViewModel);
        return new SplashView(splashPresenter, splashViewModel);
    }
}
