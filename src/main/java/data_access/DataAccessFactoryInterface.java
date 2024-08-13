package data_access;

/**
 * interface for DAO
 */
public interface DataAccessFactoryInterface {
    EventDataAccessInterface getEventDAO();
    UserDataAccessInterface getUserDAO();
    PostDataAccessInterface getPostDAO();

    FollowRelationalAccessInterface getFollowDAO();
    UsersEventsRelationalAccessInterface getUsersEventsDAO();
    UsersPostsRelationalAccessInterface getUsersPostsDAO();
}
