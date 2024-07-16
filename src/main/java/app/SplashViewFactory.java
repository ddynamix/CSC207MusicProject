package app;

import interface_adapter.splash.SplashPresenter;
import interface_adapter.splash.SplashViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import view.SplashView;

public class SplashViewFactory {

    private SplashViewFactory () {}

    public static SplashView createSplashView(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignupViewModel signupViewModel, SplashViewModel splashViewModel) {
        SplashPresenter splashPresenter = new SplashPresenter(viewManagerModel, loginViewModel, signupViewModel);
        return new SplashView(splashPresenter, splashViewModel);
    }
}
