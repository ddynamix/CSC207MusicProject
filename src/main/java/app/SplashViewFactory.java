package app;

import interface_adapter.signupselector.SignupSelectorViewModel;
import interface_adapter.splash.SplashPresenter;
import interface_adapter.splash.SplashViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import view.SplashView;

public class SplashViewFactory {

    private SplashViewFactory () {}

    public static SplashView createSplashView(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignupSelectorViewModel signupSelectorViewModel, SplashViewModel splashViewModel) {
        SplashPresenter splashPresenter = new SplashPresenter(viewManagerModel, loginViewModel, signupSelectorViewModel);
        return new SplashView(splashPresenter, splashViewModel);
    }
}
