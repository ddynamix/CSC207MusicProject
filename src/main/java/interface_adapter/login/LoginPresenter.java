package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.splash.SplashViewModel;
import usecase.userlogin.UserLoginOutputBoundary;
import usecase.userlogin.UserLoginOutputData;

public class LoginPresenter implements UserLoginOutputBoundary {
    private final SplashViewModel splashViewModel;
    private final ViewManagerModel viewManagerModel;

    public LoginPresenter(SplashViewModel splashViewModel, ViewManagerModel viewManagerModel) {
        this.splashViewModel = splashViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void prepareLoginSuccessView(UserLoginOutputData user) {
        //viewManagerModel.setActiveView(splashViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void prepareLoginFailView(String error) {
        //viewManagerModel.setActiveView(splashViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void prepareSplashView() {
        viewManagerModel.setActiveView(splashViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
