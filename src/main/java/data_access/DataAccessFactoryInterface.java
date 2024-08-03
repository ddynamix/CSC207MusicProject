package data_access;

public interface DataAccessFactoryInterface {
    EventDataAccessInterface getEventDAO();
    UserDataAccessInterface getUserDAO();

    PostDataAccessInterface getPostDAO();

    FollowRelationalAccessInterface getFollowDAO();
    UsersEventsRelationalAccessInterface getUsersEventsDAO();
}
