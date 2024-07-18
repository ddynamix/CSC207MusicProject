package app;

import dataaccess.TEMPFileAccessDataStorage;
import dataaccess.UserDataAccessInterface;
import interface_adapter.ViewManagerModel;
import interface_adapter.venuesignup.VenueSignupController;
import interface_adapter.venuesignup.VenueSignupPresenter;
import interface_adapter.venuesignup.VenueSignupViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signupselector.SignupSelectorViewModel;
import usecase.SignupOutputBoundary;
import usecase.venuesignup.VenueSignupInputBoundary;
import usecase.venuesignup.VenueSignupInteractor;
import view.VenueSignupView;

import javax.swing.*;
import java.io.IOException;

public class VenueSignupViewFactory {

    private VenueSignupViewFactory() {
    }

    public static VenueSignupView createSignupView(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, VenueSignupViewModel venueSignupViewModel, SignupSelectorViewModel signupSelectorViewModel) {
        VenueSignupController venueSignupController = createUserSignupUseCase(viewManagerModel, venueSignupViewModel, loginViewModel, signupSelectorViewModel);

        VenueSignupPresenter venueSignupPresenter = new VenueSignupPresenter(viewManagerModel, venueSignupViewModel, loginViewModel, signupSelectorViewModel);

        return new VenueSignupView(venueSignupController, venueSignupPresenter, venueSignupViewModel);
    }

    private static VenueSignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, VenueSignupViewModel venueSignupViewModel, LoginViewModel loginViewModel, SignupSelectorViewModel signupSelectorViewModel) {
        try {
            UserDataAccessInterface userDataAccessObject = new TEMPFileAccessDataStorage("./users.csv");

            JOptionPane.showMessageDialog(null, "Could not open user data file.");


            SignupOutputBoundary signupOutputBoundary = new VenueSignupPresenter(viewManagerModel, venueSignupViewModel, loginViewModel, signupSelectorViewModel);

            VenueSignupInputBoundary userSignupInteractor = new VenueSignupInteractor(
                    userDataAccessObject, signupOutputBoundary);

            return new VenueSignupController(userSignupInteractor);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
            return null;
        }
    }
}
