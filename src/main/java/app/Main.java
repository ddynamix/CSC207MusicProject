package app;

import app.interface_adapter_tools.Theme;
import app.interface_adapter_tools.ViewManagerModel;
import data_access.csv.CSVDataAccessObjectFactory;
import data_access.DataAccessFactoryInterface;
import app.interface_adapter_tools.ViewModel;
import view_model.*;
import view.jswing_views.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/*
 * TODO: Display events by profile type on User's profile.
 * TODO: Fix tests.
 * TODO: Add string search to search users.
 * TODO: Add spotify API Implementation.
 */

/**
 * Main runable program
 * creates frame and instantiates lists of required objects
 * starts application
 */
public class Main {
    static JFrame application;

    /**
     * runable
     * @param args program parameters
     */
    public static void main(String[] args) {
        // Create JFrame
        application = new JFrame("Music App");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.getContentPane().setBackground(new Color(200, 200, 200));

        // Here, you will choose how to display the app.
        ViewCreatorInterface viewCreatorInterface = new SwingViewCreator();


        // Here, you will choose how to store the data.
        DataAccessFactoryInterface dataAccessFactory = new CSVDataAccessObjectFactory();


        // Instantiate all data access objects.
        HashMap<String, Object> dataAccessObjects = new HashMap<>();
        dataAccessObjects.put("userDataAccessObject", dataAccessFactory.getUserDAO());
        dataAccessObjects.put("eventDataAccessObject", dataAccessFactory.getEventDAO());
        dataAccessObjects.put("postDataAccessObject", dataAccessFactory.getPostDAO());
        dataAccessObjects.put("followRelationalAccessObject", dataAccessFactory.getFollowDAO());
        dataAccessObjects.put("usersEventsRelationalAccessObject", dataAccessFactory.getUsersEventsDAO());
        dataAccessObjects.put("usersPostsRelationalAccessObject", dataAccessFactory.getUsersPostsDAO());
        dataAccessObjects.put("songDataAccessObject", dataAccessFactory.getSongDAO());
        dataAccessObjects.put("relationalSongDataAccessObject", dataAccessFactory.getRelationalSongDAO());


        // Create the JFrame
        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // Create the view manager that switches the views in Swing
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // Instantiate all view models.
        HashMap<String, ViewModel> viewModels = new HashMap<>();
        viewModels.put("splashViewModel", new SplashViewModel());
        viewModels.put("loginViewModel", new LoginViewModel());
        viewModels.put("signupViewModel", new UserSignupViewModel());
        viewModels.put("homescreenViewModel", new HomescreenViewModel());
        viewModels.put("eventScreenViewModel", new EventScreenViewModel());
        viewModels.put("searchEventsViewModel", new SearchEventsViewModel());
        viewModels.put("eventCrafterViewModel", new EventCrafterViewModel());
        viewModels.put("searchUsersViewModel", new SearchUsersViewModel());
        viewModels.put("myFollowersViewModel", new MyFollowersViewModel());
        viewModels.put("isFollowingViewModel", new IsFollowingViewModel());
        viewModels.put("eventEditorViewModel", new EventEditorViewModel());
        viewModels.put("postMakerViewModel", new PostMakerViewModel());
        viewModels.put("postEditorViewModel", new PostEditorViewModel());
        viewModels.put("profileViewModel", new ProfileViewModel());
        viewModels.put("editProfileViewModel", new ProfileEditViewModel());

        // Implement all use cases.
        HashMap<String, Object> controllers = ControllerCreator.createControllers(viewManagerModel, viewModels, dataAccessObjects);

        // Build the views.
        viewCreatorInterface.createAllViews(views, viewManagerModel, viewModels, controllers, dataAccessObjects);

        // Set the initial view
        viewManagerModel.setActiveView("splash");
        viewManagerModel.firePropertyChanged();

        // Set dimensions and location of JFrame
        application.setSize(400, 800);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - application.getWidth()) / 2;
        int y = (screenSize.height - application.getHeight()) / 2;
        application.setLocation(x, y);
        application.setIconImage(new ImageIcon("src/main/resources/icon.png").getImage());

        // Start the application
        themeUpdate();
        application.setVisible(true);
    }

    /**
     * ability to change to dark mode and back
     */
    public static void themeUpdate() {
        SwingUtilities.invokeLater(() -> {
            Theme.ThemeManager.applyTheme(application.getContentPane());
            application.getContentPane().revalidate();
            application.getContentPane().repaint();
        });
    }
}
