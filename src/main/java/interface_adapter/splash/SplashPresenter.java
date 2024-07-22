package interface_adapter.splash;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.usersignup.UserSignupViewModel;

public class SplashPresenter {
    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;
    private final UserSignupViewModel signUpViewModel;

    public SplashPresenter(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, UserSignupViewModel signupViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.signUpViewModel = signupViewModel;
    }

    public void prepareSignupView() {
        System.out.println("SplashPresenter.prepareSignupView");
        viewManagerModel.setActiveView(signUpViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void prepareLoginView() {
        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
