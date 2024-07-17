package dataaccess;
import entity.user.User;

public interface UserDataAccessInterface {
    // Data Access Interface - implement CRUD
    boolean userExistsInDatabase(String username);

    void update(User user);

    void create(String username, String password, String email, String firstName, String lastName);

    void delete(User user);

    String[] getUserData(User user);

// This is also where we declare any Exceptions that wil be thrown
// eg. User not found in the database.

    void Throwable(UserDataAccessObject.UserNotFoundException userNotFoundException);
}
