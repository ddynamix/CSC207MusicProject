package app;

import dataaccess.UserDataAccessInterface;
import interface_adapter.*;
import interface_adapter.login.LoginViewModel;
import interface_adapter.audiencesignup.AudienceSignupController;
import interface_adapter.audiencesignup.AudienceSignupPresenter;
import interface_adapter.audiencesignup.AudienceSignupViewModel;
import interface_adapter.signupselector.SignupSelectorViewModel;
import usecase.signup.audiencesignup.AudienceSignupInputBoundary;
import usecase.signup.audiencesignup.AudienceSignupInteractor;
import usecase.signup.SignupOutputBoundary;
import view.AudienceSignupView;

public class AudienceSignupViewFactory {

    private AudienceSignupViewFactory() {
    }

    public static AudienceSignupView createSignupView(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, AudienceSignupViewModel audienceSignupViewModel, SignupSelectorViewModel signupSelectorViewModel, UserDataAccessInterface userDataAccessObject) {
        AudienceSignupController audienceSignupController = createUserSignupUseCase(viewManagerModel, audienceSignupViewModel, loginViewModel, signupSelectorViewModel, userDataAccessObject);

        AudienceSignupPresenter audienceSignupPresenter = new AudienceSignupPresenter(viewManagerModel, audienceSignupViewModel, loginViewModel, signupSelectorViewModel);

        return new AudienceSignupView(audienceSignupController, audienceSignupPresenter, audienceSignupViewModel);
    }

    private static AudienceSignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, AudienceSignupViewModel audienceSignupViewModel, LoginViewModel loginViewModel, SignupSelectorViewModel signupSelectorViewModel, UserDataAccessInterface userDataAccessObject) {
        SignupOutputBoundary signupOutputBoundary = new AudienceSignupPresenter(viewManagerModel, audienceSignupViewModel, loginViewModel, signupSelectorViewModel);

        AudienceSignupInputBoundary userSignupInteractor = new AudienceSignupInteractor(
                userDataAccessObject, signupOutputBoundary);

        return new AudienceSignupController(userSignupInteractor);
    }
}
