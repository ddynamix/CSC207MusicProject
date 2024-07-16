package interface_adapter.splash;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;

public class SplashPresenter {
    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;
    private final SignupViewModel signupViewModel;

    public SplashPresenter(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignupViewModel signupViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = new LoginViewModel();
        this.signupViewModel = new SignupViewModel();
    }

    public void prepareSignupView() {
        viewManagerModel.setActiveView(signupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void prepareLoginView() {
        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
