package app;

import dataaccess.UserDataAccessInterface;
import interface_adapter.ViewManagerModel;
import interface_adapter.venuesignup.VenueSignupController;
import interface_adapter.venuesignup.VenueSignupPresenter;
import interface_adapter.venuesignup.VenueSignupViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signupselector.SignupSelectorViewModel;
import usecase.signup.SignupOutputBoundary;
import usecase.signup.venuesignup.VenueSignupInputBoundary;
import usecase.signup.venuesignup.VenueSignupInteractor;
import view.VenueSignupView;

public class VenueSignupViewFactory {

    private VenueSignupViewFactory() {
    }

    public static VenueSignupView createSignupView(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, VenueSignupViewModel venueSignupViewModel, SignupSelectorViewModel signupSelectorViewModel, UserDataAccessInterface userDataAccessObject) {
        VenueSignupController venueSignupController = createUserSignupUseCase(viewManagerModel, venueSignupViewModel, loginViewModel, signupSelectorViewModel, userDataAccessObject);

        VenueSignupPresenter venueSignupPresenter = new VenueSignupPresenter(viewManagerModel, venueSignupViewModel, loginViewModel, signupSelectorViewModel);

        return new VenueSignupView(venueSignupController, venueSignupPresenter, venueSignupViewModel);
    }

    private static VenueSignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, VenueSignupViewModel venueSignupViewModel, LoginViewModel loginViewModel, SignupSelectorViewModel signupSelectorViewModel, UserDataAccessInterface userDataAccessObject) {
        SignupOutputBoundary signupOutputBoundary = new VenueSignupPresenter(viewManagerModel, venueSignupViewModel, loginViewModel, signupSelectorViewModel);

        VenueSignupInputBoundary userSignupInteractor = new VenueSignupInteractor(
                userDataAccessObject, signupOutputBoundary);

        return new VenueSignupController(userSignupInteractor);
    }
}
