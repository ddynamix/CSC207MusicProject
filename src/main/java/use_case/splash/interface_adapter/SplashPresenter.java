package use_case.splash.interface_adapter;

import app.interface_adapter_tools.ViewManagerModel;
import use_case.login.interface_adapter.LoginViewModel;
import use_case.splash.SplashOutputBoundary;
import use_case.usersignup.interface_adapter.UserSignupViewModel;

/**
 * Presenter for splash view
 */
public class SplashPresenter implements SplashOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;
    private final UserSignupViewModel signUpViewModel;

    /**
     * instance of presenter
     * @param viewManagerModel  controller of view models
     * @param loginViewModel    login view creator
     * @param signupViewModel   signup view creator
     */
    public SplashPresenter(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, UserSignupViewModel signupViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.signUpViewModel = signupViewModel;
    }

    /**
     * Create signup view
     */
    @Override
    public void prepareSignUpView() {
        viewManagerModel.setActiveView(signUpViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * create login view
     */
    @Override
    public void prepareLoginView() {
        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
