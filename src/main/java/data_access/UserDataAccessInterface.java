package data_access;
import data_access.mongodb.UserDataAccessObject;
import entity.user.ArtistUser;
import entity.user.AudienceUser;
import entity.user.User;
import entity.user.VenueUser;

import java.util.ArrayList;

public interface UserDataAccessInterface {
    // Data Access Interface - implement CRUD operations for User entity
    boolean userExistsInDatabase(String username);
    void updateUsername(User user, String newUsername) throws UserDataAccessObject.UserNotFoundException;
    void updatePassword(User user, String newPassword, String confirmPassword) throws UserDataAccessObject.PasswordMismatchException, UserDataAccessObject.UserNotFoundException;
    void updateEmail(User user, String newEmail) throws UserDataAccessObject.UserNotFoundException;
    void create(User user) throws UserDataAccessObject.DuplicateUsernameException;
    void delete(User user) throws UserDataAccessObject.UserNotFoundException;

    User getUserFromUsername(String username);
    ArrayList<ArtistUser> getArtistUsers();
    ArrayList<VenueUser> getVenueUsers();
    ArrayList<AudienceUser> getAudienceUsers();

    boolean passwordMatches(String username, String password);

    // This is also where we declare any Exceptions that wil be thrown
    // e.g. User not found in the database.

    //void Throwable(UserDataAccessObject.UserNotFoundException userNotFoundException);
}
