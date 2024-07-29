package app;

import data_access.csv.CSVDataAccessObjectFactory;
import data_access.DataAccessFactoryInterface;
import app.interface_adapter_tools.ViewModel;
import use_case.eventcrafter.interface_adapter.EventCrafterViewModel;
import use_case.eventscreen.interface_adapter.EventScreenViewModel;
import use_case.homescreen.interface_adapter.HomescreenViewModel;
import use_case.search_users.interface_adapter.SearchUsersViewModel;
import use_case.splash.interface_adapter.SplashViewModel;
import use_case.login.interface_adapter.LoginViewModel;
import use_case.usersignup.interface_adapter.UserSignupViewModel;

import java.util.HashMap;

/*
 * TODO: Display events by profile type on User's profile.
 * TODO: Add search for venues and artists.
 * TODO: Fix tests.
 * TODO: Edit Event Feature for your events.
 * TODO: Implement following feature.
 * TODO: Create event view object to make it look better.
 * TODO: Change the way views are switched using screen switcher use case.
 */

public class Main {
    public static void main(String[] args) {
        // Here, you will choose how to display the app.
        ViewCreatorInterface viewCreatorInterface = new SwingViewCreator();


        // Here, you will choose how to store the data.
        DataAccessFactoryInterface dataAccessFactory = new CSVDataAccessObjectFactory();


        // Instantiate all data access objects.
        HashMap<String, Object> dataAccessObjects = new HashMap<>();
        dataAccessObjects.put("userDataAccessObject", dataAccessFactory.getUserDAO());
        dataAccessObjects.put("eventDataAccessObject", dataAccessFactory.getEventDAO());
        dataAccessObjects.put("followRelationalAccessObject", dataAccessFactory.getFollowDAO());

        // Instantiate all view models.
        HashMap<String, ViewModel> viewModels = new HashMap<>();
        viewModels.put("splashViewModel", new SplashViewModel());
        viewModels.put("loginViewModel", new LoginViewModel());
        viewModels.put("signupViewModel", new UserSignupViewModel());
        viewModels.put("homescreenViewModel", new HomescreenViewModel());
        viewModels.put("eventScreenViewModel", new EventScreenViewModel());
        viewModels.put("eventCrafterViewModel", new EventCrafterViewModel());
        viewModels.put("searchUsersViewModel", new SearchUsersViewModel());
        // Build the views and run.
        viewCreatorInterface.createAllViews(viewModels, dataAccessObjects);
        viewCreatorInterface.run();
    }
}
