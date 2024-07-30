package app.controller_factories;

import app.interface_adapter_tools.ViewManagerModel;
import data_access.FollowRelationalAccessInterface;
import use_case.follow_user.interface_adapter.FollowUserController;
import use_case.follow_user.FollowUserInputBoundary;
import use_case.follow_user.FollowUserInteractor;

public class FollowUserControllerFactory {

    private FollowUserControllerFactory() {}

    public static FollowUserController createFollowUserController(FollowRelationalAccessInterface followRelationalAccessObject) {
        FollowUserInputBoundary followUserInteractor = new FollowUserInteractor(followRelationalAccessObject);

        return new FollowUserController(followUserInteractor);
    }
}
