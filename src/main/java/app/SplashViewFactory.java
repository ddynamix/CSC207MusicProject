package app;

import interface_adapter.splash.SplashPresenter;
import interface_adapter.splash.SplashViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.usersignup.UserSignupViewModel;
import view.SplashView;

public class SplashViewFactory {

    private SplashViewFactory () {}

    public static SplashView createSplashView(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SplashViewModel splashViewModel, UserSignupViewModel signupViewModel) {
        SplashPresenter splashPresenter = new SplashPresenter(viewManagerModel, loginViewModel, signupViewModel);
        return new SplashView(splashPresenter, splashViewModel);
    }
}
