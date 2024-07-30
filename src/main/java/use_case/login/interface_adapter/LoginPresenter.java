package use_case.login.interface_adapter;

import app.interface_adapter_tools.ViewManagerModel;
import view_model.HomescreenState;
import view_model.HomescreenViewModel;
import view_model.SplashViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

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
    public void prepareSuccessView(LoginOutputData loginOutputData) {
        HomescreenState homescreenState = homescreenViewModel.getState();
        homescreenState.setSignedInAs(loginOutputData.getSignedInAs());
        homescreenViewModel.setState(homescreenState);
        homescreenViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(homescreenViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        viewManagerModel.firePropertyChanged();
    }
}
