package data_access;

public interface DataAccessFactoryInterface {
    EventDataAccessInterface getEventDAO();
    UserDataAccessInterface getUserDAO();
    FollowRelationalAccessInterface getFollowDAO();
}
