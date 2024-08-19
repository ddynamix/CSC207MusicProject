package app.controller_factories;

import data_access.FollowRelationalAccessInterface;
import use_case.follow_user.interface_adapter.FollowUserController;
import use_case.follow_user.FollowUserInputBoundary;
import use_case.follow_user.FollowUserInteractor;

/**
 * Create follow user controllers
 */
public class FollowUserControllerFactory {

    private FollowUserControllerFactory() {}

    /**
     * create follow user controller
     * @param followRelationalAccessObject DAO for access to following compared to user
     * @return new instance of FollowUserController
     */
    public static FollowUserController createFollowUserController(FollowRelationalAccessInterface followRelationalAccessObject) {
        FollowUserInputBoundary followUserInteractor = new FollowUserInteractor(followRelationalAccessObject);

        return new FollowUserController(followUserInteractor);
    }
}
