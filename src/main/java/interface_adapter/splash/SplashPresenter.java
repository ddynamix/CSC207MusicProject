package interface_adapter.splash;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.audiencesignup.AudienceSignupViewModel;
import interface_adapter.signupselector.SignupSelectorViewModel;

public class SplashPresenter {
    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;
    private final SignupSelectorViewModel signupSelectorViewModel;

    public SplashPresenter(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignupSelectorViewModel signupSelectorViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.signupSelectorViewModel = signupSelectorViewModel;
    }

    public void prepareSignupView() {
        System.out.println("SplashPresenter.prepareSignupView");
        viewManagerModel.setActiveView(signupSelectorViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void prepareLoginView() {
        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
