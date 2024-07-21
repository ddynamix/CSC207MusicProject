package dataaccess;
import entity.user.User;

public interface UserDataAccessInterface {
    // Data Access Interface - implement CRUD operations for User entity
    boolean userExistsInDatabase(String username);
    void updateUsername(User user, String newUsername) throws UserDataAccessObject.UserNotFoundException;
    void updatePassword(User user, String newPassword, String confirmPassword) throws UserDataAccessObject.PasswordMismatchException, UserDataAccessObject.UserNotFoundException;
    void updateEmail(User user, String newEmail) throws UserDataAccessObject.UserNotFoundException;
    void create(User user) throws UserDataAccessObject.DuplicateUsernameException;
    void delete(User user) throws UserDataAccessObject.UserNotFoundException;
//    String[] getUserData(User user);

// This is also where we declare any Exceptions that wil be thrown
// eg. User not found in the database.
//    void Throwable(UserDataAccessObject.UserNotFoundException userNotFoundException);
}
