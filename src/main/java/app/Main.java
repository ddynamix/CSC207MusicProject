package app;

import dataaccess.TEMPFileAccessDataStorage;
import dataaccess.UserDataAccessInterface;
import interface_adapter.artistsignup.ArtistSignupViewModel;
import interface_adapter.homescreen.HomescreenViewModel;
import interface_adapter.signupselector.SignupSelectorViewModel;
import interface_adapter.splash.SplashViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.audiencesignup.AudienceSignupViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.venuesignup.VenueSignupViewModel;
import view.*;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame application = new JFrame("Music App");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        UserDataAccessInterface userDataAccessObject = null;
        try {
            userDataAccessObject = new TEMPFileAccessDataStorage("./users.csv");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        // Instantiate and inject all view models
        SplashViewModel splashViewModel = new SplashViewModel();
        SignupSelectorViewModel signupSelectorViewModel = new SignupSelectorViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        AudienceSignupViewModel audienceSignupViewModel = new AudienceSignupViewModel();
        ArtistSignupViewModel artistSignupViewModel = new ArtistSignupViewModel();
        VenueSignupViewModel venueSignupViewModel = new VenueSignupViewModel();
        HomescreenViewModel homescreenViewModel = new HomescreenViewModel();

        SplashView splashView = SplashViewFactory.createSplashView(viewManagerModel, loginViewModel, signupSelectorViewModel, splashViewModel);
        views.add(splashView, splashView.viewName);

        SignupSelectorView signupSelectorView = SignupSelectorViewFactory.createSignupSelectorView(viewManagerModel, signupSelectorViewModel, audienceSignupViewModel, artistSignupViewModel, venueSignupViewModel, splashViewModel);
        views.add(signupSelectorView, signupSelectorView.viewName);

        AudienceSignupView audienceSignupView = AudienceSignupViewFactory.createSignupView(viewManagerModel, loginViewModel, audienceSignupViewModel, signupSelectorViewModel, userDataAccessObject);
        views.add(audienceSignupView, audienceSignupView.viewName);

        ArtistSignupView artistSignupView = ArtistSignupViewFactory.createSignupView(viewManagerModel, loginViewModel, artistSignupViewModel, signupSelectorViewModel, userDataAccessObject);
        views.add(artistSignupView, artistSignupView.viewName);

        VenueSignupView venueSignupView = VenueSignupViewFactory.createSignupView(viewManagerModel, loginViewModel, venueSignupViewModel, signupSelectorViewModel, userDataAccessObject);
        views.add(venueSignupView, venueSignupView.viewName);

        LoginView loginView = LoginViewFactory.createLoginView(viewManagerModel, loginViewModel, splashViewModel, homescreenViewModel, userDataAccessObject);
        views.add(loginView, loginView.viewName);

        HomescreenView homescreenView = HomescreenViewFactory.createHomescreenView(viewManagerModel, homescreenViewModel);
        views.add(homescreenView, homescreenView.viewName);

        // Set the initial view
        viewManagerModel.setActiveView(splashView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}