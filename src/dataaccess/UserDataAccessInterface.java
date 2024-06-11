package data_access;
import entity.*;

public interface UserDataAccessInterface {
    // Data Access Interface - implement CRUD
    boolean userExistsInDatabase(String username);

    void update(User user);

    void create(User user);

    void delete(User user); // should this be under update?

    String[] getUserData(User user);
}
