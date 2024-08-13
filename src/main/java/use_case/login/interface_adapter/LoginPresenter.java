package use_case.login.interface_adapter;

import app.interface_adapter_tools.ViewManagerModel;
import view_model.HomescreenState;
import view_model.HomescreenViewModel;
import view_model.SplashViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

/**
 * presenter for login use case
 */
public class LoginPresenter implements LoginOutputBoundary {
    private final SplashViewModel splashViewModel;
    private final HomescreenViewModel homescreenViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * create instance of presenter for login use case
     * @param splashViewModel model for splash ciew
     * @param homescreenViewModel model for homescreen
     * @param viewManagerModel manager for changing models
     */
    public LoginPresenter(SplashViewModel splashViewModel, HomescreenViewModel homescreenViewModel, ViewManagerModel viewManagerModel) {
        this.splashViewModel = splashViewModel;
        this.homescreenViewModel = homescreenViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * create homescreen view
     * @param loginOutputData to process
     */
    @Override
    public void prepareSuccessView(LoginOutputData loginOutputData) {
        HomescreenState homescreenState = homescreenViewModel.getState();
        homescreenState.setSignedInAs(loginOutputData.getSignedInAs());
        homescreenViewModel.setState(homescreenState);
        homescreenViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(homescreenViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * update ViewManagerModel
     * @param error custom message
     */
    @Override
    public void prepareFailView(String error) {
        viewManagerModel.firePropertyChanged();
    }
}
