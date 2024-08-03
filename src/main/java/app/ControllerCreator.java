package app;

import app.controller_factories.*;
import app.interface_adapter_tools.ViewManagerModel;
import app.interface_adapter_tools.ViewModel;
import data_access.*;
import view_model.*;

import java.util.HashMap;

public class ControllerCreator {
    private ControllerCreator() {
    }

    public static HashMap<String, Object> createControllers(ViewManagerModel viewManagerModel, HashMap<String, ViewModel> viewModels, HashMap<String, Object> dataAccessObjects) {
        HashMap<String, Object> controllers = new HashMap<>();

        controllers.put("craftEventController", CraftEventControllerFactory.createCraftEventController(viewManagerModel,
                (EventScreenViewModel) viewModels.get("eventScreenViewModel"),
                (EventDataAccessInterface) dataAccessObjects.get("eventDataAccessObject"),
                (UserDataAccessInterface) dataAccessObjects.get("userDataAccessObject")));

        controllers.put("makePostController", PostMakerControllerFactory.createMakePostController(viewManagerModel,
                (PostMakerViewModel) viewModels.get("postMakerViewModel"),
                (PostDataAccessInterface) dataAccessObjects.get("postDataAccessObject")));

        controllers.put("followUserController", FollowUserControllerFactory.createFollowUserController(
                (FollowRelationalAccessInterface) dataAccessObjects.get("followRelationalAccessObject")
        ));

        controllers.put("loginController", LoginControllerFactory.createLoginController(viewManagerModel,
                (SplashViewModel) viewModels.get("splashViewModel"),
                (HomescreenViewModel) viewModels.get("homescreenViewModel"),
                (UserDataAccessInterface) dataAccessObjects.get("userDataAccessObject")));

        controllers.put("screenSwitcherController", ScreenSwitcherControllerFactory.createScreenSwitcherController(viewManagerModel,
                (LoginViewModel) viewModels.get("loginViewModel"),
                (SplashViewModel) viewModels.get("splashViewModel"),
                (UserSignupViewModel) viewModels.get("signupViewModel"),
                (HomescreenViewModel) viewModels.get("homescreenViewModel"),
                (EventScreenViewModel) viewModels.get("eventScreenViewModel"),
                (SearchEventsViewModel) viewModels.get("searchEventsViewModel"),
                (EventCrafterViewModel) viewModels.get("eventCrafterViewModel"),
                (SearchUsersViewModel) viewModels.get("searchUsersViewModel"),
                (MyFollowersViewModel) viewModels.get("myFollowersViewModel"),
                (IsFollowingViewModel) viewModels.get("isFollowingViewModel"),
                (PostMakerViewModel) viewModels.get("postMakerViewModel"),
                (UserDataAccessInterface) dataAccessObjects.get("userDataAccessObject"),
                (EventDataAccessInterface) dataAccessObjects.get("eventDataAccessObject"),
                (PostDataAccessInterface) dataAccessObjects.get("postDataAccessObject")));

        controllers.put("searchUsersController", SearchUsersControllerFactory.createSearchUsersController(viewManagerModel,
                (SearchUsersViewModel) viewModels.get("searchUsersViewModel"),
                (UserDataAccessInterface) dataAccessObjects.get("userDataAccessObject")));

        controllers.put("signOutController", SignOutControllerFactory.createSignOutController());

        controllers.put("signUpController", SignUpControllerFactory.createSignUpController(viewManagerModel,
                (UserSignupViewModel) viewModels.get("signupViewModel"),
                (SplashViewModel) viewModels.get("splashViewModel"),
                (LoginViewModel) viewModels.get("loginViewModel"),
                (UserDataAccessInterface) dataAccessObjects.get("userDataAccessObject")));

        controllers.put("searchEventsController", SearchEventsControllerFactory.createSearchEventsController(
                (SearchEventsViewModel) viewModels.get("searchEventsViewModel"),
                (EventDataAccessInterface) dataAccessObjects.get("eventDataAccessObject")
        ));

        controllers.put("addEventController", AddEventControllerFactory.createAddEventsController(
                (EventScreenViewModel) viewModels.get("eventScreenViewModel"),
                (UsersEventsRelationalAccessInterface) dataAccessObjects.get("usersEventsRelationalAccessObject")
        ));

        controllers.put("editEventController", EditEventControllerFactory.createEditEventController(viewManagerModel,
                (EventEditorViewModel) viewModels.get("eventEditorViewModel"),
                (EventScreenViewModel) viewModels.get("eventScreenViewModel"),
                (EventDataAccessInterface) dataAccessObjects.get("eventDataAccessObject"),
                (UsersEventsRelationalAccessInterface) dataAccessObjects.get("usersEventsRelationalAccessObject")));

        controllers.put("addPostController", AddPostControllerFactory.createAddPostsController(
                (HomescreenViewModel) viewModels.get("homeScreenViewModel"),
                (UsersPostsRelationalAccessInterface) dataAccessObjects.get("usersPostsRelationalAccessObject")
        ));

        controllers.put("editPostController", EditPostControllerFactory.createEditPostController(viewManagerModel,
                (PostEditorViewModel) viewModels.get("postEditorViewModel"),
                (PostDataAccessInterface) dataAccessObjects.get("postDataAccessObject"),
                (UsersPostsRelationalAccessInterface) dataAccessObjects.get("usersPostsRelationalAccessObject")));

        return controllers;
    }
}
