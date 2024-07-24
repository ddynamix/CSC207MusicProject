package use_case.login.interface_adapter;

import interface_adapter.ViewManagerModel;
import use_case.homescreen.interface_adapter.HomescreenState;
import use_case.homescreen.interface_adapter.HomescreenViewModel;
import interface_adapter.splash.SplashViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

import javax.swing.*;

public class LoginPresenter implements LoginOutputBoundary {
    private final SplashViewModel splashViewModel;
    private final HomescreenViewModel homescreenViewModel;
    private final ViewManagerModel viewManagerModel;

    public LoginPresenter(SplashViewModel splashViewModel, HomescreenViewModel homescreenViewModel, ViewManagerModel viewManagerModel) {
        this.splashViewModel = splashViewModel;
        this.homescreenViewModel = homescreenViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData user) {
        HomescreenState homescreenState = homescreenViewModel.getState();
        homescreenState.setSignedInAs(user.getSignedInAs());
        homescreenViewModel.setState(homescreenState);
        homescreenViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(homescreenViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        JOptionPane.showMessageDialog(null, error);
        viewManagerModel.firePropertyChanged();
    }

    public void prepareSplashView() {
        viewManagerModel.setActiveView(splashViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
