package app;

import app.swing_view_factories.*;
import data_access.EventDataAccessInterface;
import data_access.UserDataAccessInterface;
import app.interface_adapter_tools.ViewManagerModel;
import app.interface_adapter_tools.ViewModel;
import use_case.splash.interface_adapter.SplashViewModel;
import use_case.eventcrafter.interface_adapter.EventCrafterViewModel;
import use_case.homescreen.interface_adapter.HomescreenViewModel;
import use_case.login.interface_adapter.LoginViewModel;
import use_case.usersignup.interface_adapter.UserSignupViewModel;
import view.jswing_views.*;
import view.jswing_views.HomescreenView;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class SwingViewCreator implements ViewCreatorInterface {
    private final JFrame application = new JFrame("Music App");

    // prevent instantiation
    public SwingViewCreator() {
    }

    @Override
    public void createAllViews(HashMap<String, ViewModel> viewModels, HashMap<String, Object> dataAccessObjects) {
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        SplashView splashView = SplashViewFactory.createSplashView(viewManagerModel,
                (LoginViewModel) viewModels.get("loginViewModel"),
                (SplashViewModel) viewModels.get("splashViewModel"),
                (UserSignupViewModel) viewModels.get("signupViewModel"));
        views.add(splashView, splashView.viewName);

        UserSignupView userSignupView = UserSignupViewFactory.createSignupView(viewManagerModel,
                (LoginViewModel) viewModels.get("loginViewModel"),
                (UserSignupViewModel) viewModels.get("signupViewModel"),
                (SplashViewModel) viewModels.get("splashViewModel"),
                (UserDataAccessInterface) dataAccessObjects.get("userDataAccessObject"));
        views.add(userSignupView, userSignupView.viewName);

        LoginView loginView = LoginViewFactory.createLoginView(viewManagerModel,
                (LoginViewModel) viewModels.get("loginViewModel"),
                (SplashViewModel) viewModels.get("splashViewModel"),
                (HomescreenViewModel) viewModels.get("homescreenViewModel"),
                (UserDataAccessInterface) dataAccessObjects.get("userDataAccessObject"));
        views.add(loginView, loginView.viewName);

        HomescreenView homescreenView = HomescreenViewFactory.createHomescreenView(viewManagerModel,
                (EventCrafterViewModel) viewModels.get("eventCrafterViewModel"),
                (HomescreenViewModel) viewModels.get("homescreenViewModel"),
                (SplashViewModel) viewModels.get("splashViewModel"),
                (EventDataAccessInterface) dataAccessObjects.get("eventDataAccessObject"),
                (UserDataAccessInterface) dataAccessObjects.get("userDataAccessObject"));
        views.add(homescreenView, homescreenView.viewName);

        EventCrafterView eventCrafterView = EventCrafterViewFactory.createEventCrafterView(viewManagerModel,
                (HomescreenViewModel) viewModels.get("homescreenViewModel"),
                (EventCrafterViewModel) viewModels.get("eventCrafterViewModel"),
                (EventDataAccessInterface) dataAccessObjects.get("eventDataAccessObject"),
                (UserDataAccessInterface) dataAccessObjects.get("userDataAccessObject"));
        views.add(eventCrafterView, eventCrafterView.viewName);

        // Set the initial view
        viewManagerModel.setActiveView(splashView.viewName);
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void run() {
        application.pack();
        application.setVisible(true);
    }
}
