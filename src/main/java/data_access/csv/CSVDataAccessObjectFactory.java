package data_access.csv;

import data_access.*;

import java.io.IOException;

public class CSVDataAccessObjectFactory implements DataAccessFactoryInterface {
    private final UserDataAccessInterface userDataAccessObject;
    private final EventDataAccessInterface eventDataAccessObject;
    private final PostDataAccessInterface postDataAccessObject;
    private final FollowRelationalAccessInterface followRelationalAccessObject;
    private final UsersEventsRelationalAccessInterface usersEventsRelationalAccessObject;
    private final UsersPostsRelationalAccessInterface usersPostsRelationalAccessObject;

    public CSVDataAccessObjectFactory() {
        try {
            userDataAccessObject = new UserLocalCSVDataStorage("./users.csv");
            eventDataAccessObject = new EventLocalCSVDataStorage("./events.csv", userDataAccessObject);
            postDataAccessObject = new PostLocalCSVDataStorage("./posts.csv", userDataAccessObject);
            followRelationalAccessObject = new FollowRelationalCSVDataStorage("./follows.csv", userDataAccessObject);
            usersEventsRelationalAccessObject = new UsersEventsRelationalCSVDataStorage("./users_events.csv", userDataAccessObject, eventDataAccessObject);
            usersPostsRelationalAccessObject = new UsersPostsRelationalCSVDataStorage("./users_posts.csv", userDataAccessObject, postDataAccessObject);
        } catch (IOException e) {
            throw new RuntimeException("Could not create CSV data access objects.");
        }
    }

    @Override
    public EventDataAccessInterface getEventDAO() {
        return eventDataAccessObject;
    }

    @Override
    public UserDataAccessInterface getUserDAO() {
        return userDataAccessObject;
    }

    @Override
    public PostDataAccessInterface getPostDAO() {
        return postDataAccessObject;
    }

    @Override
    public FollowRelationalAccessInterface getFollowDAO() {
        return followRelationalAccessObject;
    }

    @Override
    public UsersEventsRelationalAccessInterface getUsersEventsDAO() {
        return usersEventsRelationalAccessObject;
    }

    @Override
    public UsersPostsRelationalAccessInterface getUsersPostsDAO() {
        return usersPostsRelationalAccessObject;
    }
}
