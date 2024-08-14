package data_access.csv;

import data_access.*;

import java.io.IOException;

/**
 * DAO for all objects
 */
public class CSVDataAccessObjectFactory implements DataAccessFactoryInterface {
    private final UserDataAccessInterface userDataAccessObject;
    private final EventDataAccessInterface eventDataAccessObject;
    private final PostDataAccessInterface postDataAccessObject;
    private final FollowRelationalAccessInterface followRelationalAccessObject;
    private final UsersEventsRelationalAccessInterface usersEventsRelationalAccessObject;
    private final UsersPostsRelationalAccessInterface usersPostsRelationalAccessObject;
    private final SongDataAccessInterface songDataAccessObject;
    private final RelationalSongDataAccessInterface relationalSongDataAccessObject;

    /**
     * create DAO factory
     */
    public CSVDataAccessObjectFactory() {
        try {
            userDataAccessObject = new UserLocalCSVDataStorage("./users.csv");
            eventDataAccessObject = new EventLocalCSVDataStorage("./events.csv", userDataAccessObject);
            postDataAccessObject = new PostLocalCSVDataStorage("./posts.csv", userDataAccessObject);
            followRelationalAccessObject = new FollowRelationalCSVDataStorage("./follows.csv", userDataAccessObject);
            usersEventsRelationalAccessObject = new UsersEventsRelationalCSVDataStorage("./users_events.csv", userDataAccessObject, eventDataAccessObject);
            usersPostsRelationalAccessObject = new UsersPostsRelationalCSVDataStorage("./users_posts.csv", userDataAccessObject, postDataAccessObject);
            songDataAccessObject = new SongCSVDataStorage("./songStorage.csv");
            relationalSongDataAccessObject = new RelationalSongCSVDataAccess("./relationalSongStorage.csv", userDataAccessObject, songDataAccessObject);
        } catch (IOException e) {
            throw new RuntimeException("Could not create CSV data access objects.");
        }
    }

    /**
     * access event DAO
     * @return event DAO
     */
    @Override
    public EventDataAccessInterface getEventDAO() {
        return eventDataAccessObject;
    }

    /**
     * access user DAO
     * @return user DAO
     */
    @Override
    public UserDataAccessInterface getUserDAO() {
        return userDataAccessObject;
    }

    /**
     * access post DAO
     * @return post DAO
     */
    @Override
    public PostDataAccessInterface getPostDAO() {
        return postDataAccessObject;
    }

    /**
     * access user -> follower DAO
     * @return user -> follower DAO
     */
    @Override
    public FollowRelationalAccessInterface getFollowDAO() {
        return followRelationalAccessObject;
    }

    /**
     * access user -> event DAO
     * @return user -> event DAO
     */
    @Override
    public UsersEventsRelationalAccessInterface getUsersEventsDAO() {
        return usersEventsRelationalAccessObject;
    }

    /**
     * access user -> post DAO
     * @return user -> post DAO
     */
    @Override
    public UsersPostsRelationalAccessInterface getUsersPostsDAO() {
        return usersPostsRelationalAccessObject;
    }

    @Override
    public SongDataAccessInterface getSongDAO() {
        return songDataAccessObject;
    }

    @Override
    public RelationalSongDataAccessInterface getRelationalSongDAO() {
        return relationalSongDataAccessObject;
    }
}
