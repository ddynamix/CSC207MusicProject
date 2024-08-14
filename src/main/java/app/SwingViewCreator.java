package app;

import data_access.EventDataAccessInterface;
import data_access.PostDataAccessInterface;
import data_access.UserDataAccessInterface;
import app.interface_adapter_tools.ViewManagerModel;
import app.interface_adapter_tools.ViewModel;

import use_case.add_event.interface_adapter.AddEventController;
import use_case.add_post.interface_adapter.AddPostController;
import use_case.add_favourite_song.interface_adapter.AddFavouriteSongController;
import use_case.edit_event.interface_adapter.EditEventController;
import use_case.play_music.interface_adapter.PlayMusicController;
import use_case.postMaker.interface_adapter.PostMakerController;
//import use_case.add_post.interface_adapter.AddPostController;
import use_case.edit_post.interface_adapter.EditPostController;

import use_case.eventcrafter.interface_adapter.EventCrafterController;
import use_case.search_events.interface_adapter.SearchEventsController;
import use_case.sign_out.interface_adapter.SignOutController;
import view_model.*;
import use_case.follow_user.interface_adapter.FollowUserController;
import use_case.login.interface_adapter.LoginController;
import use_case.screen_switcher.interface_adapter.ScreenSwitcherController;
import use_case.search_users.interface_adapter.SearchUsersController;
import use_case.usersignup.interface_adapter.UserSignupController;
import view.jswing_views.*;

import javax.swing.*;
import java.util.HashMap;

public class SwingViewCreator implements ViewCreatorInterface {
    /**
     * empty contructor to prevent instantiation
     */
    public SwingViewCreator() {
    }

    /**
     * instantiation of all views
     * @param views accumulator of all views
     * @param viewManagerModel model for switching views
     * @param viewModels map of title string -> ViewModel
     * @param controllers map of title string -> Object
     * @param dataAccessObjects map of title string -> DAOs
     */
    @Override
    public void createAllViews(JPanel views, ViewManagerModel viewManagerModel, HashMap<String, ViewModel> viewModels,
                               HashMap<String, Object> controllers, HashMap<String, Object> dataAccessObjects) {
        HeaderFactory headerFactory = new HeaderFactory(viewManagerModel,
                (LoginViewModel) viewModels.get("loginViewModel"),
                (SplashViewModel) viewModels.get("splashViewModel"),
                (UserSignupViewModel) viewModels.get("signupViewModel"),
                (HomescreenViewModel) viewModels.get("homescreenViewModel"),
                (EventScreenViewModel) viewModels.get("eventScreenViewModel"),
                (EventCrafterViewModel) viewModels.get("eventCrafterViewModel"),
                (SearchUsersViewModel) viewModels.get("searchUsersViewModel"),
                (PostMakerViewModel) viewModels.get("postMakerViewModel"),

                (ScreenSwitcherController) controllers.get("screenSwitcherController"),
                (SignOutController) controllers.get("signOutController"),

                (UserDataAccessInterface) dataAccessObjects.get("userDataAccessObject"),
                (EventDataAccessInterface) dataAccessObjects.get("eventDataAccessObject"));

        SplashView splashView = new SplashView(
                (SplashViewModel) viewModels.get("splashViewModel"),
                (ScreenSwitcherController) controllers.get("screenSwitcherController"));
        views.add(splashView, splashView.viewName);

        UserSignupView userSignupView = new UserSignupView(
                (UserSignupViewModel) viewModels.get("signupViewModel"),
                (UserSignupController) controllers.get("userSignupController"),
                (ScreenSwitcherController) controllers.get("screenSwitcherController"));
        views.add(userSignupView, userSignupView.viewName);

        LoginView loginView = new LoginView(
                (LoginViewModel) viewModels.get("loginViewModel"),
                (LoginController) controllers.get("loginController"),
                (ScreenSwitcherController) controllers.get("screenSwitcherController"));
        views.add(loginView, loginView.viewName);

        HomescreenView homescreenView = new HomescreenView(
                (HomescreenViewModel) viewModels.get("homescreenViewModel"),
                (ScreenSwitcherController) controllers.get("screenSwitcherController"),
                (SignOutController) controllers.get("signOutController"),
                (EditPostController) controllers.get("editPostController"),
                (PostDataAccessInterface) dataAccessObjects.get("postDataAccessObject"),
                headerFactory.createHeader());
        views.add(homescreenView, homescreenView.viewName);

        EventScreenView eventScreenView = new EventScreenView(
                (EventScreenViewModel) viewModels.get("eventScreenViewModel"),
                (ScreenSwitcherController) controllers.get("screenSwitcherController"),
                (AddEventController) controllers.get("addEventController"),
                (EditEventController) controllers.get("editEventController"),
                headerFactory.createHeader());
        views.add(eventScreenView, eventScreenView.viewName);

        SearchEventsView searchEventsView = new SearchEventsView(
                (SearchEventsViewModel) viewModels.get("searchEventsViewModel"),
                (SearchEventsController) controllers.get("searchEventsController"),
                (AddEventController) controllers.get("addEventController"),
                (ScreenSwitcherController) controllers.get("screenSwitcherController"),
                headerFactory.createHeader());
        views.add(searchEventsView, searchEventsView.viewName);

        EventCrafterView eventCrafterView = new EventCrafterView(
                (EventCrafterViewModel) viewModels.get("eventCrafterViewModel"),
                (EventCrafterController) controllers.get("craftEventController"),
                (ScreenSwitcherController) controllers.get("screenSwitcherController"),
                headerFactory.createHeader());
        views.add(eventCrafterView, eventCrafterView.viewName);

        SearchUserView searchUserView = new SearchUserView(
                (SearchUsersViewModel) viewModels.get("searchUsersViewModel"),
                (SearchUsersController) controllers.get("searchUsersController"),
                (FollowUserController)  controllers.get("followUserController"),
                (ScreenSwitcherController) controllers.get("screenSwitcherController"),
                headerFactory.createHeader());
        views.add(searchUserView, searchUserView.viewName);

        MyFollowersView myFollowersView = new MyFollowersView(
                (MyFollowersViewModel) viewModels.get("myFollowersViewModel"),
                (ScreenSwitcherController) controllers.get("screenSwitcherController"),
                (FollowUserController)  controllers.get("followUserController"),
                headerFactory.createHeader());
        views.add(myFollowersView, myFollowersView.viewName);

        IsFollowingView isFollowingView = new IsFollowingView(
                (IsFollowingViewModel) viewModels.get("isFollowingViewModel"),
                (ScreenSwitcherController) controllers.get("screenSwitcherController"),
                (FollowUserController)  controllers.get("followUserController"),
                headerFactory.createHeader());
        views.add(isFollowingView, isFollowingView.viewName);

        EventEditorView eventEditorView = new EventEditorView(
                (EventEditorViewModel) viewModels.get("eventEditorViewModel"),
                (EditEventController) controllers.get("editEventController"),
                (ScreenSwitcherController) controllers.get("screenSwitcherController"));
        views.add(eventEditorView, eventEditorView.viewName);

        PostMakerView postMakerView = new PostMakerView(
                (PostMakerViewModel) viewModels.get("postMakerViewModel"),
                (PostMakerController) controllers.get("makePostController"),
                (ScreenSwitcherController) controllers.get("screenSwitcherController"),
                headerFactory.createHeader());
        views.add(postMakerView, postMakerView.viewName);

        PostEditorView postEditorView = new PostEditorView(
                (PostEditorViewModel) viewModels.get("postEditorViewModel"),
                (EditPostController) controllers.get("editPostController"),
                (ScreenSwitcherController) controllers.get("screenSwitcherController"));
        views.add(postEditorView, postEditorView.viewName);

        ProfileView profileView = new ProfileView(
                (ProfileViewModel) viewModels.get("profileViewModel"),
                (ScreenSwitcherController) controllers.get("screenSwitcherController"),
                (PlayMusicController) controllers.get("playMusicController"),
                (AddFavouriteSongController) controllers.get("addFavouriteSongController"),
                (EditPostController) controllers.get("editPostController"),
                headerFactory.createHeader());
        views.add(profileView, profileView.viewName);
    }
}
