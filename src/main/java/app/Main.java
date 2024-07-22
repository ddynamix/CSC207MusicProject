package app;

import dataaccess.TEMPFileAccessDataStorage;
import dataaccess.UserDataAccessInterface;
import interface_adapter.homescreen.HomescreenViewModel;
import interface_adapter.splash.SplashViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.usersignup.UserSignupViewModel;
import interface_adapter.ViewManagerModel;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

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
        LoginViewModel loginViewModel = new LoginViewModel();
        UserSignupViewModel signupViewModel = new UserSignupViewModel();
        HomescreenViewModel homescreenViewModel = new HomescreenViewModel();

        SplashView splashView = SplashViewFactory.createSplashView(viewManagerModel, loginViewModel, splashViewModel, signupViewModel);
        views.add(splashView, splashView.viewName);

        UserSignupView userSignupView = UserSignupViewFactory.createSignupView(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject);
        views.add(userSignupView, userSignupView.viewName);

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