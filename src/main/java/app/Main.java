package app;

import interface_adapter.artistsignup.ArtistSignupViewModel;
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

        // Instantiate and inject all view models
        SplashViewModel splashViewModel = new SplashViewModel();
        SignupSelectorViewModel signupSelectorViewModel = new SignupSelectorViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        AudienceSignupViewModel audienceSignupViewModel = new AudienceSignupViewModel();
        ArtistSignupViewModel artistSignupViewModel = new ArtistSignupViewModel();
        VenueSignupViewModel venueSignupViewModel = new VenueSignupViewModel();

        SplashView splashView = SplashViewFactory.createSplashView(viewManagerModel, loginViewModel, signupSelectorViewModel, splashViewModel);
        views.add(splashView, splashView.viewName);

        SignupSelectorView signupSelectorView = SignupSelectorViewFactory.createSignupSelectorView(viewManagerModel, signupSelectorViewModel, audienceSignupViewModel, artistSignupViewModel, venueSignupViewModel, splashViewModel);
        views.add(signupSelectorView, signupSelectorView.viewName);

        AudienceSignupView audienceSignupView = AudienceSignupViewFactory.createSignupView(viewManagerModel, loginViewModel, audienceSignupViewModel, signupSelectorViewModel);
        views.add(audienceSignupView, audienceSignupView.viewName);

        ArtistSignupView artistSignupView = ArtistSignupViewFactory.createSignupView(viewManagerModel, loginViewModel, artistSignupViewModel, signupSelectorViewModel);
        views.add(artistSignupView, artistSignupView.viewName);

        VenueSignupView venueSignupView = VenueSignupViewFactory.createSignupView(viewManagerModel, loginViewModel, venueSignupViewModel, signupSelectorViewModel);
        views.add(venueSignupView, venueSignupView.viewName);

        LoginView loginView = LoginViewFactory.createLoginView(viewManagerModel, loginViewModel, splashViewModel);
        views.add(loginView, loginView.viewName);

        // Set the initial view
        viewManagerModel.setActiveView(splashView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}