package dataaccess;
import entity.user.User;

public interface UserDataAccessInterface {
    // Data Access Interface - implement CRUD
    boolean userExistsInDatabase(String username);

    void update(User user);

    void create(User user);

    void delete(User user);

    String[] getUserData(User user);
}
