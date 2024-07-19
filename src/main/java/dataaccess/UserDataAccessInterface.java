package dataaccess;
import entity.user.User;

public interface UserDataAccessInterface {
    // Data Access Interface - implement CRUD operations for User entity
    boolean userExistsInDatabase(String username);
    void updateUsername(User user, String newUsername);
    void updatePassword(User user, String newPassword, String confirmPassword);
    void updateEmail(User user, String newEmail);
    void create(String username, String password, String email, String firstName, String lastName);
    void delete(User user);
    String[] getUserData(User user);

// This is also where we declare any Exceptions that wil be thrown
// eg. User not found in the database.
    void Throwable(UserDataAccessObject.UserNotFoundException userNotFoundException);
}
