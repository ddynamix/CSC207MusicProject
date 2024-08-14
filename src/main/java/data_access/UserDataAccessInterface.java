package data_access;
import data_access.mongodb.UserDataAccessObject;
import entity.user.ArtistUser;
import entity.user.AudienceUser;
import entity.user.User;
import entity.user.VenueUser;

import java.util.ArrayList;

/**
 * interface for user DAO
 */
public interface UserDataAccessInterface {
    // Data Access Interface - implement CRUD operations for User entity
    boolean userExistsInDatabase(String username);
    void create(User user) throws UserAlreadyExistsException;
    <T extends User> T getUserFromUsername(String username);
    ArrayList<ArtistUser> getArtistUsers();
    ArrayList<VenueUser> getVenueUsers();
    ArrayList<AudienceUser> getAudienceUsers();
    ArrayList<User> getAllUsers();

    boolean passwordMatches(String username, String password);

    // This is also where we declare any Exceptions that wil be thrown
    // e.g. User not found in the database.

    //void Throwable(UserDataAccessObject.UserNotFoundException userNotFoundException);
}
