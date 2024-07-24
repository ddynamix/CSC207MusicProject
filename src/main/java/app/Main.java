package app;

import dataaccess.EventDataAccessInterface;
import dataaccess.EventLocalCSVDataStorage;
import dataaccess.UserLocalCSVDataStorage;
import dataaccess.UserDataAccessInterface;
import interface_adapter.eventcrafter.EventCrafterViewModel;
import interface_adapter.homescreen.HomescreenViewModel;
import interface_adapter.splash.SplashViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.usersignup.UserSignupViewModel;
import interface_adapter.ViewManagerModel;
import view.*;

import javax.swing.*;
import java.awt.*;

// TODO: Refactor project according to lecture 8, packaging by layer.
// TODO: Add sign out button.
// TODO: Display events by profile type on User's profile.
// TODO: Add search for venues and artists.

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
        EventDataAccessInterface eventDataAccessObject = null;
        try {
            userDataAccessObject = new UserLocalCSVDataStorage("./users.csv");
            eventDataAccessObject = new EventLocalCSVDataStorage("./events.csv", userDataAccessObject);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Could not open a file data file.");
        }

        // Instantiate and inject all view models
        SplashViewModel splashViewModel = new SplashViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        UserSignupViewModel signupViewModel = new UserSignupViewModel();
        HomescreenViewModel homescreenViewModel = new HomescreenViewModel();
        EventCrafterViewModel eventCrafterViewModel = new EventCrafterViewModel();

        SplashView splashView = SplashViewFactory.createSplashView(viewManagerModel, loginViewModel, splashViewModel, signupViewModel);
        views.add(splashView, splashView.viewName);

        UserSignupView userSignupView = UserSignupViewFactory.createSignupView(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject);
        views.add(userSignupView, userSignupView.viewName);

        LoginView loginView = LoginViewFactory.createLoginView(viewManagerModel, loginViewModel, splashViewModel, homescreenViewModel, userDataAccessObject);
        views.add(loginView, loginView.viewName);

        HomescreenView homescreenView = HomescreenViewFactory.createHomescreenView(viewManagerModel, eventCrafterViewModel, homescreenViewModel, userDataAccessObject, eventDataAccessObject);
        views.add(homescreenView, homescreenView.viewName);

        EventCrafterView eventCrafterView = EventCrafterViewFactory.createEventCrafterView(viewManagerModel, homescreenViewModel, eventCrafterViewModel, eventDataAccessObject, userDataAccessObject);
        views.add(eventCrafterView, eventCrafterView.viewName);

        // Set the initial view
        viewManagerModel.setActiveView(splashView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
